package il.neuralpsy.edustuff.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto implements Comparable<TaskDto>{
    private Integer taskId;
    private String name;
    private String content;
    private String subject;
    private String status = "AVAILABLE";

    private LocalDateTime startTime;


    @Override
    public int compareTo(TaskDto o) {
        return o.startTime.compareTo(this.startTime);
    }
}
