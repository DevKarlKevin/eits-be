package ee.eits_be.catalog.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModuleGroupResponseDto {
    private String moduleCode;
    private String moduleTitle;
    private String moduleId;
    private List<ModuleGroupResponseDto> subModules;
}
