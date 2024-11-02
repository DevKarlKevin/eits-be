package ee.eits_be.external;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "eits.api")
public class EITSApiConfiguration {
    private String host;
}
