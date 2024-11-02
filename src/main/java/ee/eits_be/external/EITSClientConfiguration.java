package ee.eits_be.external;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class EITSClientConfiguration {

    @Bean("eitsClient")
    public RestClient restClient(EITSApiConfiguration apiConfiguration) {
        return RestClient.builder()
                .baseUrl(apiConfiguration.getHost())
                .build();
    }
}
