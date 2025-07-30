package org.fairportrobotics.frc.posty.exceptions;

public class AssertFailureException extends RuntimeException{
  
  private String failureReason = "";

  public AssertFailureException(){

  }

  public AssertFailureException(String reason){
    this.failureReason = reason;
  }

  public String getReason(){
    return failureReason;
  }
}
