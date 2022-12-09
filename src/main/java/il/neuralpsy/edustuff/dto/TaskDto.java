package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private Integer taskId;
    private String name;
    private String content;
    private String subject;
    private LocalDateTime startTime;
    private TaskStatus taskStatus;

}
