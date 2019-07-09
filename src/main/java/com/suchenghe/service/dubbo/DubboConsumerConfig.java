package com.suchenghe.service.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SuChenghe
 * @date 2019/4/23 11:47
 * Description: Dubbo 配置
 */
@Configuration
@DubboComponentScan(basePackages = "com.suchenghe.service.rpc")
public class DubboConsumerConfig {

    @Bean
    public ApplicationConfig applicationConfig() {
        //消费方应用信息
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("SchConsumer");
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        //远程服务调用超时时间(毫秒)
        consumerConfig.setTimeout(30000);
        //远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
        consumerConfig.setRetries(0);
        return consumerConfig;
    }


    @Bean
    public RegistryConfig registryConfig1() {
        //使用zookeeper注册中心暴露服务地址
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.3.3:2181");
        return registryConfig;
    }

    @Bean
    public RegistryConfig registryConfig2() {
        //使用zookeeper注册中心暴露服务地址
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://192.168.3.17:2181");
//        registryConfig.setDefault(false);
//        return registryConfig;
        return null;
    }

}
