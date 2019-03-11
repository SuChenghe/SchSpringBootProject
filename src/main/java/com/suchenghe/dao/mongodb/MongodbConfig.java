//package com.suchenghe.dao.config.mongodb;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
///**
// * @author SuChenghe
// * @date 2018/11/30 18:08
// */
//@Component
//@PropertySource("classpath:jdbc/mongodb.properties")
//public class MongodbConfig {
//
//  final static Logger LOGGER = LoggerFactory.getLogger(MongodbConfig.class);
//
//  @Value("${mongodb.ip}")
//  private String ip;
//  @Value("${mongodb.port}")
//  private int port;
//  /**
//   * 数据库名称
//   */
//  @Value("${mongodb.database}")
//  private String database;
//  /**
//   * 用户名
//   */
//  @Value("${mongodb.username}")
//  private String username;
//  /**
//   * 用户密码
//   */
//  @Value("${mongodb.password}")
//  private String password;
//  /**
//   * 连接超时
//   */
//  @Value("${mongodb.connectTimeout}")
//  private int connectTimeout;
//
//  @Bean
//  public MongoClient getMongoClient(){
//    ServerAddress serverAddress = new ServerAddress(ip,port);
//
//    MongoCredential credential = MongoCredential
//        .createScramSha1Credential(username,database,password.toCharArray());
//
//    MongoClientOptions.Builder build = new MongoClientOptions.Builder();
//    build.connectTimeout(connectTimeout * 1000);
//    MongoClientOptions options = build.build();
//
//    MongoClient mongoClient = null;
//    try{
//      mongoClient = new MongoClient(serverAddress,credential,options);
//      LOGGER.info("++++++++++++++++++Create mongoClient successfully");
//    }catch (Exception e){
//      LOGGER.error("-----------------Create mongoClient failed");
//    }
//    return mongoClient;
//  }
//
//}
