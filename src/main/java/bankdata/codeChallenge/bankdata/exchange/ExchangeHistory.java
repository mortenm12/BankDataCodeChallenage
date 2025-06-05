package bankdata.codeChallenge.bankdata.exchange;

import org.joda.time.DateTime;

public interface ExchangeHistory {

    double exchangeHistory(String currencyFrom, String currencyTo, DateTime dateTime);
}
