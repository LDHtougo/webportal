package jp.ac.hcs.s3a208.car;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 1件分の店舗情報.
 */
@Data
@NoArgsConstructor
public class CarData {

	/** id */
	private String id;

	/** 国名 */
	private String country;

	/** ブランド */
	private String brand;

	/** モデル(車名) */
	private String model;

	/** 価格 */
	private String price;

	/** 説明文 */
	private String desc;
}
