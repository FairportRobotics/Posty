package org.fairportrobotics.frc.posty.assertions;

public class BooleanAssertion extends BaseAssert<Boolean, Boolean>{

  public BooleanAssertion(Boolean actual){
    super(actual);
  }

  public BooleanAssertion isTrue() {
    if(this.actual != true){
      fail();
    }
    return this;
  }

  public BooleanAssertion isFalse() {
    if(this.actual != false){
      fail();
    }
    return this;
  }

}
