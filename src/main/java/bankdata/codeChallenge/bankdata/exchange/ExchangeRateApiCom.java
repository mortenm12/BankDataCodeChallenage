package bankdata.codeChallenge.bankdata.exchange;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExchangeRateApiCom implements ExchangeRate, ExchangeHistory {
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_TOKEN = ""; //add your own api token
    Logger logger = LoggerFactory.getLogger(ExchangeRateApiCom.class);

    @Override
    public double exchangeRate (String currencyFrom, String currencyTo) {
        try {
            String urlStr = API_BASE_URL + API_TOKEN + "/pair/" + currencyFrom + "/" + currencyTo;
            URI url = new URI(urlStr);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(url.toURL());
            String result = node.get("result").asText("Error");
            if (!result.equalsIgnoreCase("success")) {
                return 0;
            }
            return node.get("conversion_rate").asDouble(0);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public double exchangeHistory (String currencyFrom, String currencyTo, DateTime dateTime) throws URISyntaxException, IOException {
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        String urlStr = API_BASE_URL + API_TOKEN + "/history/" + currencyFrom + "/" + year + "/" + month + "/" + day;
        URI url = new URI(urlStr);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(url.toURL());
        String result = node.get("result").asText("Error");
        if (!result.equalsIgnoreCase("success")) {
            return 0;
        }
        return node.get("conversion_rates").get(currencyTo).asDouble(0);
    }
}
