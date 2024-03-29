package at.technikum.masterproject.integrationservice.timeout;

import graphql.execution.instrumentation.InstrumentationState;

public class MaxQueryInstrumentationState implements InstrumentationState {

  private final long startTime;

  public MaxQueryInstrumentationState(){
    startTime = System.currentTimeMillis();
  }

  public Long getTime() {
    return System.currentTimeMillis() - startTime;
  }

}
