//package com.suchenghe.dao;
//
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.result.UpdateResult;
//import java.util.ArrayList;
//import java.util.List;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
///**
// * @author SuChenghe
// * @date 2018/12/3 17:26
// */
//@Repository
//public class MongodbBaseDao {
//
//    /**
//     * 通过连接认证获取MongoDB连接
//     */
//    @Autowired
//    private MongoClient mongoClient;
//
//    /**
//     * 获取数据库
//     */
//    protected MongoDatabase getMongoDatabase(String database){
//        return mongoClient.getDatabase(database);
//    }
//
//    /**
//     * 获取集合
//     * @param database
//     * @param collectionName
//     * @return
//     */
//    protected MongoCollection getMongoCollection(String database,String collectionName){
//        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
//        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
//        return collection;
//    }
//
//    /**
//     * 插入单个文档
//     * @param database
//     * @param collectionName
//     * @param document
//     */
//    public void insertOne(String database,String collectionName,Document document) {
//        MongoCollection mongoCollection = getMongoCollection(database,collectionName);
//        mongoCollection.insertOne(document);
//    }
//
//
//    /**
//     * 插入多个文档
//     *
//     * eg.
//     *    List<Document> documents = new ArrayList<Document>();
//     *
//     *    Document document = new Document("title", "title"+sdfStr).
//     *    append("description", "database"+sdfStr).
//     *    append("by", "Suchenghe");
//     *
//     *    documents.add(document);
//     *
//     *    mongodbService.insertManyDocuments("mydb","SchMessage",documents);
//     *    System.out.println("文档插入成功");
//     *
//     * @param database
//     * @param collectionName
//     * @param documents
//     */
//    public void insertMany(String database,String collectionName,List<Document> documents) {
//        MongoCollection mongoCollection = getMongoCollection(database,collectionName);
//        mongoCollection.insertMany(documents);
//    }
//
//    /**
//     * 更新某个文档
//     * @param database
//     * @param collectionName
//     * @param bson
//     * @param document
//     */
//    public UpdateResult updateOne(String database, String collectionName, Bson bson, Document document) {
//        MongoCollection mongoCollection = getMongoCollection(database,collectionName);
//        return mongoCollection.updateOne(bson,document);
//    }
//
//    /**
//     * 更新多个文档
//     * eg. UpdateResult updateResult = mongodbService.updateManyDocument("mydb","SchMessage", Filters.eq("by", "Suchenghe_update"), new Document("$set",new Document("by","Suchenghe_update4")));
//     *     long matchCount = updateResult.getMatchedCount();
//     *     long modifiedCount = updateResult.getModifiedCount();
//     *     System.out.println("匹配:"+matchCount+";修改:"+modifiedCount+";文档更新成功");
//     *
//     * @param database
//     * @param collectionName
//     * @param bson
//     * @param document
//     */
//    public UpdateResult updateMany(String database, String collectionName, Bson bson,Document document) {
//        MongoCollection mongoCollection = getMongoCollection(database,collectionName);
//        return mongoCollection.updateMany(bson,document);
//    }
//
//    /**
//     * 检索所有的文档
//     * 1. 获取迭代器FindIterable<Document>
//     * 2. 获取游标MongoCursor<Document>
//     * 3. 通过游标遍历检索出的文档集合
//     *
//     * eg.  List<Document> documentList = mongodbService.findDocument("mydb","SchMessage", Filters.eq("by", "Suchenghe_update4"));
//     *      for(Document document:documentList){
//     *          System.out.println("_id:"+document.getObjectId("_id")+";title:"+document.getString("title"));
//     *      }
//     *
//     * @param database
//     * @param collectionName
//     * @param bson
//     */
//    public List<Document> find(String database, String collectionName, Bson bson) {
//        MongoCollection mongoCollection = getMongoCollection(database,collectionName);
//
//        FindIterable<Document> findIterable = mongoCollection.find(bson);
//        MongoCursor<Document> mongoCursor = findIterable.iterator();
//
//        List<Document> documentList = new ArrayList<Document>();
//
//        while(mongoCursor.hasNext()){
//            documentList.add(mongoCursor.next());
//        }
//        return documentList;
//    }
//
//}
