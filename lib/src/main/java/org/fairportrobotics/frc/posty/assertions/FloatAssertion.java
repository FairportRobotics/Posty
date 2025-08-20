package org.fairportrobotics.frc.posty.assertions;

public class FloatAssertion extends BaseAssert<Float, Float>{

  public FloatAssertion(Float actual){
    super(actual);
  }

  public FloatAssertion isGreaterThan(Float expected){
    if(this.actual < expected){
      fail();
    }
    return this;
  }

  public FloatAssertion isLessThan(Float expected){
    if(this.actual > expected){
      fail();
    }
    return this;
  }

}
