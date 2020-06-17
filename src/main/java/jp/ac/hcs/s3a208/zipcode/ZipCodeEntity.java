package jp.ac.hcs.s3a208.zipcode;

/**
 *郵便番号情報検索結果の店舗情報
 *各項目のデータ使用については、APIの使用を参照とする。
 *1つの郵便番号に複数の住所が紐づくこともある為、リスト構造となっている。
 *- http://zipcloud.ibsnet.co.jp/doc/api
*/

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ZipCodeEntity {

	/** ステータス */
	private String status;

	/** メッセージ */
	private String message;

	/** 郵便番号情報のリスト */
	private List<ZipCodeData> results = new ArrayList<ZipCodeData>();

}

