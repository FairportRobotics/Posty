package org.fairportrobotics.frc.posty;

import java.util.ArrayList;

import org.fairportrobotics.frc.posty.exception.PostyTimeoutException;

public class PostyManager {

  private Thread postTestRunnerThread;
  private Thread bitTestRunnerThread;

  private ArrayList<TestableSubsystem> m_registeredSubsystem;

  public PostyManager(){
    m_registeredSubsystem = new ArrayList<TestableSubsystem>();
  }

  public void registerSubsystem(TestableSubsystem subsystem){
    m_registeredSubsystem.add(subsystem);
  }

  public void runAllPOSTs(){
  
    postTestRunnerThread = new Thread(() -> {
      for(TestableSubsystem subsystem : m_registeredSubsystem){
        try{
          subsystem.runPOST();
        }catch(PostyTimeoutException ex){

        }
      }
    });

    postTestRunnerThread.start();

  }

  public void runAllBITs(){
    bitTestRunnerThread = new Thread(() -> {
      for(TestableSubsystem subsystem : m_registeredSubsystem){
        try{
          subsystem.runBIT();
        }catch(PostyTimeoutException ex){
          ex.printStackTrace();
        }
      }
    });

    bitTestRunnerThread.start();
  }

  private static PostyManager INSTANCE;

  public static PostyManager getInstance(){
    if(INSTANCE == null)
      INSTANCE = new PostyManager();

    return INSTANCE;
  }

}
