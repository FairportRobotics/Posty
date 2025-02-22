package org.fairportrobotics.frc.posty;

import java.util.ArrayList;

public class PostyManager {

  ArrayList<TestableSubsystem> m_registeredSubsystem;

  public PostyManager(){
    m_registeredSubsystem = new ArrayList<TestableSubsystem>();
  }

  public void registerSubsystem(TestableSubsystem subsystem){
    m_registeredSubsystem.add(subsystem);
  }

  public void runAllPOSTs(){

    boolean postFailed = false;

    for(TestableSubsystem subsystem : m_registeredSubsystem){
      subsystem.runPOST();
    }

    if(postFailed){
      // DO ALERT
    }

  }

  public void runAllBITs(){

    boolean bitFailed = false;

    for(TestableSubsystem subsystem : m_registeredSubsystem){
      subsystem.runBIT();
    }

    if(bitFailed){
      // DO ALERT
    }

  }


  private static PostyManager INSTANCE;

  public static PostyManager getInstance(){
    if(INSTANCE == null)
      INSTANCE = new PostyManager();

    return INSTANCE;
  }

}
