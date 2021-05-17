package assignment.restclient.services;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuotesWithScoresService
{
    @Autowired ValidateQuoteService validateQuoteService;
    @Autowired GetQuoteService getQuoteService;

    @Value("${lower-bound}")
    Integer lowerBound;

    @Value("#${upper-bound}")
    Integer upperBound;

    public Map<String, Double> getResults(int count)
    {
        if(count >= lowerBound && count <= upperBound)
        {
            Map<String, Double> results = new HashMap<>();
            Quote quote;
            QuoteScore score;
            for (int i = 0; i < count; ++i)
            {
                quote = getQuoteService.findOne();
                if (!results.containsKey(quote.getQuote()))
                {
                    score = validateQuoteService.getOne(quote);
                    results.put(quote.getQuote(), score.getResult().getPolarity());
                } else count--;
            }
            return results.entrySet().stream()
                    .sorted(Map.Entry.<String,Double>comparingByValue(
                            Comparator.comparing(Math::abs)).reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));
        }
        return null;
    }
}
