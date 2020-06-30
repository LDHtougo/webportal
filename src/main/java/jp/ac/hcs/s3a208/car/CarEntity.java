package jp.ac.hcs.s3a208.car;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 車の検索結果情報.
 * 各項目のデータ仕様については、APIの仕様を参照とする.
 * - http://webservice.recruit.co.jp/hotpepper/reference.html
 * - http://webservice.recruit.co.jp/carsensor/usedcar/v1/
 */
@Data
public class CarEntity {

	/** 全件数 */
	private String results_available;

	/** 検索件数 */
	private String results_returned;

	/** 開始位置 */
	private String results_start;

	/** 店舗情報のリスト */
	private List<CarData> cars = new ArrayList<CarData>();

}