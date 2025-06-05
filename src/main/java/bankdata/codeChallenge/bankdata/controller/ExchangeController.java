package bankdata.codeChallenge.bankdata.controller;

import bankdata.codeChallenge.bankdata.service.ExchangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Exchange")
@RequestMapping("exchange")
public class ExchangeController {

    @GetMapping("/{currency}")
    String getExchangeRate (@PathVariable("currency") String currency) {
        ExchangeService exchangeService = new ExchangeService();
        double amount = exchangeService.exchange(100, currency);
        Map<String, Double> result = new HashMap<>();
        result.put("DKK", 100.0);
        result.put(currency, amount);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            return "error"; //better to use a response
        }
    }

    @GetMapping("/history/{currency}/{dateFrom}/{dateTo}")
    String getExchangeHistory (@PathVariable("currency") String currency, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo) {
        //remember to make exclude
        //remember to make gradient
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeService exchangeService = new ExchangeService();
            DateTime dateTimeFrom = DateTime.parse(dateFrom);
            DateTime dateTimeTo = DateTime.parse(dateTo);
            if(dateTimeFrom.isAfter(dateTimeTo)){
                return "error"; //handle response status
            }
            return objectMapper.writeValueAsString(exchangeService.exchangeHistory(100, currency, dateTimeFrom, dateTimeTo));
        } catch (JsonProcessingException e) {
            return "error"; //better to use a response
        }
    }
}
