package bankdata.codeChallenge.bankdata;

import bankdata.codeChallenge.bankdata.service.ExchangeService;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

//disabled because take a lot of request
@Disabled
class ExchangeRateServiceTest {
    DateTime from = DateTime.parse("2014-01-01");
    DateTime to = DateTime.parse("2015-01-01");
    DateTime exceptionFrom = DateTime.parse("2014-03-01");
    DateTime exceptionTo = DateTime.parse("2015-03-31");

    @Test
    void testExchangeRateHistory(){
        ExchangeService exchangeService = new ExchangeService();
        Map<DateTime, Double> result = exchangeService.exchangeHistory(100, "USD", from, to);
        Assertions.assertEquals(12, result.size());
        Assertions.assertNotEquals(0, result.get(from));
    }

    @Test
    void testExchangeRateHistoryWithException(){
        ExchangeService exchangeService = new ExchangeService();
        Map<DateTime, Double> result = exchangeService.exchangeHistory(100, "USD", from, to, exceptionFrom, exceptionTo);
        Assertions.assertEquals(11, result.size());
        Assertions.assertNotEquals(0, result.get(from));
    }
}
