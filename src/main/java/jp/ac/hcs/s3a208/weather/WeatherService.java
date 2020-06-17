package jp.ac.hcs.s3a208.weather;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 天気予報情報を操作する。
 * 天気予報検索APIを活用する。
 * -http://weather.livedoor.com/forecast/webservice/json
 */

@Transactional
@Service
public class WeatherService {

	@Autowired
	RestTemplate restTemplate;
	/** 郵便番号検索API リクエストURL */
	private static final String URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city={citycode}";

	public WeatherEntity getWeather(String citycode) {
		//外部APIアクセス
		String json = restTemplate.getForObject(URL, String.class ,citycode);

		WeatherEntity weatherEntity = new WeatherEntity();

		//Mapping
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			weatherEntity.setText(node.get("description").get("text").asText());
			for (JsonNode forecast : node.get("forecasts")) {

				ForecastData forecastData = new ForecastData();
				forecastData.setDateLabel(forecast.get("dateLabel").asText());
				forecastData.setTelop(forecast.get("telop").asText());
				weatherEntity.getForecasts().add(forecastData);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return weatherEntity;
	}
}

