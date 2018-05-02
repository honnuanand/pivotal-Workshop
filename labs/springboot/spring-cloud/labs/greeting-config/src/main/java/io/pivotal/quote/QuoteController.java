package io.pivotal.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuoteController {

  private final Logger logger = LoggerFactory.getLogger(QuoteController.class);

  private final QuoteService quoteService;

  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @RequestMapping("/random-quote")
  String getView(Model model) {
    logger.debug("returning random quote");
    model.addAttribute("quote", quoteService.getQuote());
    model.addAttribute("uri", quoteService.getQuoteServiceURI());
    return "quote";
  }
}