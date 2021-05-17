package assignment.restclient.services;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidateQuoteService
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${analyze}")
    private String resource;

    public QuoteScore getOne(Quote quote)
    {
        return restTemplate.postForObject(resource, quote, QuoteScore.class);
    }

    public List<QuoteScore> get(List<Quote> list)
    {
        List<QuoteScore> scores = new ArrayList<>();
        for(Quote str : list)
        {
            scores.add(getOne(str));
        }
        return scores;
    }

}
