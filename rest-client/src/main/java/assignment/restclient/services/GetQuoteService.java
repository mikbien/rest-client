package assignment.restclient.services;

import assignment.restclient.dtos.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetQuoteService
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${quotes}")
    private String resource;

    /* Get one quote from the quote generator api */
    public Quote findOne()
    {
        Quote q = new Quote();
        try
        {
            return restTemplate.getForObject(resource, Quote.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    /* Collect given amount of quotes from the generator api */
//    public List<Quote> find(int count)
//    {
//        List<Quote> result = new ArrayList<>();
//        {
//            for(int i = 0; i < count; ++i) result.add(findOne());
//            return result;
//        }
//        return null;
//    }
}
