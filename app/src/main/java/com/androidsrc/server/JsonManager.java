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
        if(accion.equalsIgnoreCase("Conexion")){
            String UUID = GenerateUUID.crearUUID();
            String UUID64 = GenerateBase64.generar(UUID);
            respuesta="{\"Token\":\""+UUID64+"\"}";
            System.out.println("UUID:"+UUID);
            System.out.println("UUID64:"+UUID64);
        }if(accion.equalsIgnoreCase("xmalloc")){
            String tokenrecibido = parser.getString("Token");
            int size = parser.getInt("Size");
        }if (accion.equalsIgnoreCase("xMalloc")){
            String tokenrecibido = parser.getString("Token");
            int size = parser.getInt("Size");
            String datoguardar = parser.getString("Dato");
        }if (accion.equalsIgnoreCase("xAssign")){
            String tokenrecibido = parser.getString("Token");
            String datoasignar = parser.getString("Dato");
            String idEspacio = parser.getString("ID");
        }if (accion.equalsIgnoreCase("xFree")){
            String tokenrecibido = parser.getString("Token");
            String idLiberar = parser.getString("ID");
        }
        System.out.println(accion);
        return respuesta;
    }
}
