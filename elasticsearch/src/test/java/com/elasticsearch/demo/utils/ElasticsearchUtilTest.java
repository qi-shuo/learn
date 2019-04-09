package com.elasticsearch.demo.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchUtilTest {
    @Resource
    private ElasticsearchUtil elasticsearchUtil;

    @Test
    public void isIndexExist() {
        System.out.println(elasticsearchUtil.isIndexExist("movie"));
    }

    @Test
    public void searchDataById() {
        elasticsearchUtil.searchDataById("movie", "adventure", "2").forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }
}