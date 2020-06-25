package jp.ac.hcs.s3a208.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public TaskEntity selectAll(String userId)throws DataAccessException {
		return taskRepository.selectAll(userId);
	}

	public boolean insertOne(TaskData taskdata) throws DataAccessException{
		int rowNumber = taskRepository.insertOne(taskdata);
		boolean result = (rowNumber > 0) ? true : false;
		return result;
	}

	/**
	 *
	 */
	public boolean deleteOne(int id)throws DataAccessException{
		int rowNumber = taskRepository.deleteOne(id);
		boolean result = (rowNumber > 0)? true : false;
		return result;
	}
}
