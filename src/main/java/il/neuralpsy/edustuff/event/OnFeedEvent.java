package il.neuralpsy.edustuff.event;

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
