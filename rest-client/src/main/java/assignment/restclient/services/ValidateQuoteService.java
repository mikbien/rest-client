package assignment.restclient.services;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ValidateQuoteService
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${analyze}")
    private String resource;

    private static HttpHeaders headers;
    {
       headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    public QuoteScore getOne(Quote quote)
    {
        Map<String, String> params = new HashMap<>();
        params.put("text", quote.getQuote());
        return restTemplate.postForObject(resource, new HttpEntity<>(params, headers) , QuoteScore.class);
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