package org.fairportrobotics.frc.posty.assertions;

public abstract class AbstractIntegerAssertion<SELF extends AbstractIntegerAssertion<SELF>> extends BaseAssert<SELF, Integer> {

    public AbstractIntegerAssertion(Integer actual) {
        super(actual);
    }

    public SELF isGreaterThan(Integer expected){
      if(this.actual < expected){
        fail(expected);
      }
      return self;
    }

    public SELF isLessThan(Integer expected){
      if(this.actual > expected){
        fail(expected);
      }
      return self;
    }
    
}
