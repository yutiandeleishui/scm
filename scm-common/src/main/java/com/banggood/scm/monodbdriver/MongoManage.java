package com.banggood.scm.monodbdriver;


import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.TextSearchOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;

import java.io.Serializable;
import java.sql.Struct;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;


/**
 * Created by Administrator on 2017/9/25.
 */
public  class MongoManage<Entity extends Serializable>{
    //获取集合
    public    MongoCollection<Entity> getMongCollection(MongoDatabase database, String collectionName, Class<Entity> classType){
        MongoCollection<Entity> coll = database.getCollection(collectionName, classType);
        return coll;
    }

    //写一条记录
    public void mongSingleWrite( MongoCollection<Entity> coll,Entity entity){
       coll.insertOne(entity);
    }


    //写多条记录
    public void mongWrite( MongoCollection<Entity> coll,List<Entity> list){
        coll.insertMany(list);
    }

    //读取一条记录
    public  Entity getSingleEntity(MongoCollection<Entity> coll){
        return coll.find().first();
    }

    //根据条件获取第一条
    public  Entity getSingleEntityByWhere(MongoCollection<Entity> coll, Bson bson){
        return coll.find(bson).first();
    }
    //读取所有记录
    public  List<Entity> getEntityAll(MongoCollection<Entity> coll,Class<Entity> entityClass,Bson bson,int size,int currpage){
        List<Entity> list=new ArrayList<>();
        int sk=(currpage-1)*size;
        int lim=currpage*size;
        Block<Entity> block=(Entity entity)-> list.add(entity);
        coll.find(entityClass).sort(bson).skip(sk).limit(lim).forEach(block);
        return list;

    }
    //根据条件获取多条
    public  List<Entity> getEntityByWhere(MongoCollection<Entity> coll,Bson bson,Bson sortbson,Class<Entity> entityClass,int size,int currpage){
        List<Entity> list=new ArrayList<>();
        int sk=(currpage-1)*size;
        int lim=currpage*size;
        coll.find(bson,entityClass).sort(sortbson).skip(sk).limit(lim).forEach(new Block<Entity>() {
            @Override
            public void apply(Entity entity) {
                list.add(entity);
            }
        });
        return list;
    }
    public long gettotle(MongoCollection<Entity> coll){
        return coll.count();
    }
    public long gettotleByWhere(MongoCollection<Entity> coll,Bson bson){
       return coll.count(bson);
    }


    //修改
    public Boolean updateByentity(MongoCollection<Entity> coll,Bson bson,List<Bson> list){
        return coll.updateOne(bson, combine(list)).wasAcknowledged();
    }

    public long updateByManyentity(MongoCollection<Entity> coll,Bson bson,List<Bson> list){
        UpdateResult updateResult = coll.updateMany(bson, combine(list));
        return updateResult.getModifiedCount();
    }
    //删除
    public Boolean delectByentity(MongoCollection<Entity> coll,Bson bson){
        return coll.deleteOne(bson).wasAcknowledged();
    }

    //删除
    public long delectByManyentity(MongoCollection<Entity> coll,Bson bson){
        DeleteResult deleteResult = coll.deleteMany(bson);
        return  deleteResult.getDeletedCount();
    }


    //单升序指数
    public String createIndexByAscending(MongoCollection<Entity> coll,String filed){
       return coll.createIndex(Indexes.ascending(filed));
    }

    //复合升序指数
    public String createIndexByAscending(MongoCollection<Entity> coll,List<String> filed){
        return coll.createIndex(Indexes.ascending(filed));
    }

    //单降序密钥索引
    public String createIndexByDescending(MongoCollection<Entity> coll,String filed){
        return coll.createIndex(Indexes.descending(filed));
    }

    //复方降序密钥索引
    public String createIndexByDescending(MongoCollection<Entity> coll,List<String> filed){
        return coll.createIndex(Indexes.descending(filed));
    }

    //创建文本索引
    public String createIndexByText(MongoCollection<Entity> coll,String filed){
        return coll.createIndex(Indexes.text(filed));
    }
    //创建散列索引
    public String createIndexByHashed(MongoCollection<Entity> coll,String filed){
        return coll.createIndex(Indexes.hashed(filed));
    }
    //创建地理空间索引
    public String createIndexByGeo2dsphere(MongoCollection<Entity> coll,String filed){
        return coll.createIndex(Indexes.geo2dsphere(filed));
    }

    //创建唯一索引
    public String createIndexByUnique(MongoCollection<Entity> coll,String filed){
        IndexOptions indexOptions = new IndexOptions().unique(true);
        return coll.createIndex(Indexes.ascending(filed),indexOptions);
    }

    //创建部分索引
    public String createIndexByPartial(MongoCollection<Entity> coll,Bson bson,String filed){
        IndexOptions partialFilterIndexOptions = new IndexOptions().partialFilterExpression(Filters.exists(filed));
        return coll.createIndex(bson, partialFilterIndexOptions);
    }

}
