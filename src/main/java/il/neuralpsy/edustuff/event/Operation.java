package il.neuralpsy.edustuff.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Operation {
    ADD(1),
    UPDATE(2),
    DELETE(3),

    CREATE(4),

    TAKE(5);

    private final int id;

}
