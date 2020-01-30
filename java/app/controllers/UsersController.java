package controllers;

import play.mvc.Http;
import services.UserService;
import play.libs.Json;
import play.mvc.Result;
import com.google.inject.Inject;
import skeletons.requests.UserRequest;
import skeletons.responses.UserResponse;
import utils.Utils;


public class UsersController extends BaseController
{
    private final UserService userService;

    @Inject
    public UsersController
    (
            UserService userService
    )
    {
        this.userService = userService;
    }

    public Result get(Long id)
    {
        UserResponse userResponse = this.userService.get(id);
        return ok(Json.toJson(userResponse));
    }

    public Result create(Http.Request request)
    {
        UserRequest userRequest = null;

        try
        {
            userRequest = Utils.convertObject(request.body().asJson(), UserRequest.class);
        }
        catch(Exception ex)
        {
            throw new RuntimeException("Bad Request");
        }

        UserResponse userResponse = this.userService.create(userRequest);
        return ok(Json.toJson(userResponse));
    }
}
