package assignment.restclient.dtos;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Statistics
{
    private List<Score> best;
    private long positive, negative, neutral;

    public Statistics(List<Score> list)
    {
        positive = filter(list,  e->e.getScore()>0);
        negative = filter(list, e->e.getScore()<0);
        neutral = filter(list, e->e.getScore()==0);
        best = findBest(list, e->Math.abs(e.getScore()) == Math.abs((list.get(0).getScore())));
    }

    private long filter(List<Score> list, Predicate<Score> predicate)
    {
        return list.stream().filter(predicate).count();
    }

    private List<Score> findBest (List<Score> list, Predicate<Score> predicate)
    {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<Score> getBest()
    {
        return best;
    }

    public void setBest(List<Score> best)
    {
        this.best = best;
    }

    public long getPositive()
    {
        return positive;
    }

    public void setPositive(long positive)
    {
        this.positive = positive;
    }

    public long getNegative()
    {
        return negative;
    }

    public void setNegative(long negative)
    {
        this.negative = negative;
    }

    public long getNeutral()
    {
        return neutral;
    }

    public void setNeutral(long neutral)
    {
        this.neutral = neutral;
    }
}
