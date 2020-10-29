package at.technikum.masterproject.benchmarkservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

  @GetMapping
  public String home(Model model) {

    return "error";
  }

}
