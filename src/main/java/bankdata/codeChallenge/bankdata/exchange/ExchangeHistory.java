package bankdata.codeChallenge.bankdata.exchange;

import org.joda.time.DateTime;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ExchangeHistory {

    double exchangeHistory(String currencyFrom, String currencyTo, DateTime dateTime) throws URISyntaxException, IOException;
}
