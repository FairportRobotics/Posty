package org.fairportrobotics.frc.posty.assertions;

public class BooleanAssertion extends AbstractBooleanAssertion<BooleanAssertion>{

  public BooleanAssertion(Boolean actual){
    super(actual);
  }

  @Override
  public String toString() {
    return actual.toString();
  }

}