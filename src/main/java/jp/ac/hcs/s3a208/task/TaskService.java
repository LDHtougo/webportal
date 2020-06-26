package jp.ac.hcs.s3a208.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author s20183065
 *
 */
@Transactional
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	/**
	 * タスクの一覧表示
	 * @param userId
	 * @return ユーザID
	 * @throws DataAccessException
	 */
	public TaskEntity selectAll(String userId)throws DataAccessException {
		return taskRepository.selectAll(userId);
	}

	/**
	 * タスクの追加処理を行う.
	 * @param taskdata タスクの内容
	 * @return タスクのデータ
	 * @throws DataAccessException
	 */
	public boolean insertOne(TaskData taskdata) throws DataAccessException{
		int rowNumber = taskRepository.insertOne(taskdata);
		boolean result = (rowNumber > 0) ? true : false;
		return result;
	}

	/**
	 * タスクの削除処理を行う.
	 * @param id ユーザID
	 * @return 結果(true,false)
	 * @throws DataAccessException
	 */
	public boolean deleteOne(int id)throws DataAccessException{
		int rowNumber = taskRepository.deleteOne(id);
		boolean result = (rowNumber > 0)? true : false;
		return result;
	}
}
