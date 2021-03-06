package ro.kronsoft.training.config;

import java.util.Collections;
import java.util.List;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String BASE_CONTROLLER_PACKAGE = "ro.kronsoft.training.controller";
   
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(BASE_CONTROLLER_PACKAGE))
                    .paths(PathSelectors.any())
                    .build()
                    .securitySchemes(Collections.singletonList(new BasicAuth("basicAuth")))
                .securityContexts(Collections.singletonList(securityContext()));
    }
 
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth()).
                forPaths(PathSelectors.any())
                .build();
    }
 
    private List<SecurityReference> defaultAuth() {
        return Collections.singletonList((new SecurityReference("basicAuth",
                new AuthorizationScope[] { new AuthorizationScope("global", "accessEverything") })));
    }
 
}