package tw.hyin.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import tw.hyin.java.utils.config.SwaggerCreater;

/**
 * @author H-yin on 2022.
 * @Description ./swagger-ui/#/
 */
@Configuration
@EnableOpenApi
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return SwaggerCreater.createSwaggerDefault("message server", "1.0");
    }

}