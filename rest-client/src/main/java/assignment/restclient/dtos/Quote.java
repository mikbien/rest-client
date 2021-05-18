package assignment.restclient.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote
{
    @JsonAlias({"quote", "text"})
    private String quote;


    public String getQuote()
    {
        return quote;
    }

    public void setQuote(String quote)
    {
        this.quote = quote;
    }

    @Override
    public String toString()
    {
        return "{\"text\":\""+quote+"\"}";
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote1 = (Quote) o;
        return Objects.equals(getQuote().trim(), quote1.getQuote().trim());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getQuote());
    }
}
