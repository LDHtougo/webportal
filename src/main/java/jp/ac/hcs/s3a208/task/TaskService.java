package jp.ac.hcs.s3a208.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスク情報を操作する
 */
@Transactional
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	public TaskEntity selectAll(String user_id) {
		TaskEntity taskEntity = taskRepository.selectAll(user_id);

		return taskEntity;
	}

}
