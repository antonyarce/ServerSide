package com.androidsrc.server;

/**
 * Created by allan on 19/09/16.
 */
import org.json.JSONException;
import org.json.JSONObject;


public class JsonManager {
    static void parser(String mensaje) throws JSONException {
        JSONObject parser = new JSONObject(mensaje);
        String token = parser.getString("Token");
        String accion = parser.getString("Accion");
        String tipo = parser.getString("Tipo");
        String dato = parser.getString("Dato");
        System.out.println(accion);

    }
}
