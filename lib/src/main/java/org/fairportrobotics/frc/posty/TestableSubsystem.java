package org.fairportrobotics.frc.posty;

import org.fairportrobotics.frc.posty.test.BaseTest;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TestableSubsystem extends SubsystemBase{

  public TestableSubsystem(){
    this.setName(this.getClass().getName());
  }

  protected void registerPOST(BaseTest postTest){
    PostyManager.getInstance().addPOST(getName(), postTest);
  }

  protected void registerBIT(BaseTest bitTest){
    PostyManager.getInstance().addBIT(getName(), bitTest);
  }

}
