package jp.ac.hcs.s3a208.gourmet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * グルメ検索機能の動作を行う.
 * APIとの通信および、ShopData・ShopEntityに対する値の入れ替え(マッピング)を行う.
 * フリーワードで検索する
 * - http://webservice.recruit.co.jp/hotpepper/reference.html
 */
@Transactional
@Service
public class GourmetService {

	@Autowired
	RestTemplate restTemplate;

	/** グルメ検索機能API リクエストURL*/
	private static final String URL = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=9fa2bf3d4c729c0c&keyword={shopname}&large_service_area=SS40&format=json";


	/**
	 * 指定した店舗名と大サービスエリアコードに紐づく店舗情報を取得する.
	 * @param shopname 店舗名
	 * @return ShopEntity
	 */
	public ShopEntity getGourmet(String shopname) {

		String json = restTemplate.getForObject(URL,String.class,shopname);

		ShopEntity shopEntity = new ShopEntity();

		//Mapping
		try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode node = mapper.readTree(json);

				shopEntity.setResults_available(node.get("results").get("results_available").asText());
				shopEntity.setResults_returned(node.get("results").get("results_returned").asText());
				shopEntity.setResults_start(node.get("results").get("results_start").asText());

				for(JsonNode shop : node.get("results").get("shop")){
					ShopData shopData = new ShopData();
					shopData.setId(shop.get("id").asText());
					shopData.setName(shop.get("name").asText());
					shopData.setLogo_image(shop.get("logo_image").asText());
					shopData.setName_kana(shop.get("name_kana").asText());
					shopData.setAddress(shop.get("address").asText());
					shopData.setAccess(shop.get("access").asText());
					shopData.setUrl(shop.get("urls").get("pc").asText());

					shopEntity.getShops().add(shopData);
				}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return shopEntity;

	}
}
