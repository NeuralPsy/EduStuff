package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.event.AllowedFeedEvents;
import il.neuralpsy.edustuff.event.EventType;
import il.neuralpsy.edustuff.model.User;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;

public class FeedEventDto {
    private Integer eventId;
    private EventType eventType;
    private AllowedFeedEvents feedDetails;
    private Timestamp timestamp;
}
