package skeletons.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.Status;
import enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.User;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse extends BaseResponse
{
    private String username;

    private String email;

    private UserType type;

    private Status status;

    private Date createdAt;

    private Date updatedAt;

    public UserResponse(User user)
    {
        super(user.getId());
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.type = UserType.values()[user.getType()];
        this.status = Status.values()[user.getStatus()];
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
