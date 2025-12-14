package org.fairportrobotics.frc.posty.test.resultwriters;

import org.fairportrobotics.frc.posty.test.TestResult;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import static edu.wpi.first.networktables.NetworkTableValue.*;

public class NTWriter extends BaseResultWriter{

  private NetworkTableInstance ntInstance;
  private NetworkTable topLevelTable;
  private NetworkTable resultsTable;

  private NetworkTable postResultsTable;
  private NetworkTable bitResultsTable;

  public NTWriter(){
    ntInstance = NetworkTableInstance.getDefault();
    topLevelTable = ntInstance.getTable("Posty");
    resultsTable = topLevelTable.getSubTable("results");

    postResultsTable = resultsTable.getSubTable("post");
    bitResultsTable = resultsTable.getSubTable("bit");
  }

  @Override
  public void writePOSTResults(TestResult[] results) {
    String json = "[";
    for(TestResult result : results){
      json = json + result.toJSON() + ",";
    }
    json = json.substring(0, json.length() - 1);
    json = json + "]";

    postResultsTable.putValue("resultsJson", makeString(json));
  }

  @Override
  public void writeBITResults(TestResult[] results) {
    String json = "[";
    for(TestResult result : results){
      json = json + result.toJSON() + ",";
    }
    json = json + "]";

    bitResultsTable.putValue("resultsJson", makeString(json));
  }

}
