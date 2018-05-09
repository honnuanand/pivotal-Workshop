package io.pivotal.greeting;

import io.pivotal.fortune.FortuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingController {

  private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final GreetingProperties greetingProperties;
  private final FortuneService fortuneService;

  public GreetingController(GreetingProperties greetingProperties, FortuneService fortuneService) {
    this.greetingProperties = greetingProperties;
    this.fortuneService = fortuneService;
  }

  @RequestMapping("/")
  String getGreeting(Model model) {

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    if (greetingProperties.isDisplayFortune()) {
      logger.debug("Adding fortune");
      model.addAttribute("fortune", fortuneService.getFortune());
    }

    return "greeting"; // resolves to the greeting.ftl template
  }

}
