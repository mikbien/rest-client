package assignment.restclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sentences
{
    @JsonProperty("sentece")
    private String sentence;

    public String getSentence()
    {
        return sentence;
    }

    public void setSentence(String sentence)
    {
        this.sentence = sentence;
    }

    @Override
    public String toString()
    {
        return "Sentences{" +
                "sentence='" + sentence + '\'' +
                '}';
    }
}
