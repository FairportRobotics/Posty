package org.fairportrobotics.frc.posty.assertions;

import org.fairportrobotics.frc.posty.exceptions.AssertFailureException;

public abstract class BaseAssert<SELF extends BaseAssert<SELF, ACTUAL>, ACTUAL> {

  protected SELF self;
  protected ACTUAL actual;

  private String failMessage = null;

  public BaseAssert(ACTUAL actual) {
    this.actual = actual;
    this.self = (SELF) this;
  }

  protected void fail(Object expected) {
    if(failMessage != null){
      failWithMessage(this.failMessage);
    }else{
      throw new AssertFailureException(String.format("expected: <%s> was: <%s>", expected.toString(), actual.toString()));
    }
  }

  protected void failWithMessage(String message) {
    throw new AssertFailureException(message);
  }

  public SELF as(String message){
    this.failMessage = message;
    return self;
  }

  public SELF isEqual(Object expected){
    if(!expected.equals(actual)){
      fail(expected);
    }
    return self;
  }

  public SELF isNotEqual(Object expected){
    if(expected.equals(actual)){
      fail(expected);
    }
    return self;
  }

  public SELF isNull(){
    if(actual != null){
      failWithMessage(String.format("expected <%s> to be null", this.actual.toString()));
    }
    return self;
  }
  
  public SELF isNotNull(){
    if(actual == null){
      failWithMessage(String.format("expected <%s> to not be null", this.actual.toString()));
    }
    return self;
  }

}
