package org.fairportrobotics.frc.posty.test;

import org.assertj.core.api.WithAssertions;
import java.util.function.BooleanSupplier;

public abstract class BaseTest implements WithAssertions{

  private String testName;

  public BaseTest(String testName){
    this.testName = testName;
  }

  public abstract void initialize();

  public abstract void execute();

  public abstract boolean isFinished();

  public abstract void end();

  public String getTestName(){
    return testName;
  }

  protected boolean waitForCondition(BooleanSupplier condition){
    return waitForCondition(condition, 10);
  }

  protected boolean waitForCondition(BooleanSupplier condition, double sec){

    long startTime = System.currentTimeMillis();
    while(System.currentTimeMillis() - startTime <= (sec * 1000)){
      if(condition.getAsBoolean()){
        return true;
      }
    }
    return false;
  }

}
