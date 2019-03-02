package com.lnsoft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By Chr on 2019/3/1/0001.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRestTemplate {

    @Test
    public void contextLoads() {

        Map<String,String> params=new HashMap<>();
        params.put("tableName", "emp");
        params.put("objId", "7788");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8085/Inter-Manage2/im/pms?tableName=emp&objId=7788", String.class);
        String body = forEntity.getBody();
        System.err.println(body);

        //该方法未成功
//        String forObject = new RestTemplate().getForObject("http://localhost:8085/Inter-Manage2/im/pms", String.class, params);
//        System.err.println(forObject.getBytes());
    }
}
