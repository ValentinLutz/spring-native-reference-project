import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IDEPathHelper {

  static final Path mavenSourcesDirectory;
  static final Path mavenResourcesDirectory;
  static final Path mavenBinariesDirectory;
  static final Path resultsDirectory;
  static final Path recorderConfigFile;

  static {
    try {
      final Path projectRootDir =
          Paths.get(IDEPathHelper.class.getClassLoader().getResource("gatling.conf").toURI())
              .getParent()
              .getParent()
              .getParent();
      final Path mavenTargetDirectory = projectRootDir.resolve("target");
      final Path mavenSrcTestDirectory = projectRootDir.resolve("src").resolve("test");

      mavenSourcesDirectory = mavenSrcTestDirectory.resolve("java");
      mavenResourcesDirectory = mavenSrcTestDirectory.resolve("resources");
      mavenBinariesDirectory = mavenTargetDirectory.resolve("test-classes");
      resultsDirectory = mavenTargetDirectory.resolve("gatling");
      recorderConfigFile = mavenResourcesDirectory.resolve("recorder.conf");
    } catch (final URISyntaxException e) {
      throw new ExceptionInInitializerError(e);
    }
  }
}
