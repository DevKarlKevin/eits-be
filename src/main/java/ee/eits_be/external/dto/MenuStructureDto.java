package ee.eits_be.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuStructureDto {
    @JsonProperty("parent_level")
    private boolean parentLevel;
    @JsonProperty("child_objects")
    private List<MenuStructureDto> childObjects;
    private String title;
    private String id;
    private String content;
}
