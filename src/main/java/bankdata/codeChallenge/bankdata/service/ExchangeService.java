package bankdata.codeChallenge.bankdata.service;

import bankdata.codeChallenge.bankdata.exchange.ExchangeHistory;
import bankdata.codeChallenge.bankdata.exchange.ExchangeRate;
import bankdata.codeChallenge.bankdata.exchange.ExchangeRateApiCom;
import bankdata.codeChallenge.bankdata.exchange.ExchangeRateApiIo;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

public class ExchangeService {
    ExchangeRate exchange = new ExchangeRateApiCom();
    ExchangeHistory exchangeHistory = new ExchangeRateApiIo();
    String currencyFrom = "DKK";

    public double exchange(double amount, String currencyTo){
        double result = exchange.exchangeRate(currencyFrom, currencyTo);
        return amount * result;
    }

    public Map<DateTime, Double> exchangeHistory(double amount, String currencyTo, DateTime dateTimeFrom, DateTime dateTimeTo, DateTime exceptionFrom, DateTime excectionTo) {
        Map<DateTime, Double> result = new HashMap<>();

        for(; dateTimeFrom.isBefore(dateTimeTo); dateTimeFrom.plusMonths(1)){
            if(!between(dateTimeFrom, exceptionFrom, excectionTo)) {
                double doubleAmount = exchangeHistory.exchangeHistory(currencyFrom, currencyTo, dateTimeFrom);
                result.put(dateTimeFrom, doubleAmount * amount);
            }
        }
        double doubleAmount = exchangeHistory.exchangeHistory(currencyFrom, currencyTo, DateTime.now());
        result.put(dateTimeFrom, doubleAmount * amount);
        return result;
    }

    public Map<DateTime, Double> exchangeHistory(double amount, String currencyTo, DateTime dateTimeFrom, DateTime dateTimeTo) {
        return exchangeHistory(amount, currencyTo, dateTimeFrom, dateTimeTo, null, null);
    }

    private boolean between(DateTime dateTime, DateTime from, DateTime to){
        return from != null && to != null && (dateTime.isBefore(from) || dateTime.isAfter(to));
    }
}
