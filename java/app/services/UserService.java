package services;

import skeletons.requests.UserRequest;
import skeletons.responses.UserResponse;

public interface UserService
{
    UserResponse get(Long id);

    UserResponse create(UserRequest request);
}
