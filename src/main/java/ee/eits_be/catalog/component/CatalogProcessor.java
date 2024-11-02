package ee.eits_be.catalog.component;

import ee.eits_be.catalog.controller.dto.response.CatalogModuleResponse;
import ee.eits_be.catalog.controller.dto.response.CatalogResponse;
import ee.eits_be.catalog.mapper.CatalogMapper;
import ee.eits_be.external.dto.MeasureGroupDto;
import ee.eits_be.external.service.EITSProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogProcessor {
    private final EITSProxyService proxyService;
    private final CatalogMapper catalogMapper;
    private static final List<String> MEASURE_GROUP_CODES = List.of("3.2", "3.3", "3.4");

    public CatalogResponse fetchCatalogByVersion(String version) {
        var catalog = proxyService.fetchCatalogByVersion(version);
        return catalogMapper.mapToResponse(catalog);
    }

    public CatalogModuleResponse fetchCatalogModuleContents(String version, String id) {
        var moduleContents = proxyService.fetchCatalogModuleByVersionAndId(version, id);
        var measures = moduleContents.getMeasureDetails().stream()
                .filter(it -> MEASURE_GROUP_CODES.contains(it.getGroupCode()))
                .map(it -> {
                    String groupType = it.getGroupTitle().replaceFirst("^[^\\s]+\\s+", "");
                    return MeasureGroupDto.builder()
                            .groupType(groupType)
                            .groupCode(it.getGroupCode())
                            .groupTitle(it.getGroupTitle())
                            .measures(it.getMeasures())
                            .build();
                })
                .toList();
        return catalogMapper.mapToCatalogModuleResponse(measures);
    }
}
