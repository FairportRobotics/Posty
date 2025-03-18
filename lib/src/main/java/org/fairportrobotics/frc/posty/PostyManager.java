package org.fairportrobotics.frc.posty;

import java.util.ArrayList;
import java.util.HashMap;

import org.fairportrobotics.frc.posty.test.BaseTest;

public class PostyManager {

  private Thread postTestRunnerThread;
  private Thread bitTestRunnerThread;

  private HashMap<String, ArrayList<BaseTest>> postCommands;
  private HashMap<String, ArrayList<BaseTest>> bitCommands;

  public PostyManager(){
    postCommands = new HashMap<>();
    bitCommands = new HashMap<>();
  }

  public boolean addPOST(TestableSubsystem subsystem, BaseTest postTest){
    return addPOST(subsystem.getName(), postTest);
  }

  public boolean addPOST(String subsystemName, BaseTest postTest){
    if(!postCommands.containsKey(subsystemName)){
      postCommands.put(subsystemName, new ArrayList<>());
    }

    return postCommands.get(subsystemName).add(postTest);
  }

  public boolean addBIT(TestableSubsystem subsystem, BaseTest bitTest){
    return addBIT(subsystem.getName(), bitTest);
  }

  public boolean addBIT(String subsystemName, BaseTest bitTest){
    if(!bitCommands.containsKey(subsystemName)){
      bitCommands.put(subsystemName, new ArrayList<>());
    }

    return bitCommands.get(subsystemName).add(bitTest);
  }

  public void runAllPOSTs(){
  
    postTestRunnerThread = new Thread(() -> {
      for(ArrayList<BaseTest> subsystemTests : postCommands.values()){
        for(BaseTest test : subsystemTests){

          test.initialize();

          do{
            test.execute();
          }while(!test.isFinished());

          test.end();

        }
      }
    });

    postTestRunnerThread.start();

  }

  public void runAllBITs(){
    bitTestRunnerThread = new Thread(() -> {
      for(ArrayList<BaseTest> subsystemTests : bitCommands.values()){
        for(BaseTest test : subsystemTests){

          test.initialize();

          do{
            test.execute();
          }while(!test.isFinished());

          test.end();

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
