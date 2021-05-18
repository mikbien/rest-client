package assignment.restclient.services;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import assignment.restclient.dtos.Score;
import assignment.restclient.dtos.Sentences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuotesWithScoresService
{
    @Autowired ValidateQuoteService validateQuoteService;
    @Autowired GetQuoteService getQuoteService;

    public List<Score> getResults(int count)
    {
        if(count >= ServiceConfig.lowerBound && count <= ServiceConfig.upperBound)
        {
            return validateQuoteService.getScores(getQuoteService.find(count));
        }
        return null;
    }
}
