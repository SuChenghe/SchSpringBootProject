//package com.suchenghe.dao;
//
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.result.UpdateResult;
//import com.suchenghe.SchprojectApplication;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.bson.Document;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @author SuChenghe
// * @date 2018/6/17 14:29
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SchprojectApplication.class)
//public class MongodbDaoTest {
//
//  SimpleDateFormat sdf = new SimpleDateFormat("MMdd_HHmmss");
//
//  @Autowired
//  MongodbBaseDao mongodbDao;
//
//  @Test
//  public void insertDocument() {
//    String sdfStr = sdf.format(new Date());
//    List<Document> documents = new ArrayList<>();
//
//    for (int i = 0; i < 3; i++) {
//      Document document = new Document("title", "title" + sdfStr).
//          append("description", "database" + sdfStr).
//          append("by", "Suchenghe");
//      documents.add(document);
//    }
//
//    mongodbDao.insertMany("mydb", "SchMessage", documents);
//    System.out.println("文档插入成功");
//  }
//
//  @Test
//  public void updateDocument() {
//    UpdateResult updateResult = mongodbDao
//        .updateMany("mydb", "SchMessage", Filters.eq("by", "Suchenghe_update"),
//            new Document("$set", new Document("by", "Suchenghe_update4")));
//    long matchCount = updateResult.getMatchedCount();
//    long modifiedCount = updateResult.getModifiedCount();
//    System.out.println("匹配:" + matchCount + ";修改:" + modifiedCount + ";文档更新成功");
//  }
//
//  @Test
//  public void findDocument() {
//    List<Document> documentList = mongodbDao
//        .find("mydb", "SchMessage", Filters.eq("by", "Suchenghe_update4"));
//    for (Document document : documentList) {
//      System.out
//          .println("_id:" + document.getObjectId("_id") + ";title:" + document.getString("title"));
//    }
//  }
//}
