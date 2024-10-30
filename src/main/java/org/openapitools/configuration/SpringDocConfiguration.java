package org.openapitools.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

  @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
  OpenAPI apiInfo() {
    return new OpenAPI()
        .info(
            new Info()
                .title("GroupGrub")
                .description("API for managing group food orders.")
                .version("1.0.0"));
  }
}
