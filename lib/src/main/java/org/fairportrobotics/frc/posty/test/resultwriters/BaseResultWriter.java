package org.fairportrobotics.frc.posty.test.resultwriters;

import org.fairportrobotics.frc.posty.test.TestResult;

public abstract class BaseResultWriter{

  public BaseResultWriter(){

  }

  public abstract void writePOSTResults(TestResult[] results);

  public abstract void writeBITResults(TestResult[] results);

}
