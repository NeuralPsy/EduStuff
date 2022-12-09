package il.neuralpsy.edustuff.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Operation {
    ADDED(1),
    UPDATED(2),
    DELETED(3),

    CREATED(4),

    TAKEN(5);

    private final int id;

}
