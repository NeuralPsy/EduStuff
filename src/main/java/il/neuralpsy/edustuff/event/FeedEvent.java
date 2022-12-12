package il.neuralpsy.edustuff.event;

import il.neuralpsy.edustuff.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@SuppressWarnings("ALL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class FeedEvent implements Comparable<FeedEvent>{
    @Id
    @GeneratedValue
    private Integer eventId;
    private Integer eventObjectId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private EventType eventType;
    private AllowedFeedEvents feedDetails;
    private LocalDateTime timestamp;

    @Override
    public int compareTo(FeedEvent o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
