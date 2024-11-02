package ee.eits_be.external.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContentTreeDto {
    private ArticleDto article;

    @Data
    public static class ArticleDto {
        private List<MenuStructureDto> structure;
    }
}
