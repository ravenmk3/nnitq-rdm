package raven.nnitq.restdm.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Raven
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TERMS_OF_SERVICE_URL = "https://none";
    private static final String API_VERSION = "1.0";

    @Bean
    public static Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("raven.nnitq.restdm.webapp"))
                .paths(PathSelectors.any())
                .build();
    }

    private static ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Restful Data Service")
                .description("")
                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                .version(API_VERSION)
                .build();
    }

}
