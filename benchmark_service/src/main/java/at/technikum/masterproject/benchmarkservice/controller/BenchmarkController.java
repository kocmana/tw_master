package at.technikum.masterproject.benchmarkservice.controller;

import at.technikum.masterproject.benchmarkservice.model.BenchmarkResult;
import at.technikum.masterproject.benchmarkservice.service.ResultService;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BenchmarkController {

  private final ResultService resultService;

  @Autowired
  public BenchmarkController(ResultService resultService) {
    this.resultService = resultService;
  }

  @GetMapping(value = "/results")
  public String home(Model model) {
    BenchmarkResult statistics = resultService
        .retrieveBenchmarkResults("schemas/benchmark.graphqls", 100);

    model.addAttribute("benchmarkStatistics", statistics);
    model.addAttribute("graphLabels", generateStringList(statistics.getResponseTimeDistribution().keySet()));
    model.addAttribute("graphValues", generateStringList(statistics.getResponseTimeDistribution().values()));
    return "home";
  }

  private <T> String generateStringList(Collection<T> keySet) {
    return keySet.stream()
        .map(Object::toString)
        .collect(Collectors.joining(", ", "[", "]"));
  }

}
