package jp.ac.hcs.s3a208.gourmet;

import java.security.Principal;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GourmetController {

	@Autowired //GourmetControllerとGourmetServiceを紐付ける処理
	private GourmetService gourmetService;

	/**
	 * 店舗名からグルメ情報を検索し、結果画面を表示する.
	 * 地区は北海道に固定する
	 * - large_service_area(大サービスエリアコード) :SS40(北海道)
	 * @param shopname 検索する店舗名
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - グルメ情報
	 */

	@PostMapping("/gourmet")
	public String postGourmet(@RequestParam("shopname") String shopname, Principal principal, Model model) {

		if(shopname.equals("")) {
			log.info("[" + principal.getName() + "]グルメ情報検索:" + shopname + "[情報不適切:空文字]");
			return "redirect:/";
		}
		if(shopname.length() > 50) {
			log.info("[" + principal.getName() + "]グルメ情報検索:" + shopname + "[情報不適切:文字超過]");
			return "redirect:/";
		}
		Pattern p = Pattern.compile("[!\"#$%&'()\\*\\+\\-\\.,\\/:;<=>?@\\[\\\\\\]^_`{|}~]");
		if(p.matcher(shopname).find()) {
			log.info("[" + principal.getName() + "]グルメ情報検索:" + shopname + "[ 店舗名不適切：記号、特殊文字 ]");
			return "redirect:/";
		}

		ShopEntity shopEntity = gourmetService.getGourmet(shopname);
		model.addAttribute("shopEntity",shopEntity); //左は画面に受け渡すときの名前 →右は実際に入る値の名前
		log.debug("[" + principal.getName() + "])グルメ情報検索結果:" + shopEntity.toString());
		return "gourmet/gourmet";
	}
}
