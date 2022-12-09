package il.neuralpsy.edustuff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedEventDto implements Comparable<FeedEventDto>{
    private Integer eventId;
    private String objectName;
    private Integer objectId;
    private String operationType;
    private String userName;

    private LocalDateTime timestamp;

    @Override
    public int compareTo(FeedEventDto o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
