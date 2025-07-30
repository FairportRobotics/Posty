package org.fairportrobotics.frc.posty;

import org.assertj.core.api.WithAssertions;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class TestableSubsystem extends SubsystemBase implements WithAssertions{

  public TestableSubsystem(){
    this.setName(this.getClass().getName());
    PostyManager.getInstance().registerSubsystem(this);
  }

}
