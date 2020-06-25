package jp.ac.hcs.s3a208.task;


import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

/**
 * タスク情報に関する機能・画面を制御する
 */


@Slf4j
@Controller
public class TaskController {

		@Autowired
		TaskService taskService;

		/**
		 * タスク一覧画面を表示する
		 * @param prinsipal ログイン情報
		 * @param model
		 * @return タスク一覧画面
		 */
		@PostMapping("/task/tasklist")
		public String getTaskList(Principal principal, Model model) {

			log.info("[" + principal.getName() + "]タスク検索:" + principal.getName());

			TaskEntity taskEntity = taskService.selectAll(principal.getName());
			model.addAttribute("taskEntity",taskEntity);

			return "task/task";
		}

		/**
		 *新規データを登録する
		 */
	@PostMapping("/task/insert")//（）の中はURLを記載している
	public String postTaskInsert(@RequestParam("comment")String comment,@RequestParam("limitday")String limitday,
			Principal principal,
			Model model) {

		if (comment == null || comment.contentEquals("")) {
			log.warn("[" + principal.getName() + "]タスク登録データ：コメント未設定");
			getTaskList(principal,model);
		}
		Date dateLimitday = null;
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateLimitday = sdFormat.parse(limitday);
		} catch (ParseException e) {
			log.warn("[" + principal.getName() + "]タスク登録データ:日付形式がおかしい" + limitday);
			e.printStackTrace();
		}

		log.info("[" + principal.getName() + "]タスク登録データ:" + comment + "," + limitday);

		TaskData data = new TaskData();
		data.setUser_id(principal.getName());
		data.setComment(comment);
		data.setLimitday(dateLimitday);

		boolean result = taskService.insertOne(data);

		if (result) {
			log.info("[" + principal.getName() + "]タスク登録成功");
		}else {
			log.warn("[" + principal.getName() + "]タスク登録失敗");
		}

		return getTaskList(principal,model);
	}

	/**
	 *登録データの取得を行う
	 */
	@GetMapping("/task/delete/{id:.+}")
	public String getTaskDelete(@PathVariable("id") int id, Principal principal,Model model) {

		log.info("[" + principal.getName() + "]タスク削除:" + id);

		boolean result = taskService.deleteOne(id);

		if(result) {
			log.info("[" + principal.getName() + "]タスク削除成功");
			model.addAttribute("result","タスク削除成功");
		}else {
			log.warn("[" + principal.getName() + "]タスク削除失敗");
			model.addAttribute("result","タスク削除失敗");
		}
		return getTaskList(principal,model);

	}

	/**
	 * ソートをする画面
	 */
	@GetMapping("task/sort/{id:.+")
	pubilc String getTasksort(@PathVariable("id") int id , ) {

	}
}

