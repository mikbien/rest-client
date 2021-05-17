package assignment.restclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result
{

    private Double polarity;

    public Double getPolarity()
    {
        return polarity;
    }

    public void setPolarity(Double polarity)
    {
        this.polarity = polarity;
    }

    @Override
    public String toString()
    {
        return "Result{" +
                "polarity=" + polarity +
//                ", type='" + type + '\'' +
                '}';
    }
}
