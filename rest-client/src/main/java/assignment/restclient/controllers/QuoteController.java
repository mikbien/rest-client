package assignment.restclient.controllers;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import assignment.restclient.services.GetQuoteService;
import assignment.restclient.services.ValidateQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class QuoteController
{
    @Autowired
    private GetQuoteService getQuoteService;
    @Autowired
    private ValidateQuoteService validateQuoteService;

    @GetMapping("{count}")
    public List<Quote> getScore(@PathVariable int count){
        return getQuoteService.find(count);
    }
}
