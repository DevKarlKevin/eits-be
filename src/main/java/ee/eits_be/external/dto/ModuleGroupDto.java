package ee.eits_be.external.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModuleGroupDto {
    private String groupId;
    private String groupTitle;
    private String moduleTitle;
    private List<ModuleDto> modules;
    private List<ModuleGroupDto> moduleSubgroups = new ArrayList<>();
}
