package com.suchenghe.service.dubbo.rpc;

import com.alibaba.dubbo.config.annotation.Reference;
import com.suchenghe.service.dubbo.SchRpcInterface;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author SuChenghe
 * @date 2019/4/23 11:47
 * Description: Dubbo 配置
 */
@Service
@EnableScheduling
public class SchRpcConsumer {

    @Reference
    SchRpcInterface schRpcInterface;

    @Scheduled(cron = "0/10 * * * * ?")
    private void getMsg(){
        System.out.println("++++++++++++++++++++++"+schRpcInterface.getMsg());
    }

}
