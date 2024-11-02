package ee.eits_be.catalog.controller.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CatalogResponse(List<ModuleGroupResponseDto> modules) {
}
