package tr.com.argela.whatsapp.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("tr.com.argela.whatsapp"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Whatsapp",
                "Whatsapp Swagger",
                "V1.0",
                "TERMS OF SERVICE URL",
                new Contact("Onurcan Pekgöz", "https://www.linkedin.com/in/onurcan-pekg%C3%B6z-397060144/",
                        "onurcanaylin@gmail.com"),
                "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }

}
