package org.fairportrobotics.frc.posty.assertions;

public class BooleanAssertion extends BaseAssert<Boolean, Boolean>{

  public BooleanAssertion(Boolean actual){
    super(actual);
  }

  public BooleanAssertion isTrue() {
    this.expected = true;
    if(this.actual != this.expected){
      fail();
    }
    return this;
  }

  public BooleanAssertion isFalse() {
    this.expected = false;
    if(this.actual != this.expected){
      fail();
    }
    return this;
  }

}
