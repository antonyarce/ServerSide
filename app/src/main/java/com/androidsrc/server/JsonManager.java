package com.androidsrc.server;

/**
 * Created by allan on 19/09/16.
 */
import org.json.JSONException;
import org.json.JSONObject;


public class JsonManager {
    static String parser(String mensaje) throws JSONException {
        JSONObject parser = new JSONObject(mensaje);
        String token;
        String tipo;
        String dato;
        String respuesta="Aqui";
        String accion = parser.getString("Accion");
        if(accion.equalsIgnoreCase("iniciar")){
            respuesta="{\"Token:\""+ GenerateBase64.generar(GenerateUUID.crearUUID())+"\"}";
        }
        System.out.println(accion);
        return respuesta;
    }
}
