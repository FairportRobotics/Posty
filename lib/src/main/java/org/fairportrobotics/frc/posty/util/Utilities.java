package org.fairportrobotics.frc.posty.util;

import java.util.function.BooleanSupplier;

public class Utilities {

  /**
   * Blocking wait call until a condition is met.
   * Will timeout and un-block after 10 seconds.
   *
   * @param condition BooleanSupplier Condition to wait for
   *
   * @return boolean True if condition is met. False otherwise
   */
  public static boolean waitForCondition(BooleanSupplier condition) {
    return waitForCondition(condition, 10);
  }

  /**
   * Blocking wait call until a condition is met or a timeout is exceeded.
   *
   * @param condition BooleanSupplier Condition to wait for
   * @param timeout float How long to wait before timing out (in seconds)
   *
   * @return boolean True if condition is met. False otherwise
   */
  public static boolean waitForCondition(BooleanSupplier condition, float timeout) {
    long startTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - startTime <= (timeout * 1000)) {
      if (condition.getAsBoolean()) {
        return true;
      }
    }
    return false;
  }

}
