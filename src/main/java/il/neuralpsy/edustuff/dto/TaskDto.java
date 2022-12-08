package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.TaskStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskDto {
    private Integer taskId;
    private String name;
    private String content;
    private String subject;
    private Timestamp startTime;
    private TaskStatus taskStatus;

}
