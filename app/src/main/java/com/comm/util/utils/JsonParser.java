package com.comm.util.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
//    private static MyLogger logger = MyLogger.getLogger("JsonParser");

    public JsonParser() {
    }

    public static Map decodeArray(String data) {
        if (data != null && !data.equals("")) {
            Map map = null;
            data = data.trim();
            if (data.startsWith("{")) {
                try {
                    JSONArray jsonObj = new JSONArray(data);
//                    if (jsonObj != null) {
//                        map = parserJSONObject(jsonObj);
//                    }
                } catch (JSONException var4) {
                    var4.printStackTrace();
                }
            } else {
//                logger.e("---> request date is not json ");
            }

            return map;
        } else {
            return null;
        }
    }


    public static Map decode(String data) {
        if (data != null && !data.equals("")) {
            Map map = null;
            data = data.trim();
            if (data.startsWith("{")) {
                try {
                    JSONObject jsonObj = new JSONObject(data);
                    if (jsonObj != null) {
                        map = parserJSONObject(jsonObj);
                    }
                } catch (JSONException var4) {
                    var4.printStackTrace();
                }
            } else {
//                logger.e("---> request date is not json ");
            }

            return map;
        } else {
            return null;
        }
    }

    private static Map parserJSONObject(JSONObject jsonObject) {
        HashMap map = null;

        try {
            if (jsonObject != null) {
                map = new HashMap();
                Iterator a = jsonObject.keys();

                while(a.hasNext()) {
                    String str = (String)a.next();
                    Object obj = jsonObject.get(str);
                    if (obj instanceof JSONObject) {
                        map.put(str, parserJSONObject((JSONObject)obj));
                    } else if (obj instanceof JSONArray) {
                        map.put(str, parserJSONArray((JSONArray)obj));
                    } else if (obj instanceof String) {
                        map.put(str, obj);
                    } else if (obj instanceof Long) {
                        map.put(str, "" + obj);
                    } else if (obj instanceof Double) {
                        map.put(str, "" + obj);
                    } else if (obj instanceof Float) {
                        map.put(str, "" + obj);
                    } else if (obj instanceof Integer) {
                        map.put(str, "" + obj);
                    } else if (obj instanceof Boolean) {
                        map.put(str, obj.toString());
                    }
                }
            }
        } catch (Exception var5) {
//            logger.i("parserJSONObject err:" + var5);
        }

        return map;
    }

    private static List parserJSONArray(JSONArray jsonArray) {
        ArrayList list = null;

        try {
            if (jsonArray != null) {
                list = new ArrayList();
                int len = jsonArray.length();

                for(int i = 0; i < len; ++i) {
                    new HashMap();
                    Object obj = jsonArray.get(i);
                    jsonArray.getString(i);
                    if (obj instanceof JSONObject) {
                        list.add(parserJSONObject((JSONObject)obj));
                    } else if (obj instanceof JSONArray) {
                        list.add(parserJSONArray((JSONArray)obj));
                    } else if (obj instanceof String) {
                        list.add(obj);
                    } else if (obj instanceof Long) {
                        list.add("" + obj);
                    } else if (obj instanceof Double) {
                        list.add("" + obj);
                    } else if (obj instanceof Float) {
                        list.add("" + obj);
                    } else if (obj instanceof Integer) {
                        list.add("" + obj);
                    } else if (obj instanceof Boolean) {
                        list.add(obj.toString());
                    }
                }
            }
        } catch (Exception var7) {
//            logger.i("parserJSONArray err:" + var7);
        }

        return list;
    }
}