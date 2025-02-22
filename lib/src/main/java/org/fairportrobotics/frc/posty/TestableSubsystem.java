package org.fairportrobotics.frc.posty;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TestableSubsystem extends SubsystemBase{


  public TestableSubsystem(){

  }

  public abstract boolean onPowerOnTest();

  public abstract boolean onBuiltInTest();

  public void runPOST(){

  }

  public void runBIT(){

  }

}
