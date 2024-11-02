package ee.eits_be.external.dto;

import lombok.Data;

@Data
public class ModuleDto {
    private String moduleId;
    private String groupId;
    private String moduleTitle;
    private String moduleCode;
}
