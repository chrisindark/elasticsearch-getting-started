package services;

import models.BaseModel;

public interface ElasticService
{
    <T> T get(String index, Long id, Class<T> toClass);

    Boolean index(String index, Long id, BaseModel document);

    Boolean index(String index, BaseModel document);
}
