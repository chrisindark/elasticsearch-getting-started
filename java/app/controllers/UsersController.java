package controllers;

import helpers.ElasticHelper;
import play.libs.Json;
import play.mvc.Result;
import com.google.inject.Inject;
import skeletons.responses.UserResponse;

public class UsersController extends BaseController
{
    private final ElasticHelper elasticHelper;

    @Inject
    public UsersController
    (
            ElasticHelper elasticHelper
    )
    {
        this.elasticHelper = elasticHelper;
    }

    public Result get(Long id)
    {
        UserResponse userResponse = this.elasticHelper.get("users", id, UserResponse.class);
        return ok(Json.toJson(userResponse));
    }
}
