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
import org.fairportrobotics.frc.posty.TestableSubsystem;
import org.fairportrobotics.frc.posty.test.PostTest;
import org.fairportrobotics.frc.posty.test.BitTest;

public class ExampleSubsystem extends TestableSubsystem{

    public ExampleSubsystem(){
        super("ExampleSubsystem"); // Setting subsystem name

    }

    @PostTest
    public void myPostTest() {

    }

    @BitTest
    public void myBitTest() {

    }

}
```


