package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
//                Path of the feature file
                features =".//Features/Customers.feature",
//                stepDefinitions package name
                glue="stepDefinitions",
//                dryRun = true,
                dryRun = false,
                monochrome = true,
                plugin ={"pretty","html:test-output"}

        )
public class TestRunner {

}
