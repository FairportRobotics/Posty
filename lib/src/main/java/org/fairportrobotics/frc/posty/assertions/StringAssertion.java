package org.fairportrobotics.frc.posty.assertions;

public class StringAssertion extends BaseAssert<String, String> {

  public StringAssertion(String actual){
    super(actual);
  }

  public StringAssertion startsWith(String startsWithStr){
    if(!this.actual.startsWith(startsWithStr)){
      fail();
    }
    return this;
  }

  public StringAssertion contains(String containsStr){
    if(!this.actual.contains(containsStr)){
      fail();
    }
    return this;
  }

  public StringAssertion endWith(String endsWithStr){
    if(!this.actual.endsWith(endsWithStr)){
      fail();
    }
    return this;
  }

}
