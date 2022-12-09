package il.neuralpsy.edustuff.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AllowedFeedEvents {

    TAKE_TASK(Operation.TAKEN, EventType.TASK),
    CREATE_TASK(Operation.CREATED, EventType.TASK),
    UPDATE_TASK(Operation.UPDATED, EventType.TASK),
    DELETE_TASK(Operation.DELETED, EventType.TASK),
    ADD_COMMENT(Operation.ADDED, EventType.COMMENT),
    DELETE_COMMENT(Operation.DELETED, EventType.COMMENT);

    private final Operation operation;
    private final EventType type;
}
