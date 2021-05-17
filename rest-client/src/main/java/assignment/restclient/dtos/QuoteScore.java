package assignment.restclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteScore
{
    private Result result;

    public Result getResult()
    {
        return result;
    }

    public void setResult(Result result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "QuoteScore{" +
                "result=" + result +
                '}';
    }
}
