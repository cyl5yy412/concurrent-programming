package com.lnsoft.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 该方法测试JSONObject的putAll()方法把接口返回的参数拼接
 * Created By Chr on 2019/1/30/0030.
 */
@Component
public class TestFastJson {

    public String getInfo() {
        //emp用户信息
        String empInfo = new TestRestTemplate(new RestTemplate()).getEmpInfo();

        JSONObject empJson = JSONObject.parseObject(empInfo);
        //user用户信息
        String userInfo = new TestRestTemplate(new RestTemplate()).getUserInfo();

        JSONObject userJson = JSONObject.parseObject(userInfo);

        JSONObject result = new JSONObject();
        //我怎么没看出来putAll()能把，两个接口的结果结合在一起呢
        result.putAll(userJson);
        result.putAll(empJson);

        System.err.println(result.toString());
        return result.toString();

    }
    public String getEmpInfo() {
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8085/Inter-Manage2/im/pms?tableName=emp&objId=7788", String.class);
        String body = forEntity.getBody();
        System.err.println(body);
        return body;
    }

    public String getUserInfo() {
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8085/Inter-Manage2/im/user?tableName=user&objId=8", String.class);
        String body = forEntity.getBody();
        System.err.println(body);
        return body;
    }

    public static void main(String args[]){
        new TestFastJson().getInfo();
    }
}
