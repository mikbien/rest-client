package assignment.restclient.controllers;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import assignment.restclient.services.GetQuoteService;
import assignment.restclient.services.ValidateQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class QuoteController
{
    @Autowired
    private GetQuoteService getQuoteService;
    @Autowired
    private ValidateQuoteService validateQuoteService;

    @GetMapping("{count}")
    public List<QuoteScore> getScore(@PathVariable int count){
        return validateQuoteService.get(getQuoteService
                .find(count)).stream().sorted((e1,e2)->e1.getResult().getUnsignedPolarity()
                    .compareTo(e2.getResult().getUnsignedPolarity())).collect(Collectors.toList());
    }
}
