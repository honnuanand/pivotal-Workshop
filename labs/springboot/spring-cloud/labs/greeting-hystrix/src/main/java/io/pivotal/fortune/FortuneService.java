package io.pivotal.fortune;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FortuneService {

  private final Logger logger = LoggerFactory.getLogger(FortuneService.class);

  private final RestTemplate restTemplate;

  public FortuneService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @HystrixCommand(threadPoolKey = "thread-pool-0" , fallbackMethod = "defaultFortune")
  public String getFortune() {
    return restTemplate.getForObject("http://fortune-service", String.class);
  }


  @HystrixCommand(threadPoolKey = "thread-pool-1" , fallbackMethod = "secondDefaultFortune")
  public String defaultFortune() {
    logger.debug("Default fortune used.");
    return "This fortune is no good. This may work .";
  }

  @HystrixCommand(threadPoolKey = "thread-pool-2l")
  public String secondDefaultFortune() {
    logger.debug("Default fortune used.");
    return "This fortune is no good. Try another.";
  }

}
