package ee.eits_be.external.service;

import ee.eits_be.external.client.EITSDataApi;
import ee.eits_be.external.dto.CatalogDto;
import ee.eits_be.external.dto.CatalogModuleContentDto;
import ee.eits_be.external.dto.ContentTreeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EITSProxyService {
    private final EITSDataApi dataApi;

    public CatalogDto fetchCatalogByVersion(String version) {
        return dataApi.callGetCatalogEndpoint(version);
    }

    public CatalogModuleContentDto fetchCatalogModuleByVersionAndId(String version, String id) {
        return dataApi.callGetCatalogModuleEndpoint(version, id);
    }

    public ContentTreeDto fetchContentTreeByVersion(String version) {
        return dataApi.callGetContentTreeEndpoint(version);
    }
}
