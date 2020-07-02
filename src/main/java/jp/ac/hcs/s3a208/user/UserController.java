package jp.ac.hcs.s3a208.user;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * ユーザ情報に関する機能・画面を制御する.
 */
@Slf4j
@Controller
public class UserController {

	@Autowired
	UserService userService;

	/** 権限のラジオボタン用変数 */
	private Map<String, String> radioRole;

	/** 権限のラジオボタンを初期化する処理 */
	private Map<String, String> initRadioRole() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("管理", "ROLE_ADMIN");
		radio.put("一般", "ROLE_GENERAL");
		return radio;
	}

	/**
	 * ユーザ一覧画面を表示する.
	 * @param model
	 * @return ユーザ一覧画面
	 */
	@GetMapping("/user/userList")
	public String getUserList(Model model) {

		UserEntity userEntity = userService.selectAll();
		model.addAttribute("userEntity", userEntity);

		return "user/userList";
	}

	/**
	 * ユーザ詳細情報画面を表示する.
	 * @param form 更新時の入力チェック用UserForm
	 * @param user_id 検索するユーザID
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ詳細情報画面
	 */
	@GetMapping("/user/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute UserFormForUpdate form,
			@PathVariable("id") String user_id,
			Principal principal,
			Model model) {

		// ラジオボタンの準備
		radioRole = initRadioRole();
		model.addAttribute("radioRole", radioRole);

		// 検索するユーザーIDのチェック
		if (user_id != null && user_id.length() > 0) {

			log.info("[" + principal.getName() + "]ユーザ検索:" + user_id);

			UserData data = userService.selectOne(user_id);

			// データ表示準備(パスワードは暗号化済の為、表示しない)
			form.setUser_id(data.getUser_id());
			form.setUser_name(data.getUser_name());
			form.setDarkmode(data.isDarkmode());
			form.setRole(data.getRole());
			model.addAttribute("userFormForUpdate", form);
		}

		return "user/userDetail";
	}

	/**
	 * 1件分のユーザ情報でデータベースを更新する.
	 * パスワードの更新が不要の場合は、画面側で何も値を設定しないものとする.
	 * @param form 更新するユーザ情報(パスワードは平文)
	 * @param bindingResult データバインド実施結果
	 * @param principal ログイン情報
	 * @param model
	 * @return ユーザ一覧画面
	 */
	@PostMapping(value = "/user/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute @Validated UserFormForUpdate form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {

		// 入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserDetail(form, form.getUser_id(), principal, model);
		}

		log.info("[" + principal.getName() + "]ユーザ更新:" + form.toString());

		// ダークモードは更新しない為、設定無し
		UserData data = new UserData();
		data.setUser_id(form.getUser_id());
		data.setUser_name(form.getUser_name());
		data.setRole(form.getRole());

		boolean result = false;

		if (form.getPassword() == null || form.getPassword().equals("")) {
			// パスワード更新無
			result = userService.updateOne(data);
		} else {
			// パスワード更新有
			data.setPassword(form.getPassword());
			result = userService.updateOneWithPassword(data);
		}

		if (result) {
			log.info("[" + principal.getName() + "]ユーザ更新成功");
			model.addAttribute("result", "ユーザ更新成功");
		} else {
			log.warn("[" + principal.getName() + "]ユーザ更新失敗");
			model.addAttribute("result", "ユーザ更新失敗");
		}

		return getUserList(model);
	}

	@PostMapping("/user/userAdmin")
	public String getuserAdmin(Model model) {

		UserEntity userEntity = userService.selectAll();
		model.addAttribute("userEntity", userEntity);

		return "user/userList";
	}

	@GetMapping("/user/userCreate")
	public String getUserCreate(@ModelAttribute UserForm form,Model model) {
		// ラジオボタンの準備
		radioRole = initRadioRole();
		model.addAttribute("radioRole", radioRole);
		return "user/userCreate";
	}
	@PostMapping("/user/userCreate")
	public String getUserCreate(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {


		if(bindingResult.hasErrors()) {//入力チェック
			return getUserCreate(form,model);
		}

		// ダークモードは更新しない為、設定無し
		UserData data = new UserData();
		data.setUser_id(form.getUser_id());
		data.setPassword(form.getPassword());
		data.setUser_name(form.getUser_name());
		data.setRole(form.getRole());

		boolean result = false;

		result = userService.insertOne(data);


		if (result) {
			log.info("[" + principal.getName() + "]ユーザ登録成功");
			model.addAttribute("result", "ユーザ登録成功");
		} else {
			log.warn("[" + principal.getName() + "]ユーザ登録失敗");
			model.addAttribute("result", "ユーザ登録失敗");
		}

		return getUserList(model);


	}

}