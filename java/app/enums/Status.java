package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum Status
{
    INACTIVE(0),
    ACTIVE(1);

    @Getter
    @Setter
    private int status;
}
