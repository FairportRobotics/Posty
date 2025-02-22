package org.fairportrobotics.frc.posty;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;

public abstract class TestableSubsystem extends SubsystemBase{

  @FunctionalInterface
  public interface POSTTest{
    boolean postTest();
  }

  @FunctionalInterface
  public interface BITTest {
    boolean bitTest();
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
   * 
   * @return
   */
  public abstract boolean onPowerOnTest();

  /**
   * 
   * @return
   */
  public abstract boolean onBuiltInTest();

  public void runPOST(){

    Boolean postFailed = false;

    postFailed |= onPowerOnTest();

    for(POSTTest pTest : m_POSTTests){
      postFailed |= pTest.postTest();
    }

    if(postFailed){
      Alert alert = new Alert(getName(), "POST Failed", AlertType.kError);
      alert.set(true);
      alert.close();
    }

  }

  public void runBIT(){

    Boolean bitFailed = false;

    for(BITTest bTest : m_BITTests){
      bitFailed |= bTest.bitTest();
    }

    if(bitFailed){
      Alert alert = new Alert(getName(), "BIT Failed", AlertType.kError);
      alert.set(true);
      alert.close();
    }

  }

  /**
   * 
   * @param pTest
   */
  protected void registerPOSTTest(POSTTest pTest){
    m_POSTTests.add(pTest);
  }

  /**
   * 
   * @param bTest
   */
  protected void registerBITTest(BITTest bTest){
    m_BITTests.add(bTest);
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
