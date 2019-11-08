package com.webencyclop.demo.convertorJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;



public class JsonConvector implements BaseJsonConvector {

    @Override
    public String ObjectToJson(Object object) throws IllegalAccessException, JSONException {
        String str = "";
        Class c = object.getClass();
        JSONObject jsonObject = new JSONObject();
        for (Field field : c.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            String value = String.valueOf(field.get(object));
            jsonObject.put(name, value);
        }
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }

    @Override
    public String ListToJson(List list) throws JSONException, IllegalAccessException {
        JSONArray jsonArray = new JSONArray();
        for(Object object:list){
            String jstr = ObjectToJson(object);
            JSONObject jsonObject = new JSONObject(jstr);
            jsonArray.put(jsonArray);
        }
        return jsonArray.toString();
    }
}
