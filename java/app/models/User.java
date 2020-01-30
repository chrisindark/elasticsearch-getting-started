package models;

import enums.Status;
import enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User extends BaseModel
{
    private String username;

    private String email;

    private String password;

    private Integer type;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;
}
