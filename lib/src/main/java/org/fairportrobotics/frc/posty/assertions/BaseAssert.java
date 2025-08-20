package org.fairportrobotics.frc.posty.assertions;

import org.fairportrobotics.frc.posty.exceptions.AssertFailureException;

public abstract class BaseAssert<EXPECTED, ACTUAL> {

  protected EXPECTED expected;
  protected ACTUAL actual;

  private String failMessage = null;

  public BaseAssert(ACTUAL actual) {
    this.actual = actual;
  }

  protected void fail() {
    if(failMessage != null){
      failWithMessage(this.failMessage);
    }else{
      throw new AssertFailureException(String.format("expected: <%s> was: <%s>", expected.toString(), actual.toString()));
    }
  }

  protected void failWithMessage(String message) {
    throw new AssertFailureException(message);
  }

  public BaseAssert<EXPECTED, ACTUAL> isEqual(EXPECTED expected){
    this.expected = expected;
    if(!expected.equals(actual)){
      fail();
    }
    return this;
  }

  public BaseAssert<EXPECTED, ACTUAL> isNotEqual(EXPECTED expected){
    this.expected = expected;
    if(expected.equals(actual)){
      fail();
    }
    return this;
  }

  public BaseAssert<EXPECTED, ACTUAL> isNull(){
    if(expected != null){
      failWithMessage(String.format("expected <%s> to be null", this.actual.toString()));
    }
    return this;
  }
  
  public BaseAssert<EXPECTED, ACTUAL> isNotNull(){
    if(expected == null){
      failWithMessage(String.format("expected <%s> to not be null", this.actual.toString()));
    }
    return this;
  }

}
