package ee.eits_be.external.dto;

import lombok.Data;

import java.util.List;

@Data
public class CatalogDto {
    private String version;
    private String lang;
    private List<ModuleGroupDto> moduleGroups;
}
