package com.webencyclop.demo.convertorJson;

import org.json.JSONException;

import java.util.List;

public interface BaseJsonConvector {

    String ObjectToJson(Object object) throws IllegalAccessException, JSONException;

    String ListToJson(List list) throws JSONException, IllegalAccessException;

}
