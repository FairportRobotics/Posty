package org.fairportrobotics.frc.posty.assertions;

import java.util.function.BooleanSupplier;

public class Assertions{

  public static BooleanSupplierAssert assertThat(BooleanSupplier condition){
    return new BooleanSupplierAssert(condition);
  }

}
