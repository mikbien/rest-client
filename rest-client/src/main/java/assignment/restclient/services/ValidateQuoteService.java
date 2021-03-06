package assignment.restclient.services;

import assignment.restclient.dtos.Quote;
import assignment.restclient.dtos.QuoteScore;
import assignment.restclient.dtos.Score;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.print.attribute.standard.Media;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.TEXT_HTML;

@Service
public class ValidateQuoteService
{
    @Autowired
    private RestTemplate restTemplate;

    private static final HttpHeaders headers;
    private static Logger log;
    private static ObjectMapper mapper;

    static
    {
       headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
       log = LoggerFactory.getLogger(ValidateQuoteService.class);
       mapper = new ObjectMapper();
    }

    public List<Score> getScores(List<Quote> quotes)
    {
        return Flux.fromIterable(quotes)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap(this::getOne)
                .collectSortedList((e1, e2)->
                        Double.compare(Math.abs(e2.getScore()),
                                Math.abs(e1.getScore())))
                .block();
    }

    public Mono<Score> getOne(Quote quote)
    {
        Map<String, String> params = new HashMap<>();
        params.put("text", quote.getQuote());
        QuoteScore qs = restTemplate.postForObject(ServiceConfig.analyze, new HttpEntity<>(params, headers) , QuoteScore.class);

        return Mono.just(new Score(quote.getQuote(), qs.getResult().getPolarity()));
    }

//    public Score getOneSimple(Quote quote)
//    {
//        Map<String, String> params = new HashMap<>();
//        params.put("text", quote.getQuote());
//        QuoteScore qs = restTemplate.postForObject(resource, new HttpEntity<>(params, headers) , QuoteScore.class);
//        return new Score(quote.getQuote(), qs.getResult().getPolarity());
//    }
}


