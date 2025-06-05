package bankdata.codeChallenge.bankdata;

import bankdata.codeChallenge.bankdata.exchange.ExchangeHistory;
import bankdata.codeChallenge.bankdata.exchange.ExchangeRateApiIo;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateApiIoTest {

    @Test
    void testExchangeRateHistory() {
        ExchangeHistory exchangeRateApiIo = new ExchangeRateApiIo();
        double result = exchangeRateApiIo.exchangeHistory("DKK", "USD", DateTime.now().minusYears(1));
        Assertions.assertNotEquals(0, result);
    }
}
