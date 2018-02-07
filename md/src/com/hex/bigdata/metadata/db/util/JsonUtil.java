//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hex.bigdata.metadata.db.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class JsonUtil {
    public JsonUtil() {
    }

    public static <T> String parseObj2JSON(T obj) {
        if (obj == null) {
            return "";
        }
        JSONObject jsonObj = JSONObject.fromObject(obj);
        return jsonObj.toString();
    }

    public static <T> T parseJSON2Obj(String jsonStr, Class clazz) {
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        return (T) JSONObject.toBean(jsonObj, clazz);
    }

    public static <T> String parseList2JSON(List<T> objList) {
        if (objList == null) {
            return "";
        }
        JSONArray jsonArr = JSONArray.fromObject(objList);
        return jsonArr.toString();
    }

    public static <T> List<T> parseJSON2List(String jsonStr, Class clazz) {
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        return (List<T>) JSONArray.toCollection(jsonArr, clazz);
    }

    public static <T> String parseMap2JSON(Map<String, T> map) {
        if (map == null) {
            return "";
        }
        JSONObject jsonObj = JSONObject.fromObject(map);
        return jsonObj.toString();
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        Iterator var = jsonObj.keySet().iterator();

        while (var.hasNext()) {
            Object key = var.next();
            Object val = jsonObj.get(key);
            if (val instanceof JSONArray) {
                ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator it = ((JSONArray) val).iterator();

                while (it.hasNext()) {
                    JSONObject json = (JSONObject) it.next();
                    list.add(parseJSON2Map(json.toString()));
                }

                map.put(key.toString(), list);
            } else {
                map.put(key.toString(), val);
            }
        }

        return map;
    }

    public static <T> Map<String, T> parseJSON2Map(String jsonStr, Class clazz) {
        HashMap map = new HashMap();
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        Iterator var = jsonObj.keySet().iterator();
        while (var.hasNext()) {
            Object key = var.next();
            Object val = jsonObj.get(key);
            if (val instanceof JSONArray) {
                ArrayList list = new ArrayList();
                Iterator it = ((JSONArray) val).iterator();
                while (it.hasNext()) {
                    JSONObject json = (JSONObject) it.next();
                    list.add(parseJSON2Map(json.toString(), clazz));
                }
                map.put(key.toString(), list);
            } else if (val instanceof JSONObject) {
                map.put(key.toString(), JSONObject.toBean((JSONObject) val, clazz));
            } else {
                map.put(key.toString(), val);
            }
        }

        return map;
    }

    public static String parseMapList2JSON(List<Map<String, Object>> mapList) {
        if (mapList == null) {
            return "";
        }
        JSONArray jsonArr = JSONArray.fromObject(mapList);
        return jsonArr.toString();
    }

    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Iterator it = jsonArr.iterator();

        while (it.hasNext()) {
            JSONObject json = (JSONObject) it.next();
            mapList.add(parseJSON2Map(json.toString()));
        }
        return mapList;
    }

    public static <T> T parseJSON2Obj(String jsonStr, Class clazz, Map<String, Class> classMap) {
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        return (T) JSONObject.toBean(jsonObj, clazz, classMap);
    }
}
