
package org.fairportrobotics.frc.posty.assertions;

import java.util.function.BooleanSupplier;

import org.fairportrobotics.frc.posty.exceptions.AssertFailureException;

public class BooleanSupplierAssert {

  BooleanSupplier cond;

  public BooleanSupplierAssert(BooleanSupplier condition){
    this.cond = condition;
  }

  public void isTrue() { 
    if (!cond.getAsBoolean()){
      throw new AssertFailureException("Expecting value to be true but was false");
    }
  }

  public void isFalse() {
    if(cond.getAsBoolean()){
      throw new AssertFailureException("Expecting value to be false but was true");
    }
  }
}
