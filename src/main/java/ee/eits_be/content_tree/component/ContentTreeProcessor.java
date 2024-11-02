package ee.eits_be.content_tree.component;

import ee.eits_be.external.dto.MenuStructureDto;
import ee.eits_be.external.service.EITSProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ContentTreeProcessor {
    private final EITSProxyService proxyService;
    private static final List<String> MEASURE_GROUP_CODES = List.of("3.2", "3.3", "3.4");
    private static final String CATALOG_TITLE = "Etalonturbe kataloog";
    private static final String METHODS_TITLE = "3 Meetmed";


    public List<MenuStructureDto> fetchContentTreeByVersion(String version) {
        var contentTreeDto = proxyService.fetchContentTreeByVersion(version);
        return findAndProcessCorrectCatalog(contentTreeDto.getArticle().getStructure());
    }

    private List<MenuStructureDto> findAndProcessCorrectCatalog(List<MenuStructureDto> structures) {
        if (isNull(structures)) {
            return Collections.emptyList();
        }

        for (var structure : structures) {
            if (structure.getTitle().equals(CATALOG_TITLE)) {
                return processChildObjects(structure.getChildObjects());
            }
            List<MenuStructureDto> result = findAndProcessCorrectCatalog(structure.getChildObjects());
            if (!result.isEmpty()) {
                return result;
            }
        }
        return Collections.emptyList();
    }

    private List<MenuStructureDto> processChildObjects(List<MenuStructureDto> childObjects) {
        if (isNull(childObjects)) {
            return Collections.emptyList();
        }

        List<MenuStructureDto> result = new ArrayList<>();

        for (MenuStructureDto child : childObjects) {
            result.add(processChild(child));
        }
        return result;
    }

    private MenuStructureDto processChild(MenuStructureDto child) {
        var methods = findMethodsChild(child.getChildObjects());

        if (methods.isPresent()) {
            List<MenuStructureDto> filteredChildren = parseMethodsFromInput(methods.get().getChildObjects());
            return buildMenuStructureDto(child, filteredChildren, false);
        } else {
            List<MenuStructureDto> nestedChildren = processChildObjects(child.getChildObjects());
            return buildMenuStructureDto(child, nestedChildren, true);
        }
    }

    private MenuStructureDto buildMenuStructureDto(MenuStructureDto parent, List<MenuStructureDto> children, boolean parentLevel) {
        return MenuStructureDto.builder()
                .id(parent.getId())
                .title(parent.getTitle())
                .childObjects(children)
                .parentLevel(parentLevel)
                .build();
    }

    private Optional<MenuStructureDto> findMethodsChild(List<MenuStructureDto> childObjects) {
        return Optional.ofNullable(childObjects)
                .orElse(Collections.emptyList())
                .stream()
                .filter(it -> METHODS_TITLE.equals(it.getTitle()))
                .findFirst();
    }

    private List<MenuStructureDto> parseMethodsFromInput(List<MenuStructureDto> methodsList) {
        return methodsList.stream()
                .filter(subChild -> MEASURE_GROUP_CODES.stream().anyMatch(subChild.getTitle()::contains))
                .map(this::stripTitlePrefix).toList();
    }

    private MenuStructureDto stripTitlePrefix(MenuStructureDto dto) {
        var title = dto.getTitle().replaceFirst("^[^\\s]+\\s+", "");
        return MenuStructureDto.builder()
                .title(title)
                .id(dto.getId())
                .childObjects(dto.getChildObjects())
                .build();
    }
}
