package assignment.restclient.services;

import assignment.restclient.dtos.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class GetQuoteService
{
    //@Autowired
    //private RestTemplate restTemplate;
    private static Logger log = LoggerFactory.getLogger(ValidateQuoteService.class);

    private static WebClient client;

    @Value("${quotes}")
    private static String resource;

    private ExecutorService executor;

    /* Get one quote from the quote generator api */
    public List<Quote> find(int count)
    {
        List<Quote> temp = new ArrayList<>();
        List<Quote> res = new ArrayList<>();
        int size = count;
        do
        {
            temp = new ArrayList<>(Objects.requireNonNull(Flux.range(1, size)
                    .parallel()
                    .runOn(Schedulers.boundedElastic())
                    .flatMap(e -> this.findOne())
                    .collectSortedList((e1, e2) -> e1.getQuote().compareTo(e2.getQuote()))
                    .block()))
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());

            for(Quote q : temp) if(!res.contains(q)) res.add(q);

            size = count - res.size();

        } while(size!=0);
        return res;
    }
    /* Collect given amount of quotes from the generator api */
    public Mono<Quote> findOne()
    {
        String res = "";
        try
        {
             res = WebClient.builder()
                    .baseUrl("https://api.kanye.rest")
                    .build()
                    .get()
                    .uri("/")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
             //log.info(res);
             ObjectMapper mapper = new ObjectMapper();
             return Mono.just(mapper.readValue(res, Quote.class));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
