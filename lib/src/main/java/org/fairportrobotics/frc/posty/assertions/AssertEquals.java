
package org.fairportrobotics.frc.posty.assertions;

import javax.annotation.Nullable;

import org.fairportrobotics.frc.posty.exceptions.TestFailureException;

class AssertEquals {

  private AssertEquals(){
    /* no-op */
  }

  static void assertEquals(int expected, int actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }

  static void assertEquals(byte expected, byte actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }

  static void assertEquals(char expected, char actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }

  static void assertEquals(double expected, double actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }

  static void assertEquals(float expected, float actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }

  static void assertEquals(short expected, short actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }

  static void assertEquals(long expected, long actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }


  static void assertEquals(@Nullable Object expected, @Nullable Object actual) throws TestFailureException{
    objectsEquals(expected, actual);
  }

  private static void objectsEquals(@Nullable Object expected, @Nullable Object actual) throws TestFailureException{
    if( !expected.equals(actual) ){
      throw new TestFailureException();
    }
  }

}
