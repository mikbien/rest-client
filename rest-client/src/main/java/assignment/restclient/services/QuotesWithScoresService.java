package assignment.restclient.services;

import assignment.restclient.dtos.*;
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

    public List<Score> getScores(int count)
    {
        if(count >= ServiceConfig.lowerBound && count <= ServiceConfig.upperBound)
        {
            return validateQuoteService.getScores(getQuoteService.find(count));
        }
        return null;
    }

    public Statistics getStatistics(List<Score> score)
    {
        return new Statistics(score);
    }
}
