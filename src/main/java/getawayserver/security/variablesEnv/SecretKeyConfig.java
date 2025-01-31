package getawayserver.security.variablesEnv;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwtkey")
public class SecretKeyConfig {
    private String SECRET_KEY;

    public SecretKeyConfig() {
    }

    public SecretKeyConfig(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }
}
