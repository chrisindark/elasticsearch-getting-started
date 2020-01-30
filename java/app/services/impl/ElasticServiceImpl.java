package services.impl;

import models.BaseModel;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import play.libs.Json;
import services.ElasticService;
import utils.Utils;

import java.util.Date;

public class ElasticServiceImpl implements ElasticService
{
    private final RestHighLevelClient client;

    private Long createId()
    {
        return ((new Date()).getTime() / 1000);
    }

    public ElasticServiceImpl()
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
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return response;
    }

    public Boolean index(String index, Long id, BaseModel document)
    {
        Boolean isSuccess = false;

        IndexRequest indexRequest = new IndexRequest(index).id(id.toString()).source(Json.toJson(document).toString().getBytes(), XContentType.JSON);
        try
        {
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            RestStatus status = indexResponse.status();
            isSuccess = ((status.equals(RestStatus.CREATED)) || (status.equals(RestStatus.ACCEPTED)));
        }
        catch(Exception ex)
        {
            //log
            String sh = "sh";
            ex.printStackTrace();

        }

        return isSuccess;
    }

    @Override
    public Boolean index(String index, BaseModel document)
    {
        Long id = this.createId();
        document.setId(id);
        return this.index(index, id, document);
    }


}
