package science.monke;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import science.monke.enitity.Config;

import java.io.IOException;
import java.io.InputStream;

public class ConfigLoader {

  private ConfigLoader() {}

  public static Config load() throws IOException {
    final String environment = System.getProperty("environment");
    return load(environment);
  }

  public static Config load(final String environment) throws IOException {
    final String configName = String.format("/%s.yaml", environment);
    final InputStream resourceAsStream = ConfigLoader.class.getResourceAsStream(configName);
    final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    return objectMapper.readValue(resourceAsStream, Config.class);
  }
}
