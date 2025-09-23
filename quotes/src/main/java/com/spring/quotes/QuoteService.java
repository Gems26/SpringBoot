package com.spring.quotes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {
   List<String> quoteslist= Arrays.asList("Naan oru thadava sonna, nooru thadava sonna maadhiri. — Rajinikanth",
            "En vazhi, thani vazhi. — Rajinikanth",
            "Neenga nallavara kettavara? — Kamal Haasan",
            "I’m waiting. — Vijay",
            "Money, money, money… I want my money! — Ajith",
            "Singa kutti singa kutti dhaan… singam pera kuda valarum. — Suriya",
            "Naan solradhu seiven… seiradhu sonnen. — Vikram",
            "Naanga ellam rowdy illa… rowdy ah nadikkara actors. — Dhanush");

   public String getQuotes(){
        Random quoGenerator = new Random();
        int randomIndex = quoGenerator.nextInt(quoteslist.size());
        return quoteslist.get(randomIndex);
   }
}
