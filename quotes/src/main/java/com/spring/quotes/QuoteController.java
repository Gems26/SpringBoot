package com.spring.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuoteController {
  
    @Autowired
    private QuoteService quotes;

    @GetMapping("/")
    public String sendQuotes(Model model){
        String quote = quotes.getQuotes();
        model.addAttribute("q", quote);
        return "quote";
    }

}
