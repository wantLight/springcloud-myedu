package com.wsq.edu.config;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-10-8 18:45
 */
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lulu
 * @Date 2019/6/7 18:27
 */
@Configuration
public class EsConfig {

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public RestHighLevelClient client(){
        RestHighLevelClient client=new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http")
                        //这里如果要用client去访问其他节点，就添加进去
                )
        );
        return client;
    }
}

