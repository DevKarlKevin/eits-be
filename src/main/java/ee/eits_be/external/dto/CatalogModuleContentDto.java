package ee.eits_be.external.dto;

import lombok.Data;

import java.util.List;

@Data
public class CatalogModuleContentDto {
    private List<MeasureGroupDto> measureDetails;
}
