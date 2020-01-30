package services.impl;

import com.google.inject.Inject;
import models.User;
import services.ElasticService;
import services.UserService;
import skeletons.requests.UserRequest;
import skeletons.responses.UserResponse;

import java.util.Date;

public class UserServiceImpl implements UserService
{

    private final ElasticService elasticService;

    @Inject
    UserServiceImpl
    (
            ElasticService elasticService
    )
    {
        this.elasticService = elasticService;
    }

    @Override
    public UserResponse get(Long id)
    {
        UserResponse userResponse = this.elasticService.get("users", id, UserResponse.class);

        return userResponse;
    }

    @Override
    public UserResponse create(UserRequest request)
    {
        UserResponse userResponse = null;

        Date now = new Date();

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setType(request.getType().ordinal());
        user.setStatus(request.getStatus().ordinal());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        Boolean success = this.elasticService.index("users", user);

        if(success)
        {
            userResponse = new UserResponse(user);
        }

        return userResponse;
    }
}
