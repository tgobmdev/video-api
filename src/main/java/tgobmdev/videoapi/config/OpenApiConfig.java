package tgobmdev.videoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${info.application.name}")
  private String applicationName;

  @Value("${info.application.description}")
  private String applicationDescription;

  @Value("${info.application.version}")
  private String applicationVersion;

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().info(getInfo());
  }

  @Bean
  public GroupedOpenApi groupedOpenApi() {
    return GroupedOpenApi.builder()
        .group("api")
        .pathsToMatch("/**")
        .build();
  }

  private Info getInfo() {
    return new Info().title(applicationName)
        .description(applicationDescription)
        .version(applicationVersion);
  }
}