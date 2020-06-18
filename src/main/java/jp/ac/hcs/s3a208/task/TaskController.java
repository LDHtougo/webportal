package jp.ac.hcs.s3a208.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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

}

