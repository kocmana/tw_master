package at.technikum.masterproject.service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BenchmarkService {

  private final GraphQlCaller graphQlCaller;

  @Autowired
  public BenchmarkService(GraphQlCaller graphQlCaller) {
    this.graphQlCaller = graphQlCaller;
    log.warn("I got a call");
  }

  @PostConstruct
  public void start() {
    log.warn("Post construct called");
    graphQlCaller.doBenchmark("schema/benchmark.graphqls", 100);
  }
}
