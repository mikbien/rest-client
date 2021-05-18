package assignment.restclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteScore
{
    @JsonProperty("result")
    private Result result;

    @JsonProperty("sentences")
    private List<Sentences> sentences;

    @JsonIgnore
    public String getFullQuote()
    {
        String res = "";
        for(Sentences s : sentences) res += s.getSentence() + " ";
        return res;
    }

    public List<Sentences> getSentences()
    {
        return sentences;
    }

    public void setSentences(List<Sentences> sentences)
    {
        this.sentences = sentences;
    }

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
                ", sentences=" + sentences +
                '}';
    }
}
