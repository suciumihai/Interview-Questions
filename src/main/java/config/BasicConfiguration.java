package config;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties("basic")
public class BasicConfiguration {
    //facem clasa asta ca sa nu ai values aiurea prin applicatie, sa fie totul centralizat
}
