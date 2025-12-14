package org.fairportrobotics.frc.posty.assertions;

public abstract class AbstractBooleanAssertion<SELF extends AbstractBooleanAssertion<SELF>> extends BaseAssert<SELF, Boolean> {

    public AbstractBooleanAssertion(Boolean actual) {
        super(actual);
    }
    
    public SELF isTrue() {
      if(this.actual != true){
        fail(true);
      }
      return self;
    }

    public SELF isFalse() {
      if(this.actual != false){
        fail(false);
      }
      return self;
    }

}
