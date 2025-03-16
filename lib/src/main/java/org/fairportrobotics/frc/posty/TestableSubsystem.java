package org.fairportrobotics.frc.posty;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TestableSubsystem extends SubsystemBase{

  public TestableSubsystem(){
    this.setName(this.getClass().getName());
  }

  protected void registerPOST(Command postCommand){
  }

  protected void registerBIT(Command bitCommand){

  }

}
