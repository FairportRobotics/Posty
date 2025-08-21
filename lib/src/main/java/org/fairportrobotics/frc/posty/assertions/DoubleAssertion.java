package org.fairportrobotics.frc.posty.assertions;

public class DoubleAssertion extends BaseAssert<Double, Double> {

  public DoubleAssertion(Double actual){
    super(actual);
  }

  public DoubleAssertion isGreaterThan(Double expected){
    this.expected = expected;
    if(this.actual < this.expected){
      fail();
    }
    return this;
  }

  public DoubleAssertion isLessThan(Double expected){
    this.expected = expected;
    if(this.actual > this.expected){
      fail();
    }
    return this;
  }

}
