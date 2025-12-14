package org.fairportrobotics.frc.posty.assertions;

public abstract class AbstractFloatAssertion<SELF extends AbstractFloatAssertion<SELF>> extends BaseAssert<SELF, Float> {

    public AbstractFloatAssertion(Float actual) {
        super(actual);
    }

    public SELF isGreaterThan(Float expected){
      if(this.actual < expected){
        fail(expected);
      }
      return self;
    }

    public SELF isLessThan(Float expected){
      if(this.actual > expected){
        fail(expected);
      }
      return self;
    }
    
}
