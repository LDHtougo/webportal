/**
 * タスク管理機能用のJavaScriptファイルです
 */
function check(){
	if (task_form.comment.value == "" || task_form.comment.value == null){// task.htmlで名前を宣言される
    	alert("タスク名は必須項目です。入力してください。");
    	return false;
	}
}