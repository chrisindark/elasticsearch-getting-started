package skeletons.responses;

import enums.Status;
import enums.UserType;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserResponse extends BaseResponse
{
    private Long id;

    private String username;

    private String email;

    private String password;

    private UserType type;

    private Status status;
}
