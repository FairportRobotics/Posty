package org.fairportrobotics.frc.posty;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;

import org.fairportrobotics.frc.posty.exception.PostyTimeoutException;

import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;

public abstract class TestableSubsystem extends SubsystemBase{

  @FunctionalInterface
  public interface TestRunner{
    boolean test() throws PostyTimeoutException;
  }

  @FunctionalInterface
  public interface TestCondition{
    boolean condition();
  }

  private class POSTTest{
    String name;
    TestRunner testRunner;
    Alert testAlert;

    public POSTTest(String testName, TestRunner testRunner, Alert testAlert){
      this.name = testName;
      this.testRunner = testRunner;
      this.testAlert = testAlert;
    }
  }

  private class BITTest{
    String name;
    TestRunner testRunner;
    Alert testAlert;

    public BITTest(String testName, TestRunner testRunner, Alert testAlert){
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

  public void runPOST() throws PostyTimeoutException{

    Boolean postFailed = false;

    try{
        postFailed |= !onPowerOnTest();
    }catch(UnsupportedOperationException ex){
        // Default exception when function is overriden
    }

    for(POSTTest pTest : m_POSTTests){
      boolean testPassed = pTest.testRunner.test();
      if(!testPassed)
        pTest.testAlert.set(true);
      else{
      }
      postFailed |= !testPassed;
    }

    if(postFailed){
      new Alert(getName(), "POST Failed", AlertType.kError).set(true);
    }else{
      new Alert(getName(), "POST Passed", AlertType.kInfo).set(true);
    }

  }

  public void runBIT() throws PostyTimeoutException{

    Boolean bitFailed = false;

    System.out.println("Starting BIT tests for " + this.getName());

    try{
        bitFailed |= !onBuiltInTest();
    }catch(UnsupportedOperationException ex){
        // Default exception when function is overriden
    }

    for(BITTest bTest : m_BITTests){

      System.out.println("Starting BIT test " + bTest.name);

      boolean testPassed = bTest.testRunner.test();
      if(!testPassed)
        bTest.testAlert.set(true);
      bitFailed |= !testPassed;

      System.out.println("Finished BIT test " + bTest.name + " it " + (testPassed ? "passed" : "failed"));
    }

    if(bitFailed){
      new Alert(getName(), "BIT Failed", AlertType.kError).set(true);
    }else{
      new Alert(getName(), "BIT Passed", AlertType.kInfo).set(true);;
    }

    System.out.println("Finsihed BIT test for " + this.getName());

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
  protected void registerPOSTTest(String testName, TestRunner postTest, AlertType alertType){
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
  protected void registerPOSTTest(String testName, TestRunner postTest){
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
  protected void registerBITTest(String testName, TestRunner bitTest, AlertType alertType){
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
  protected void registerBITTest(String testName, TestRunner bitTest){
    m_BITTests.add(new BITTest(testName, bitTest, new Alert(getName() + ":" + testName, AlertType.kError)));
  }

  protected boolean waitForCondition(TestCondition condition){
    return waitForCondition(condition, 10);
  }

  protected boolean waitForCondition(TestCondition condition, int timeout){
    long startTime = System.currentTimeMillis();
    while(System.currentTimeMillis() - startTime <= (timeout * 1000)){
      if(condition.condition()){
        return true;
      }
    }
    return false;
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
