package org.fairportrobotics.frc.posty;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TestableSubsystem extends SubsystemBase{

  public TestableSubsystem(){
    this.setName(this.getClass().getName());
    PostyManager.getInstance().registerSubsystem(this);
  }

}
