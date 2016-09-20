package com.androidsrc.server;

/**
 * Created by allan on 19/09/16.
 */
import org.json.JSONException;
import org.json.JSONObject;


public class JsonManager {
    static void parser(String mensaje) throws JSONException {
        JSONObject parser = new JSONObject(mensaje);
    }
}
