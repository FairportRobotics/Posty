package org.fairportrobotics.frc.posty.assertions;

public class IntegerAssertion extends BaseAssert<Integer, Integer> {

  public IntegerAssertion(Integer actual){
    super(actual);
  }

  public IntegerAssertion isGreaterThan(Integer expected){
    this.expected = expected;
    if(this.actual < this.expected){
      fail();
    }
    return this;
  }

  public IntegerAssertion isLessThan(Integer expected){
    this.expected = expected;
    if(this.actual > this.expected){
      fail();
    }
    return this;
  }

}
