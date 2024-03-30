package tgobmdev.videoapi.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OpenApiConfigTest {

  private String applicationName;
  private String applicationDescription;
  private String applicationVersion;

  @InjectMocks
  private OpenApiConfig openApiConfig;

  @BeforeEach
  void setUp() {
    applicationName = "Test Application";
    applicationDescription = "Description of Test Application";
    applicationVersion = "1.0.0";

    setField(openApiConfig, "applicationName", applicationName);
    setField(openApiConfig, "applicationDescription", applicationDescription);
    setField(openApiConfig, "applicationVersion", applicationVersion);
  }

  @Test
  void givenApplicationValues_whenOpenAPI_thenReturnsCorrectInfo() {
    OpenAPI openAPI = openApiConfig.openAPI();
    Info info = openAPI.getInfo();

    assertEquals(applicationName, info.getTitle());
    assertEquals(applicationDescription, info.getDescription());
    assertEquals(applicationVersion, info.getVersion());
  }

  @Test
  void givenApplicationValues_whenGroupedOpenApi_thenReturnsCorrectInfo() {
    List<String> pathsToMatch = Collections.singletonList("/**");

    assertEquals("api", openApiConfig.groupedOpenApi().getGroup());
    assertEquals(pathsToMatch, openApiConfig.groupedOpenApi().getPathsToMatch());
  }
}