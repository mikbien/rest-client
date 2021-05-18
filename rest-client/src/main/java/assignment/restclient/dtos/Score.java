package assignment.restclient.dtos;

public class Score
{
    private String sentence;
    private Double score;

    public Score(String sentence, Double score)
    {
        this.sentence = sentence;
        this.score = score;
    }

    public String getSentence()
    {
        return sentence;
    }

    public void setSentence(String sentence)
    {
        this.sentence = sentence;
    }

    public Double getScore()
    {
        return score;
    }

    public void setScore(Double score)
    {
        this.score = score;
    }
}
