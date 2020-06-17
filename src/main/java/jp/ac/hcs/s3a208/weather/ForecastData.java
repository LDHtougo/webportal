package jp.ac.hcs.s3a208.weather;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 1件分の天気情報
 * 各項目のデータ仕様については、APIの仕様を参照とする
 * - http://weather.livedoor.com/weather_hacks/webservice
 *
*/


@Data
@NoArgsConstructor
public class ForecastData {

   /** 日付 */
	private String dateLabel;

	/** 予報 */
	private String telop;
}
