package assignment.restclient.controllers;

import assignment.restclient.services.QuotesWithScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class QuoteController
{
    @Autowired
    private QuotesWithScoresService quotesWithScoresService;

    @GetMapping
    public String home(){
        return "index";
    }

    @PostMapping("/post")
    public String getScore(@PathVariable int count, Model model){
        model.addAttribute("quotes", quotesWithScoresService.getResults(count));
        return model.getAttribute("quotes") == null ? "results" : "results";
    }
}
