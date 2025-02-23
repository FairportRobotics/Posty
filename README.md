# Posty
Posty is a Java library for building and running hardware tests for FRC Robot projects.

Posty has two types of tests. Power on Self Tests and Built in Tests.

## POST Tests
POST tests are intended to be quick checks to verify all hardware components are present. These tests are run every time the robot powers on.

## BIT Tests
BIT tests are intended to be in-depth system checks for each subsystem. These tests are intended to be run on-demand. We typically run them all when the robot is enabled in Test mode.


# How to use
Only subsystems that extend `TestableSubsystem` will implement this functionality. `TestableSubsystem` has all the same functionality as `SubsystemBase` and is a drop-in replacement.

Example subsystem:

```Java
import org.fairportrobotics.posty.TestableSubsystem;

public class ExampleSubsystem extends TestableSubsystem{

    public ExampleSubsystem(){
        super("ExampleSubsystem"); // Setting subsystem name

    }

}
```

## Registering tests

I recommend registering tests in the constructor of the subsystem. To do so, use `registerPOSTTest()` and `registerBITTest()`

### POST Example
```Java
registerPOSTTest("Name of test", () -> { return true; });
```

This registers a test with a name of `Name of test` and the test will always pass. If the lambda function returns false the test will fail.

### BIT Example
```Java
registerBITTest("Name of test", () -> { return true; });
```

This registers a test with a name of `Name of test` and the test will always pass. If the lambda function returns false the test will fail.

# Including in FRC Project
To include this library in a Java FRC Robot project. 

# Building
The project can be built and published to the local Maven repository using `./gradlew pubLocal`

## Updating version
The version tag can be found in `gradle.properties`. When updating the version match the year to the current year, and increment the major and minor version as needed.
