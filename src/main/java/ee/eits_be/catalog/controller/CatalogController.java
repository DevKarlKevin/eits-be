package ee.eits_be.catalog.controller;

import ee.eits_be.catalog.component.CatalogProcessor;
import ee.eits_be.catalog.controller.dto.response.CatalogModuleResponse;
import ee.eits_be.catalog.controller.dto.response.CatalogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {
    private final CatalogProcessor catalogProcessor;

    @GetMapping
    public CatalogResponse getCatalogByVersion(@RequestParam("version") String version) {
        return catalogProcessor.fetchCatalogByVersion(version);
    }

    @GetMapping("/{version}/{id}")
    public CatalogModuleResponse getCatalogContentsById(@PathVariable("version") String version,
                                                        @PathVariable("id") String id) {
        return catalogProcessor.fetchCatalogModuleContents(version, id);
    }
}
