package science.monke.util.task.repository

import org.springframework.data.repository.CrudRepository
import science.monke.util.task.entity.TaskLogEntity

interface TaskLogRepository : CrudRepository<TaskLogEntity, Int>