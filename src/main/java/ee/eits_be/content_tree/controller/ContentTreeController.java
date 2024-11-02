package ee.eits_be.content_tree.controller;

import ee.eits_be.content_tree.component.ContentTreeProcessor;
import ee.eits_be.external.dto.MenuStructureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content_tree")
public class ContentTreeController {
    private final ContentTreeProcessor contentTreeProcessor;

    @GetMapping("/{version}")
    public List<MenuStructureDto> getContentTreeByVersion(@PathVariable("version") String version) {
        return contentTreeProcessor.fetchContentTreeByVersion(version);
    }
}
