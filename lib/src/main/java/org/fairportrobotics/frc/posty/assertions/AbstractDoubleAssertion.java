package org.fairportrobotics.frc.posty.assertions;

public abstract class AbstractDoubleAssertion<SELF extends AbstractDoubleAssertion<SELF>> extends BaseAssert<SELF, Double> {

    public AbstractDoubleAssertion(Double actual) {
        super(actual);
    }

    public SELF isGreaterThan(Double expected){
      if(this.actual < expected){
        fail(expected);
      }
      return self;
    }

    public SELF isLessThan(Double expected){
      if(this.actual > expected){
        fail(expected);
      }
      return self;
    }
    
}
