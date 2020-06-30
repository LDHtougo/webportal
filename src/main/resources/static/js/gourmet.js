/**
 * グルメ検索機能用のJavaScriptファイルです
 */
function check(){
	if (shop_form.shopname.value == "" || shop_form.shopname.value == null){
    	alert("タスク名は必須項目です。入力してください。");
    	return false;
	}

	return Boolean(check2());
}
function check2(){
	if (shop_form.shopname.value.length > 50){
    	alert("文字数制限を超えています。50文字以内で入力してください。");
    	return false;
	}

	return Boolean(check3());
}


function check3() {
	  var reg = new RegExp(/[!"#$%&'()\*\+\-\.,\/:;<=>?@\[\\\]^_`{|}~]/g);
	  if(reg.test(shop_form.shopname.value)) {
		  alert("記号や特殊文字は入力できません。再入力してください。");
	    return false;
	  }
	  return true;
}