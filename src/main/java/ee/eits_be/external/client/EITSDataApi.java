package ee.eits_be.external.client;

import ee.eits_be.external.dto.CatalogDto;
import ee.eits_be.external.dto.CatalogModuleContentDto;
import ee.eits_be.external.dto.ContentTreeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class EITSDataApi {
    private final RestClient eitsClient;
    private static final String CATALOG_DATA_ENDPOINT = "/catalog/%s";
    private static final String CONTENT_TREE_ENDPOINT = "/content_tree";

    public CatalogDto callGetCatalogEndpoint(String version) {
        var catalogResponse = eitsClient.get()
                .uri(CATALOG_DATA_ENDPOINT.formatted(version))
                .retrieve()
                .toEntity(CatalogDto.class);

        return catalogResponse.getBody();
    }

    public CatalogModuleContentDto callGetCatalogModuleEndpoint(String version, String id) {
        var moduleResponse = eitsClient.get()
                .uri((CATALOG_DATA_ENDPOINT + "/%s").formatted(version, id))
                .retrieve()
                .toEntity(CatalogModuleContentDto.class);

        return moduleResponse.getBody();
    }

    public ContentTreeDto callGetContentTreeEndpoint(String version) {
        var contentTreeResponse = eitsClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(CONTENT_TREE_ENDPOINT)
                        .queryParam("version", version)
                        .build())
                .retrieve()
                .toEntity(ContentTreeDto.class);

        return contentTreeResponse.getBody();
    }
}
