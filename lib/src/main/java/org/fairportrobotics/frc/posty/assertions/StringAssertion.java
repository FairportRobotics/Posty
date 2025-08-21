package org.fairportrobotics.frc.posty.assertions;

public class StringAssertion extends BaseAssert<String, String> {

  public StringAssertion(String actual){
    super(actual);
  }

  public StringAssertion startsWith(String startsWithStr){
    if(!this.actual.startsWith(startsWithStr)){
      failWithMessage(String.format("expected <%s> to start with <%s>", this.actual, startsWithStr));
    }
    return this;
  }

  public StringAssertion contains(String containsStr){
    if(!this.actual.contains(containsStr)){
      failWithMessage(String.format("expected <%s> to contain <%s>", this.actual, containsStr));
    }
    return this;
  }

  public StringAssertion endWith(String endsWithStr){
    if(!this.actual.endsWith(endsWithStr)){
      failWithMessage(String.format("expected <%s> to end with <%s>", this.actual, endsWithStr));
    }
    return this;
  }

}
