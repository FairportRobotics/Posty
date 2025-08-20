package org.fairportrobotics.frc.posty;

import org.fairportrobotics.frc.posty.test.PostTest;
import org.fairportrobotics.frc.posty.exceptions.AssertFailureException;
import org.fairportrobotics.frc.posty.test.BitTest;

import java.util.ArrayList;
import java.lang.reflect.Method;

public class PostyManager {

  private Thread postTestRunnerThread;
  private Thread bitTestRunnerThread;

  private ArrayList<TestableSubsystem> mSubsystems = new ArrayList<>();

  public PostyManager() {
  }

  public void registerSubsystem(TestableSubsystem subSys) {
    this.mSubsystems.add(subSys);
  }

  public void runAllPOSTs() {

    postTestRunnerThread = new Thread(() -> {

      ArrayList<TestResult> testResults = new ArrayList<>();

      for (TestableSubsystem subSys : mSubsystems) {
        Class<?> clazz = subSys.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
          if (method.isAnnotationPresent(PostTest.class)) {
            PostTest postTestAnno = method.getAnnotation(PostTest.class);
            TestResult res = new TestResult();
            res.status = TestResult.TestStatus.PASSED;

            String testName = postTestAnno.name().isEmpty() ? method.getName() : postTestAnno.name();
            res.testName = subSys.getSubsystem() + "_" + testName;

            if (!postTestAnno.enabled()){
              res.status = TestResult.TestStatus.SKIPPED;
              testResults.add(res);
              continue; // Skip test if disabled
            }

            method.setAccessible(true);
            try {
              method.invoke(subSys);
            } catch (Exception ex) {
              // Failed to execute test
              if (ex.getCause() instanceof AssertFailureException) {
                // Test assertion failure
                AssertFailureException assertExcp = (AssertFailureException)ex.getCause();

                res.status = TestResult.TestStatus.FAILED;
                res.failureReason = assertExcp.getReason();
              }
            }
            testResults.add(res);
          }
        }
      }

      printTestResults(testResults);

    });

    postTestRunnerThread.start();

  }

  public void runAllBITs() {
    bitTestRunnerThread = new Thread(() -> {

      ArrayList<TestResult> testResults = new ArrayList<>();

      for (TestableSubsystem subSys : mSubsystems) {
        Class<?> clazz = subSys.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
          if (method.isAnnotationPresent(BitTest.class)) {
            BitTest bitTestAnno = method.getAnnotation(BitTest.class);
            TestResult res = new TestResult();
            res.status = TestResult.TestStatus.PASSED;

            String testName = bitTestAnno.name().isEmpty() ? method.getName() : bitTestAnno.name();
            res.testName = subSys.getSubsystem() + "_" + testName;

            if (!bitTestAnno.enabled()){
              res.status = TestResult.TestStatus.SKIPPED;
              testResults.add(res);
              continue; // Skip test if disabled
            }

            method.setAccessible(true);
            try {
              method.invoke(subSys);
            } catch (Exception ex) {
              if(ex.getCause() instanceof AssertFailureException) {
                AssertFailureException assertExcp = (AssertFailureException)ex.getCause();

                res.status = TestResult.TestStatus.FAILED;
                res.failureReason = assertExcp.getReason();
              }
            }
            testResults.add(res);
          }
        }
      }
    });

    bitTestRunnerThread.start();
  }

  private void printTestResults(ArrayList<TestResult> results){

    System.out.println("=====================================");
    System.out.println("============ Test Results ===========");
    System.out.println("=====================================");
    System.out.format("|%32s|%10s|\n", "Test Name", "Status");
    System.out.println("|=====================================|");

    for(TestResult res : results ){
      String statusStr = res.status.toString();
      System.out.format("|%32s|%10s|\n", res.testName, statusStr);

      if(res.status == TestResult.TestStatus.FAILED){
        System.out.format("|%43s|\n", res.failureReason);
      }
      System.out.format("|-------------------------------------------|\n", "");
    }

    System.out.println("=====================================");
    System.out.println("=====================================");

  }

  private static PostyManager INSTANCE;

  public static PostyManager getInstance() {
    if (INSTANCE == null)
      INSTANCE = new PostyManager();

    return INSTANCE;
  }

  private class TestResult{
    public enum TestStatus{
      PASSED,
      FAILED,
      SKIPPED
    };

    String testName = "";
    TestStatus status = null;
    String failureReason = "";
  }

}
