package assignment.restclient.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
        return "Quote{" +
                "quote='" + quote + '\'' +
                '}';
    }
}
