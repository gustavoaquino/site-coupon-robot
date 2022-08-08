package br.com.cupom.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginProperties {

    private String name;
    private String password;

    public LoginProperties(@Value("${socialsoul.login}") String name, @Value("${socialsoul.password}") String password) {
        this.name = name;
        this.password = password;
    }
}
