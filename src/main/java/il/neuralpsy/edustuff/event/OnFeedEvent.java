package il.neuralpsy.edustuff.event;

import il.neuralpsy.edustuff.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OnFeedEvent{
    private int userId;
    private int entityId;
    private AllowedFeedEvents feedDetails;

}
