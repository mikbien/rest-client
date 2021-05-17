package assignment.restclient.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result
{

    private Double polarity;
    private String type;

    public Double getPolarity()
    {
        return polarity;
    }

    public Double getUnsignedPolarity()
    {
        return polarity>=0 ? polarity : polarity*(-1.0);
    }

    public void setPolarity(Double polarity)
    {
        this.polarity = polarity;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Result{" +
                "polarity=" + polarity +
                ", type='" + type + '\'' +
                '}';
    }
}
