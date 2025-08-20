package org.fairportrobotics.frc.posty.assertions;

public class Assertions{

  public static BooleanAssertion assertThat(Boolean actual){
    return new BooleanAssertion(actual);
  }

  public static IntegerAssertion assertThat(Integer actual){
    return new IntegerAssertion(actual);
  }

  public static DoubleAssertion assertThat(Double actual){
    return new DoubleAssertion(actual);
  }

  public static FloatAssertion assertThat(Float actual){
    return new FloatAssertion(actual);
  }

  public static StringAssertion assertThat(String actual){
    return new StringAssertion(actual);
  }

}
