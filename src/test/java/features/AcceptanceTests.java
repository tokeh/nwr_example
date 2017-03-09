package features;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    plugin = "pretty",
    tags = {"@happy_path"}
)
public class AcceptanceTests { }
