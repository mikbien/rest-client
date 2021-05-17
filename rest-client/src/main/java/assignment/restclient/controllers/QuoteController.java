package assignment.restclient.controllers;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import assignment.restclient.services.GetQuoteService;
import assignment.restclient.services.QuotesWithScoresService;
import assignment.restclient.services.ValidateQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class QuoteController
{
    @Autowired
    private QuotesWithScoresService quotesWithScoresService;

    @GetMapping("{count}")
    public Map<String,Double> getScore(@PathVariable int count){
        return quotesWithScoresService.getResults(count);
    }
}
