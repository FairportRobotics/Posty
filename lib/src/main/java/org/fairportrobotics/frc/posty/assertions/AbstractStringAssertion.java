package org.fairportrobotics.frc.posty.assertions;

public abstract class AbstractStringAssertion<SELF extends AbstractStringAssertion<SELF>> extends BaseAssert<SELF, String> {

    public AbstractStringAssertion(String actual) {
        super(actual);
    }

    public SELF startsWith(String startsWithStr){
      if(!this.actual.startsWith(startsWithStr)){
        failWithMessage(String.format("expected <%s> to start with <%s>", this.actual, startsWithStr));
      }
      return self;
    }

    public SELF contains(String containsStr){
      if(!this.actual.contains(containsStr)){
        failWithMessage(String.format("expected <%s> to contain <%s>", this.actual, containsStr));
      }
      return self;
    }

    public SELF endWith(String endsWithStr){
      if(!this.actual.endsWith(endsWithStr)){
        failWithMessage(String.format("expected <%s> to end with <%s>", this.actual, endsWithStr));
      }
      return self;
    }
    
}
