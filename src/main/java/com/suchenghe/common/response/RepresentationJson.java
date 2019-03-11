package com.suchenghe.common.response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 返回前端Json
 *
 * @author suchenghe
 * @date 2018/7/3 11:35
 */

public class RepresentationJson {

    public static JSONObject jsonSucceedRepresentation() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        return jsonObject;
    }

    public static JSONObject jsonSucceedRepresentation(String result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("result", result);
        return jsonObject;
    }

    public static JSONObject jsonSucceedRepresentation(JSONArray result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("result", result);
        return jsonObject;
    }

    public static JSONObject jsonSucceedRepresentation(JSONObject result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("result", result);
        return jsonObject;
    }

    public static JSONObject jsonSucceedRepresentation(int code, JSONObject result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("result", result);
        return jsonObject;
    }

    public static JSONObject jsonSucceedRepresentation(int code, String result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("result", result);
        return jsonObject;
    }

    public static JSONObject jsonErrorRepresentation(int code, String errorMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("errMsg", errorMsg);
        return jsonObject;
    }


    public static JSONObject jsonErrorRepresentation(String errorMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 21111);
        jsonObject.put("errMsg", errorMsg);
        return jsonObject;
    }


    public static JSONObject jsonNormalRepresentation(int code, String resultname, String result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put(resultname, result);
        return jsonObject;
    }

    public static JSONObject jsonNormalRepresentation(int code, String resultname, JSONArray result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put(resultname, result);
        return jsonObject;
    }

    public static JSONObject jsonNormalRepresentation(int code, String resultname, JSONObject result) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put(resultname, result);
        return jsonObject;
    }


}
