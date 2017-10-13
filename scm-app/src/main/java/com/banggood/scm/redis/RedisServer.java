package com.banggood.scm.redis;

import com.banggood.scm.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class RedisServer<T> {

    private RedisTemplate<String,T> redisTemplate;

    public  RedisServer(RedisTemplate<String,T> redisTemplate){
        super();
        this.redisTemplate=redisTemplate;
    }
    public  void setRedisServer(String key, T value){
        redisTemplate.opsForValue().set(key,value);
    }

    public T  getRedisServer(String key){
       return   redisTemplate.opsForValue().get(key);
    }

    ///执行脚本语句
    public T ExceScpit(String key){

        DefaultRedisScript<T> defaultRedisScript=new DefaultRedisScript<>();
        defaultRedisScript.setScriptText("for i=1,1000 do\n" +
                " local key=KEYS[1]..i\n" +
                " redis.call('set',key,i)\n" +
                " redis.call('EXPIRE',key,i-1)\n" +
                "end\n" +
                " return redis.call('get',KEYS[1])\n");
        List<String> list=new  ArrayList<>();
        list.add("yutian");
        return redisTemplate.execute(defaultRedisScript,list);

    }


}
