package ee.eits_be.external.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MeasureGroupDto {
    private String groupTitle;
    private String groupCode;
    private String groupType;
    private List<MeasureDto> measures;
}
