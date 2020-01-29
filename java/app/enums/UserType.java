package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum UserType
{
    DEFAULT(0),
    ADMIN(1);

    @Getter
    @Setter
    private int type;
}
