package ee.eits_be.catalog.controller.dto.response;

import ee.eits_be.external.dto.MeasureGroupDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CatalogModuleResponse {
    private List<MeasureGroupDto> measures;
}
