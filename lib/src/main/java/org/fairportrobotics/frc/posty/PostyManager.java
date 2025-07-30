package org.fairportrobotics.frc.posty;

import org.fairportrobotics.frc.posty.test.PostTest;
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

      for (TestableSubsystem subSys : mSubsystems) {
        Class<?> clazz = subSys.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
          if (method.isAnnotationPresent(PostTest.class)) {
            PostTest postTestAnno = method.getAnnotation(PostTest.class);

            if (!postTestAnno.enabled())
              continue; // Skip test if disabled

            String testName = postTestAnno.name().isEmpty() ? method.getName() : postTestAnno.name();

            method.setAccessible(true);
            try {
              method.invoke(subSys);
            } catch (Exception ex) {
              // Failed to execute test
              ex.printStackTrace();
            }
          }
        }
      }

    });

    postTestRunnerThread.start();

  }

  public void runAllBITs() {
    bitTestRunnerThread = new Thread(() -> {

      for (TestableSubsystem subSys : mSubsystems) {
        Class<?> clazz = subSys.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
          if (method.isAnnotationPresent(BitTest.class)) {
            BitTest bitTestAnno = method.getAnnotation(BitTest.class);

            if (!bitTestAnno.enabled())
              continue; // Skip test if disabled

            String testName = bitTestAnno.name().isEmpty() ? method.getName() : bitTestAnno.name();

            method.setAccessible(true);
            try {
              method.invoke(subSys);
            } catch (Exception ex) {
              // Failed to execute test
            }
          }
        }
      }
    });

    bitTestRunnerThread.start();
  }

  private static PostyManager INSTANCE;

  public static PostyManager getInstance() {
    if (INSTANCE == null)
      INSTANCE = new PostyManager();

    return INSTANCE;
  }

}
