package assignment.restclient.controllers;

import assignment.restclient.dtos.Count;
import assignment.restclient.dtos.Score;
import assignment.restclient.services.QuotesWithScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class QuoteController
{
    @Autowired
    private QuotesWithScoresService quotesWithScoresService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("count", new Count());
        return "index";
    }

    @PostMapping("/")
    public String getScore(@ModelAttribute Count count, Model model){
        model.addAttribute("quotes", quotesWithScoresService.getScores(count.getValue()));
        model.addAttribute("statistics", quotesWithScoresService
                .getStatistics((List<Score>) model.getAttribute("quotes")));
        return model.getAttribute("quotes") == null ? "index" : "results";
    }
}
