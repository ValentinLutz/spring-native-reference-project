package science.monke.enitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Config {
  @JsonProperty("base-uri")
  private String baseUri;

  @JsonProperty("base-path")
  private String basePath;
}
