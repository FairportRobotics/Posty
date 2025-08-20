package org.fairportrobotics.frc.posty.assertions;

public class DoubleAssertion extends BaseAssert<Double, Double> {

  public DoubleAssertion(Double actual){
    super(actual);
  }

  public DoubleAssertion isGreaterThan(Double expected){
    if(this.actual < expected){
      fail();
    }
    return this;
  }

  public DoubleAssertion isLessThan(Double expected){
    if(this.actual > expected){
      fail();
    }
    return this;
  }

}
