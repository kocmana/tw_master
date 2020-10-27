package at.technikum.masterproject.controller;

import at.technikum.masterproject.model.QueryStatistic;
import at.technikum.masterproject.service.GraphQlCaller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BenchmarkController {

  private final GraphQlCaller graphQlCaller;

  @Autowired
  public BenchmarkController(GraphQlCaller graphQlCaller) {
    this.graphQlCaller = graphQlCaller;
  }

  @GetMapping
  public String home(Model model) {
    List<QueryStatistic> statistics = graphQlCaller
        .retrieveStatistics("schemas/benchmark.graphqls");

    model.addAttribute("queryStatistics", statistics);
    return "home";
  }
}
