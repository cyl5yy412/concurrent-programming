package com.lnsoft.test;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 该方法测试RestTemplate是否对HttpClient封装，是否可用
 * </p>
 * Created By Chr on 2019/1/30/0030.
 */
public class TestRestTemplate {


    //自动注入RestTemplate
    private final RestTemplate restTemplate;

    public TestRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getEmpInfo() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8085/Inter-Manage2/im/pms?tableName=emp&objId=7788", String.class);
        String body = forEntity.getBody();
        System.err.println(body);
        return body;
    }

    public String getUserInfo() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8085/Inter-Manage2/im/user?tableName=user&objId=8", String.class);
        String body = forEntity.getBody();
        System.err.println(body);
        return body;
    }

    public static void main(String[] args) {
        //通过构造方法获得自动注入的restTemplate，
        new TestRestTemplate(new RestTemplate()).getEmpInfo();
        new TestRestTemplate(new RestTemplate()).getUserInfo();
    }


}
