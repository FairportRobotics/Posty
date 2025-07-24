package org.fairportrobotics.frc.posty.assertions;

import java.util.function.BooleanSupplier;

import org.fairportrobotics.frc.posty.exceptions.TestFailureException;

public class AssertCondition{

  private AssertCondition(){
    /* no-op */
  }

  private static void assertCondition(BooleanSupplier condition) throws TestFailureException{
    if ( !condition.getAsBoolean() ){
      throw new TestFailureException();
    }
  }

}
