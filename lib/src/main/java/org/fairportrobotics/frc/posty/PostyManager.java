package org.fairportrobotics.frc.posty;

import java.util.ArrayList;

public class PostyManager {

  private ArrayList<TestableSubsystem> m_registeredSubsystem;

  public PostyManager(){
    m_registeredSubsystem = new ArrayList<TestableSubsystem>();
  }

  public void registerSubsystem(TestableSubsystem subsystem){
    m_registeredSubsystem.add(subsystem);
  }

  public void runAllPOSTs(){
    for(TestableSubsystem subsystem : m_registeredSubsystem){
      subsystem.runPOST();
    }
  }

  public void runAllBITs(){
    for(TestableSubsystem subsystem : m_registeredSubsystem){
      subsystem.runBIT();
    }
  }

  private static PostyManager INSTANCE;

  public static PostyManager getInstance(){
    if(INSTANCE == null)
      INSTANCE = new PostyManager();

    return INSTANCE;
  }

}
