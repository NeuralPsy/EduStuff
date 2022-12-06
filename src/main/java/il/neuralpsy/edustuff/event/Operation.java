package il.neuralpsy.edustuff.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Operation {
    CREATE(1),
    ADD(2),
    UPDATE(3),
    DELETE(4);

    private final int id;

}
