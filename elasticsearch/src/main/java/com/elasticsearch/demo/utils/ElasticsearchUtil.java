package com.elasticsearch.demo.utils;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019/4/9 3:34 PM
 */
@Component
public class ElasticsearchUtil {
    @Resource
    private TransportClient transportClient;

    public boolean isIndexExist(String index) {
        IndicesExistsResponse indicesExistsResponse = transportClient.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        return indicesExistsResponse.isExists();
    }

    public Map<String, Object> searchDataById(String index, String type, String id){
        GetRequestBuilder getRequestBuilder = transportClient.prepareGet(index, type, id);
        return getRequestBuilder.execute().actionGet().getSource();
    }
}
