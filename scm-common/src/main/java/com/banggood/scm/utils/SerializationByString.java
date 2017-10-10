package com.banggood.scm.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Administrator on 2017/8/29.
 */
public class SerializationByString {

    //object 序列化成json
    public <T> String ObjectChangeJson(T entity) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String json=mapper.writeValueAsString(entity);
        return json;
    }


    //json 反序列化成object
    public static <T> T JsonChangeObject(String json, Class<T> classtype) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        T object=mapper.readValue(json,classtype);
        return object;
    }
}
