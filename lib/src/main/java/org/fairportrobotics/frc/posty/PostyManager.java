package org.fairportrobotics.frc.posty;

import java.util.ArrayList;
import java.util.HashMap;

import org.fairportrobotics.frc.posty.exception.PostyTimeoutException;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class PostyManager {

  private Thread postTestRunnerThread;
  private Thread bitTestRunnerThread;

  private HashMap<String, ArrayList<Command>> postCommands;
  private HashMap<String, ArrayList<Command>> bitCommands;

  public PostyManager(){
    postCommands = new HashMap<>();
    bitCommands = new HashMap<>();
  }

  public boolean addPOST(String subsystemName, Command postCommand){
    if(!postCommands.containsKey(subsystemName)){
      postCommands.put(subsystemName, new ArrayList<>());
    }

    return postCommands.get(subsystemName).add(postCommand);
  }

  public boolean addBIT(String subsystemName, Command bitCommand){
    if(!bitCommands.containsKey(subsystemName)){
      bitCommands.put(subsystemName, new ArrayList<>());
    }

    return bitCommands.get(subsystemName).add(bitCommand);
  }

  public void runAllPOSTs(){
  
    postTestRunnerThread = new Thread(() -> {
      for(ArrayList<Command> subsystemCommands : postCommands.values()){
        for(Command command : subsystemCommands){

          command.initialize();

          do{
            command.execute();
          }while(!command.isFinished());

          command.end(false);

        }
      }
    });

    postTestRunnerThread.start();

  }

  public void runAllBITs(){
    bitTestRunnerThread = new Thread(() -> {
      for(ArrayList<Command> subsystemCommands : bitCommands.values()){
        for(Command command : subsystemCommands){

          command.initialize();

          do{
            command.execute();
          }while(!command.isFinished());

          command.end(false);

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
