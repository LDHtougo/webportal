package jp.ac.hcs.s3a208.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 都市コード検索結果のお天気情報.
 * 各項目のデータ仕様については、APIの仕様を参照とする.
 * 複数のお天気情報を取得する必要がある為、リスト構造となっている.
 * - http://weather.livedoor.com/forecast/webservice/json/v1
 */
@Data
public class WeatherEntity {

	/** お天気情報のリスト */
	private List<ForecastData> forecasts = new ArrayList<ForecastData>();

	private String text;



}
