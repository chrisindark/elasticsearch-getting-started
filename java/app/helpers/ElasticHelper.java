package helpers;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestClient;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import play.libs.Json;
import org.elasticsearch.client.RequestOptions;
import skeletons.responses.BaseResponse;
import skeletons.responses.UserResponse;
import utils.Utils;

public class ElasticHelper
{
    private final RestHighLevelClient client;

    public ElasticHelper()
    {
        client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost(System.getenv("ELASTIC_IP_HTTP"), Integer.valueOf(System.getenv("ELASTIC_PORT_HTTP")), System.getenv("ELASTIC_SCHEME"))
            )
        );
    }

    public <T> T get(String index, Long id, Class<T> toClass)
    {
        T response = null;
        try
        {
            GetRequest getRequest = new GetRequest(index, id.toString());
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

            response = Utils.convertObject(getResponse.getSource(), toClass);
            System.out.println(Json.toJson(getResponse));
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return response;
    }


}
