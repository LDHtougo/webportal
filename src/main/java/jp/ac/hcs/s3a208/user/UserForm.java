package jp.ac.hcs.s3a208.user;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * アップデート用にパスワード、ダークモード、権限のチェックを外したUserForm.
 * その他の内容はUserFormに準じる.
 * 管理者用
 */
@Data
public class UserForm {

	/** ユーザID（メールアドレス）*/
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")     //メール形式チェック
	private String user_id;

	/** パスワード */
	@NotBlank(message = "{require_check}")
	@Length(min = 4,max = 100,message = "{length_check")
	@Pattern(regexp = "^[a-zA-Z0-9]+$",message = "{pattern_check}")
	private String password;

	/** ユーザ名 */
	@NotBlank(message = "{require_check}")
	private String user_name;

	/** ダークモードフラグ */
	@AssertFalse
	private boolean darkmode;

	/** 権限 */
	@NotBlank(message = "{require_check}")
	private String role;

}
