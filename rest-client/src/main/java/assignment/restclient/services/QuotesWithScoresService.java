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

    public Statistics getResults(int count)
    {
        if(count >= ServiceConfig.lowerBound && count <= ServiceConfig.upperBound)
        {
            return new Statistics(validateQuoteService.getScores(getQuoteService.find(count)));
        }
        return null;
    }
}
