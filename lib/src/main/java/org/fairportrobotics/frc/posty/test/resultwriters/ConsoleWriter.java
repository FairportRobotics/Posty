package org.fairportrobotics.frc.posty.test.resultwriters;

import org.fairportrobotics.frc.posty.test.TestResult;

public class ConsoleWriter extends BaseResultWriter{

  @Override
  public void writePOSTResults(TestResult[] results) {
    printTestResults(results);
  }

  @Override
  public void writeBITResults(TestResult[] results) {
    printTestResults(results);
  }

  private void printTestResults(TestResult[] results) {

    System.out.format("|-----------------------------------------------------------------------|\n", "");
    System.out.format("|-------------------------------Test Results----------------------------|\n", "");
    System.out.format("|-----------------------------------------------------------------------|\n", "");
    System.out.format("|%-60s|%-10s|\n", "Test Name", "Status");
    System.out.format("|-----------------------------------------------------------------------|\n", "");
    for (TestResult res : results) {
      String statusStr = res.status.toString();
      if (res.status == TestResult.TestStatus.FAILED) {
        System.out.format("|%-60s|%-10s|\n", res.testName, statusStr);
        System.out.format("|└─ %-68s|\n", res.failureReason);
      }else
      {
        System.out.format("|%-60s|%-10s|\n", res.testName, statusStr);
      }
    }
    System.out.format("|-----------------------------------------------------------------------|\n", "");
  }

}
