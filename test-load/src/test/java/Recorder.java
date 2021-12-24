import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

public class Recorder {

  public static void main(final String[] args) {
    final GatlingPropertiesBuilder props =
        new GatlingPropertiesBuilder()
            .resourcesDirectory(IDEPathHelper.mavenResourcesDirectory.toString())
            .resultsDirectory(IDEPathHelper.resultsDirectory.toString())
            .binariesDirectory(IDEPathHelper.mavenBinariesDirectory.toString());

    Gatling.fromMap(props.build());
  }
}
