package il.neuralpsy.edustuff.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
    TASK(1),
    COMMENT(2);

    private final Integer eventTypeId;
}
