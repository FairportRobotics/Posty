package org.fairportrobotics.frc.posty;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;

import org.ejml.simple.UnsupportedOperation;

import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;

public abstract class TestableSubsystem extends SubsystemBase{

  @FunctionalInterface
  public interface POSTTestRunner{
    boolean postTest();
  }

  @FunctionalInterface
  public interface BITTestRunner {
    boolean bitTest();
  }

  private class POSTTest{
    String name;
    POSTTestRunner testRunner;
    Alert testAlert;

    public POSTTest(String testName, POSTTestRunner testRunner, Alert testAlert){
      this.name = testName;
      this.testRunner = testRunner;
      this.testAlert = testAlert;
    }
  }

  private class BITTest{
    String name;
    BITTestRunner testRunner;
    Alert testAlert;

    public BITTest(String testName, BITTestRunner testRunner, Alert testAlert){
      this.name = testName;
      this.testRunner = testRunner;
      this.testAlert = testAlert;
    }
  }

  private ArrayList<POSTTest> m_POSTTests;
  private ArrayList<BITTest> m_BITTests;

  public TestableSubsystem(String subsystemName){
    setName(subsystemName);
    m_POSTTests = new ArrayList<>();
    m_BITTests = new ArrayList<>();

    PostyManager.getInstance().registerSubsystem(this);
  }

  /**
   * This function is called before all POST tests are run. Put any setup logic in here.
   * @return
   */
  public boolean onPowerOnTest() { return true; }

  /**
   * This function is called before all BIT tests are run. Put any setup logic in here.
   * @return
   */
  public boolean onBuiltInTest(){ return true; }

  public void runPOST(){

    Boolean postFailed = false;

    try{
        postFailed |= !onPowerOnTest();
    }catch(UnsupportedOperationException ex){
        // Default exception when function is overriden
    }

    for(POSTTest pTest : m_POSTTests){
      boolean testPassed = pTest.testRunner.postTest();
      if(!testPassed)
        pTest.testAlert.set(true);
      postFailed |= !testPassed;
    }

    if(postFailed){
      Alert alert = new Alert(getName(), "POST Failed", AlertType.kError);
      alert.set(true);
      alert.close();
    }

  }

  public void runBIT(){

    Boolean bitFailed = false;

    try{
        bitFailed |= !onBuiltInTest();
    }catch(UnsupportedOperationException ex){
        // Default exception when function is overriden
    }

    for(BITTest bTest : m_BITTests){
      boolean testPassed = bTest.testRunner.bitTest();
      if(!testPassed)
        bTest.testAlert.set(true);
      bitFailed |= !testPassed;
    }

    if(bitFailed){
      Alert alert = new Alert(getName(), "BIT Failed", AlertType.kError);
      alert.set(true);
      alert.close();
    }

  }

  /**
   * Register a Power on Self Test(POST) test.
   *
   * This test will be run on every power up of the robot.
   *
   * @param testName The name of this test
   * @param postTest The runnable that defines the test. Should return true if the test passes, false if the test fails
   * @param alertType The type of alert to display when the test fails
   */
  protected void registerPOSTTest(String testName, POSTTestRunner postTest, AlertType alertType){
    m_POSTTests.add(new POSTTest(testName, postTest, new Alert(getName() + ":" + testName, alertType)));
  }

  /**
   * Register a Power on Self Test(POST) test.
   *
   * This test will be run on every power up of the robot.
   *
   * @param testName The name of this test
   * @param postTest The runnable that defines the test. Should return true if the test passes, false if the test fails
   */
  protected void registerPOSTTest(String testName, POSTTestRunner postTest){
    m_POSTTests.add(new POSTTest(testName, postTest, new Alert(getName() + ":" + testName, AlertType.kError)));
  }

  /**
   * Register a Built in Test(BIT) test.
   *
   * This test will be run when the robot is enabled in Test mode.
   *
   * @param testName The name of this test
   * @param bitTest The runnable that defines the test. Should return true if the test passes, false if the test fails
   * @param alertType The type of alert to display when the test fails
   */
  protected void registerBITTest(String testName, BITTestRunner bitTest, AlertType alertType){
    m_BITTests.add(new BITTest(testName, bitTest, new Alert(getName() + ":" + testName, alertType)));
  }

  /**
   * Register a Built in Test(BIT) test.
   *
   * This test will be run when the robot is enabled in Test mode.
   *
   * @param testName The name of this test
   * @param bitTest The runnable that defines the test. Should return true if the test passes, false if the test fails
   */
  protected void registerBITTest(String testName, BITTestRunner bitTest){
    m_BITTests.add(new BITTest(testName, bitTest, new Alert(getName() + ":" + testName, AlertType.kError)));
  }

  protected void throwError(String msg){
    Alert alert = new Alert(getName(), msg, AlertType.kError);
    alert.set(true);
    alert.close();
  }

  protected void throwWarning(String msg){
    Alert alert = new Alert(getName(), msg, AlertType.kWarning);
    alert.set(true);
    alert.close();
  }

  protected void throwInfo(String msg){
    Alert alert = new Alert(getName(), msg, AlertType.kInfo);
    alert.set(true);
    alert.close();
  }

}
