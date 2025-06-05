package bankdata.codeChallenge.bankdata.exchange;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExchangeRateApiIo implements ExchangeHistory {
    private static final String API_BASE_URL = "https://api.exchangeratesapi.io/v1/";
    private static final String API_TOKEN = ""; //add your own api token
    Logger logger = LoggerFactory.getLogger(ExchangeRateApiIo.class);

    @Override
    public double exchangeHistory (String currencyFrom, String currencyTo, DateTime dateTime) throws URISyntaxException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String urlStr = API_BASE_URL + dateTime.toString(dateTimeFormatter) + "?access_key=" + API_TOKEN + "&symbols=" + currencyTo + "," + currencyFrom;
        URI url = new URI(urlStr);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(url.toURL());
        String result = node.get("success").asText("Error");
        if (!result.equalsIgnoreCase("true")) {
            return 0;
        }
        //this is needed because the free plan do not give you access to custom base currency
        double currencyToDouble = node.get("rates").get(currencyTo).asDouble(0);
        double currencyFromDouble = node.get("rates").get(currencyFrom).asDouble(0);
        return currencyToDouble / currencyFromDouble;

    }
}
