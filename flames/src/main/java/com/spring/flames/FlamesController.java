package com.spring.flames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlamesController {
    
    @Autowired
    private FlamesService flamesService;

    @GetMapping("/")
    public String getFlamesNameDetails(){
        return "flames";
    }


    @PostMapping("/flames")
    public String sendFlamesResult(@RequestParam String name1,@RequestParam String name2,Model model){
        String flamesResult = flamesService.checkFlames(name1,name2);
        model.addAttribute("flamesResult",flamesResult);
        return "flames";
    }
}
