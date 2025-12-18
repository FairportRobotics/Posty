package org.fairportrobotics.frc.posty.test;

public class TestResult{
  public enum TestStatus {
    PASSED,
    FAILED,
    SKIPPED
  };

  public String subsystemName = "";
  public String testName = "";
  public TestStatus status = TestStatus.PASSED;
  public String failureReason = "";

  public String toJSON(){
    return String.format("{\"testName\": \"%s\", \"subsystemName\": \"%s\", \"result\": \"%s\", \"failReason\": \"%s\"}", testName, subsystemName, status.toString(), failureReason);
  }
}
