package skeletons.requests;

import enums.Status;
import enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest
{
    private String username;

    private String email;

    private String password;

    private UserType type = UserType.DEFAULT;

    private Status status = Status.ACTIVE;
}
