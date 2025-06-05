package bankdata.codeChallenge.bankdata.controller;

import bankdata.codeChallenge.bankdata.service.ExchangeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "Exchange")
@RequestMapping("exchange")
public class ExchangeController {
    Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @GetMapping("/{currency}")
    ResponseEntity<String> getExchangeRate (@PathVariable("currency") String currency) {
        ExchangeService exchangeService = new ExchangeService();
        double amount = exchangeService.exchange(100, currency);
        Map<String, Double> result = new HashMap<>();
        result.put("DKK", 100.0);
        result.put(currency, amount);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/history/{currency}/{dateFrom}/{dateTo}")
    ResponseEntity<String> getExchangeHistory (@PathVariable("currency") String currency,
                               @PathVariable("dateFrom") @Parameter(description = "format: yyyy-mm-dd") String dateFrom,
                               @PathVariable("dateTo") @Parameter(description = "format: yyyy-mm-dd")String dateTo,
                               @Nullable @RequestParam("excludeFrom") @Parameter(description = "format: yyyy-mm-dd") String excludeFrom,
                               @Nullable @RequestParam("excludeTo") @Parameter(description = "format: yyyy-mm-dd") String excludeTo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeService exchangeService = new ExchangeService();
            DateTime dateTimeFrom = dateFrom != null ? DateTime.parse(dateFrom) : null;
            DateTime dateTimeTo = dateTo != null ? DateTime.parse(dateTo) : null;
            DateTime dateTimeExcludeFrom = excludeFrom != null ? DateTime.parse(excludeFrom) : null;
            DateTime dateTimeExcludeTo = excludeTo != null ? DateTime.parse(excludeTo) : null;
            if(dateTimeFrom == null || dateTimeTo == null){
                logger.warn("dateTime are not correct");
                return ResponseEntity.badRequest().body("dateTime are not correct");
            }
            if(dateTimeFrom.isAfter(dateTimeTo)){
                logger.warn("dateFrom is after dateTo");
                return ResponseEntity.badRequest().body("dateFrom is after dateTo");
            }
            if(dateTimeExcludeFrom != null && dateTimeExcludeTo != null && dateTimeExcludeFrom.isAfter(dateTimeExcludeTo)){
                logger.warn("excludeDateFrom is after excludeDateTo");
                return ResponseEntity.badRequest().body("excludeDateFrom is after excludeDateTo");
            }
            return ResponseEntity.ok(objectMapper.writeValueAsString(exchangeService.exchangeHistory(100, currency, dateTimeFrom, dateTimeTo, dateTimeExcludeFrom, dateTimeExcludeTo)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
