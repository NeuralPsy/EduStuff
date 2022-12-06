package il.neuralpsy.edustuff.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AllowedFeedEvents {

    ADD_TASK(Operation.ADD, EventType.TASK),
    UPDATE_TASK(Operation.UPDATE, EventType.TASK),
    DELETE_TASK(Operation.DELETE, EventType.TASK),
    ADD_COMMENT(Operation.ADD, EventType.COMMENT),
    DELETE_COMMENT(Operation.DELETE, EventType.COMMENT);

    private final Operation operation;
    private final EventType type;
}
