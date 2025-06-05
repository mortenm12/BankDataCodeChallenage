package bankdata.codeChallenge.bankdata;

import bankdata.codeChallenge.bankdata.exchange.ExchangeRate;
import bankdata.codeChallenge.bankdata.exchange.ExchangeRateApiCom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateApiComTest {

    @Test
    void testExchangeRate() {
        ExchangeRate exchangeRateApiCom = new ExchangeRateApiCom();
        double result = exchangeRateApiCom.exchangeRate("DKK", "USD");
        Assertions.assertNotEquals(0, result);
    }
}
