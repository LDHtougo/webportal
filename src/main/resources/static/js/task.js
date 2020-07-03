/**
 * タスク管理機能用のJavaScriptファイルです
 */
function check(){
	if (task_form.task_name.value == "" || task_form.task_name.value == null){// task.htmlで名前を宣言される
    	alert("タスク名は必須項目です。入力してください。");
    	return false;
	}else if (task_form.comment.value == "" || task_form.comment.value == null){// task.htmlで名前を宣言される
		alert("タスクの内容は必須項目です。入力してください。");
    	return false;
	}else if (task_form.limitday.value == "" || task_form.limitday.value == null){// task.htmlで名前を宣言される
    	alert("日付は必須項目です。入力してください。");
    	return false;
	}
}
