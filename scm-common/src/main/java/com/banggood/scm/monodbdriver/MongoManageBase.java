package com.banggood.scm.monodbdriver;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Created by Administrator on 2017/9/26.
 */
public class MongoManageBase {

    //连接服务器
    public MongoClient mongConnect(String address, int port, String user, String database, String password, boolean isSSL){
        port=port==0? 27017:port;
        ServerAddress serverAddress= new ServerAddress(address, port);
        MongoCredential credential = MongoCredential.createScramSha1Credential(user,database,password.toCharArray());
        if (isSSL) {
            MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
            MongoClient mongoClient = new MongoClient(serverAddress, asList(credential),options);
            return  mongoClient;
        }else {
            MongoClient mongoClient = new MongoClient(serverAddress, asList(credential));
            return  mongoClient;
        }
    }

    public  MongoClient MongConnectCluster(Map<String,Integer> address, String user, String database, String password, boolean isSSL){
        //如果是集群
        List<ServerAddress> serverAddressList=new ArrayList<ServerAddress>();
        address.forEach((x,y)->{
            serverAddressList.add(new ServerAddress(x, y));
        });
        MongoCredential credential = MongoCredential.createScramSha1Credential(user,database,password.toCharArray());
        if (isSSL) {
            MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
            MongoClient mongoClient = new MongoClient(serverAddressList, asList(credential),options);
            return  mongoClient;
        }else {
            MongoClient mongoClient = new MongoClient(serverAddressList, asList(credential));
            return  mongoClient;
        }
    }

    //获取数据库
    public MongoDatabase getMongDataBase(MongoClient  mongoClient, String dbName){
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        // get handle to "mydb" database
        MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
        return database;
    }

    //获取集合列表名称
    public List<String> getMongCollectionNames( MongoDatabase database){
        List<String> list=new ArrayList<>();
        for (String name : database.listCollectionNames()) {
            list.add(name);
        }
        return  list;
    }

    //删除集合
    public void deleteMongcollection(MongoDatabase database,String collectionName){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.drop();
    }
}
