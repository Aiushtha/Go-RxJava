package org.lxz.tools.android.debug;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Lin on 16/4/20.
 */
public class PrintFormat {

    public static String printJson(String json) {
        Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        String jsonformat = gson3.toJson(je);
        return jsonformat;
    }

    ;

    public static String printJson2html(String json) {
        return printJson(json).replaceAll("\n", "<br>");
    }

    ;

    public static String url2json(String url) {
        System.out.println("-->"+url);
        JsonObject jsonObj = new JsonObject();
        JsonObject jsonParmas = new JsonObject();
        try {
            jsonObj.addProperty("url", url.split("\\?")[0]);
            String arrs = url.substring(url.split("\\?")[0].length() + 1, url.length());
            String[] keyValues = arrs.split("&");
            for (int i = 0, j = keyValues.length; i < j; i++) {
                String str = keyValues[i];
                String[] keyValue = str.split("=");
                String key = keyValue[0];
                String value = null;
                try {
                    value = keyValue[1];
                } catch (Exception e) {
                    value="";
                }

                jsonParmas.addProperty(key, value);
            }
            jsonObj.add("parmas", jsonParmas);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return printJson2html(jsonObj.toString());

    }


}
