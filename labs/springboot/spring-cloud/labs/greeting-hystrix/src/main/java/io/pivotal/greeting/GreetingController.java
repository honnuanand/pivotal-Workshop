package io.pivotal.greeting;

import io.pivotal.fortune.FortuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

  private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final FortuneService fortuneService;

  public GreetingController(FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  @RequestMapping("/")
  String getGreeting(Model model) {

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    String fortune = fortuneService.getFortune();

    logger.debug("Adding fortune");
    model.addAttribute("fortune", fortune);

    return "greeting"; // resolves to the greeting.ftl template
  }

}
