package io.pivotal.greeting;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

  private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final EurekaClient discoveryClient;

  public GreetingController(EurekaClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  @RequestMapping("/")
  String getGreeting(Model model) {

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    RestTemplate restTemplate = new RestTemplate();
    String fortune = restTemplate.getForObject(fetchFortuneServiceUrl(), String.class);

    logger.debug("Adding fortune: {}", fortune);
    model.addAttribute("fortune", fortune);

    return "greeting"; // resolves to the greeting.ftl template
  }

  private String fetchFortuneServiceUrl() {
    InstanceInfo instance = discoveryClient.getNextServerFromEureka("FORTUNE-SERVICE", false);
    logger.debug("instanceID: {}", instance.getId());

    String fortuneServiceUrl = instance.getHomePageUrl();
    logger.debug("fortune service homePageUrl: {}", fortuneServiceUrl);

    return fortuneServiceUrl;
  }

}
