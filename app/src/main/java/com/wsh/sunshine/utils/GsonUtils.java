package com.wsh.sunshine.utils;

import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonUtils {
    private static Gson gson = new Gson();

    /**
     * json转换成Bean
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        T t = null;
        try {
            t = gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            XLog.e(e);
        }
        return t;
    }

    /**
     * Bean转换json
     */
    public static String bean2Json(Object bean) {
        return gson.toJson(bean);
    }
}
