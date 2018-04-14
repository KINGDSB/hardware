package com.honeylovely.center.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.protobuf.GeneratedMessage;
import com.googlecode.protobuf.format.JsonFormat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 */
public class JsonUtils {
    private static ObjectMapper objectMapper;
    private static JsonFormat jsonFormat = new JsonFormat();

    static
    {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static String toJson(Object object)
            throws JsonProcessingException
    {
        return objectMapper.writeValueAsString(object);
    }

    public static Map toMap(String content)
            throws IOException
    {
        return (Map)objectMapper.readValue(content, Map.class);
    }

    public static <T> T toObject(String content, Class<T> t)
            throws IOException
    {
        return objectMapper.readValue(content, t);
    }

    public static String jsonProto(GeneratedMessage generatedMessage)
    {
        return jsonFormat.printToString(generatedMessage);
    }
}
