
package br.com.erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
      .title("Rest Full Java 19 e Spring Boot 3")
      .version("V1")
      .description("Api Person")
      .termsOfService("https://storedrinks.online/alldrinks")
      .license(new License().name("Apache 2.0").url("https://storedrinks.online/alldrinks")));
              
  }
  
}
