package com.suchenghe.sevice;

import com.suchenghe.SchprojectApplication;
import com.suchenghe.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SuChenghe
 * @date 2018/12/4 14:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchprojectApplication.class)
public class RedisServiceTest {
  @Autowired
  RedisService redisService;

  @Test
  public void putValue(){
    redisService.putValue("123","abc");
  }

  @Test
  public void getValue(){
    String value = redisService.getKeyValue("123");
    System.out.println(value);
  }

}
