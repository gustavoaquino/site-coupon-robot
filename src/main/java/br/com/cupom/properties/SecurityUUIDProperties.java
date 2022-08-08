package br.com.cupom.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SecurityUUIDProperties {

    private String securityUuid;

    public SecurityUUIDProperties(@Value("${api.security.uuid}") String securityUuid) {
        this.securityUuid = securityUuid;
    }
}
