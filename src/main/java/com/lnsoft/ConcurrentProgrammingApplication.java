package com.lnsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConcurrentProgrammingApplication {
    /**
     * spring框架提供的RestTemplate类可用于在应用中调用rest服务，它简化了与http服务的通信方式，
     * 统一了RESTful的标准，封装了http链接， 我们只需要传入url及返回值类型即可。
     * 相较于之前常用的HttpClient，RestTemplate是一种更优雅的调用RESTful服务的方式。
     * RestTemplate默认依赖JDK提供http连接的能力（HttpURLConnection），
     * 如果有需要的话也可以通过setRequestFactory方法替换为例如 Apache HttpComponents、Netty或OkHttp等其它HTTP library。
     *
     * @return
     */

    public static void main(String[] args) {
        SpringApplication.run(ConcurrentProgrammingApplication.class, args);
    }

}

