package com.suchenghe.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 一、get方法：getForEntity、getForObject
 * 二、post方法：postForEntity、postForObject
 * 三、put方法：put
 * 四、delete方法：delete
 *
 * @author SuChenghe
 * @date 2018/4/20 15:20
 */
@Component
public class RestTemplateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtils.class);

    @Autowired
    RestTemplate restTemplate;

    /**
     * 解决乱码问题
     */
    @PostConstruct
    void changeConverter() {
        restTemplate.getMessageConverters()
                .set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    /**
     * POST请求
     */
    public String postForObject(String url, String body, HashMap hashMap) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity requestEntity = new HttpEntity(body, requestHeaders);

        String response = restTemplate.postForObject(url, requestEntity, String.class, hashMap);
        return response;
    }

    /**
     * GET请求
     */
    public String getForObject(String url, HashMap hashMap) {
        String response = restTemplate.getForObject(url, String.class, hashMap);
        return response;
    }


}
