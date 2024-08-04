package tgobmdev.videoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.util.List;
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
  public List<GroupedOpenApi> groupedOpenApis() {
    GroupedOpenApi categories = GroupedOpenApi.builder()
        .group("categories")
        .pathsToMatch("/categories/**")
        .build();
    GroupedOpenApi videos = GroupedOpenApi.builder()
        .group("videos")
        .pathsToMatch("/videos/**")
        .build();
    return List.of(categories, videos);
  }

  private Info getInfo() {
    return new Info().title(applicationName)
        .description(applicationDescription)
        .version(applicationVersion);
  }
}