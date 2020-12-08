package at.technikum.masterproject.benchmarkservice.controller;

import static java.util.Objects.nonNull;

import at.technikum.masterproject.benchmarkservice.model.dto.BenchmarkResult;
import at.technikum.masterproject.benchmarkservice.service.ResultService;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/results")
public class ResultController {

  private final ResultService resultService;

  @Autowired
  public ResultController(ResultService resultService) {
    this.resultService = resultService;
  }

  @GetMapping
  public String home(Model model,
                     @RequestParam @NotBlank String uuid,
                     @RequestParam(required = false) Integer bucketSize) {

    bucketSize = nonNull(bucketSize) ? bucketSize : 1000;

    BenchmarkResult statistics = resultService.retrieveBenchmarkResults(uuid, bucketSize);

    model.addAttribute("benchmarkStatistics", statistics);
    model.addAttribute("graphLabels", generateStringList(statistics.getResponseTimeDistribution().keySet()));
    model.addAttribute("graphValues", generateStringList(statistics.getResponseTimeDistribution().values()));
    return "home";
  }

  private <T> String generateStringList(Collection<T> keySet) {
    return keySet.stream()
        .map(T::toString)
        .collect(Collectors.joining(", ", "[", "]"));
  }

}
