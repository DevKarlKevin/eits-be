package ee.eits_be.catalog.mapper;

import ee.eits_be.catalog.comparator.ModuleCodeComparator;
import ee.eits_be.catalog.controller.dto.response.CatalogModuleResponse;
import ee.eits_be.catalog.controller.dto.response.CatalogResponse;
import ee.eits_be.catalog.controller.dto.response.ModuleGroupResponseDto;
import ee.eits_be.external.dto.CatalogDto;
import ee.eits_be.external.dto.MeasureGroupDto;
import ee.eits_be.external.dto.ModuleDto;
import ee.eits_be.external.dto.ModuleGroupDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class CatalogMapper {
    public CatalogResponse mapToResponse(CatalogDto catalog) {
        var modules = catalog.getModuleGroups().stream()
                .map(this::mapGroupToResponseDto)
                .sorted(Comparator.comparing(ModuleGroupResponseDto::getModuleTitle, new ModuleCodeComparator()))
                .toList();
        return new CatalogResponse(modules);
    }

    private ModuleGroupResponseDto mapGroupToResponseDto(ModuleGroupDto group) {
        var modules = group.getModules().stream()
                .sorted(Comparator.comparing(ModuleDto::getModuleCode, new ModuleCodeComparator()))
                .map(module ->
                        ModuleGroupResponseDto.builder()
                                .moduleCode(module.getModuleCode())
                                .moduleTitle(module.getModuleTitle())
                                .moduleId(module.getModuleId())
                                .subModules(new ArrayList<>())
                                .build())
                .toList();

        var subGroups = group.getModuleSubgroups().stream()
                .map(this::mapGroupToResponseDto)
                .sorted(Comparator.comparing(ModuleGroupResponseDto::getModuleTitle, new ModuleCodeComparator()))
                .toList();

        List<ModuleGroupResponseDto> combined = new ArrayList<>();
        combined.addAll(modules);
        combined.addAll(subGroups);
        combined.sort(Comparator.comparing(ModuleGroupResponseDto::getModuleTitle, new ModuleCodeComparator()));

        return ModuleGroupResponseDto.builder()
                .moduleTitle(group.getGroupTitle())
                .subModules(combined)
                .build();
    }

    public CatalogModuleResponse mapToCatalogModuleResponse(List<MeasureGroupDto> moduleContents) {
        return CatalogModuleResponse.builder()
                .measures(moduleContents)
                .build();
    }
}
