package com.androidsrc.server;

/**
 * Created by allan on 19/09/16.
 */

import org.json.JSONException;
import org.json.JSONObject;


public class JsonManager{

    static String parser(String mensaje) throws JSONException {
        JSONObject parser = new JSONObject(mensaje);
        String respuesta="";
        String accion = parser.getString("Accion");
        if(accion.equalsIgnoreCase("Conexion")){
            String UUID = GenerateUUID.crearUUID();
            String UUID64 = GenerateBase64.generar(UUID);
            respuesta="{\"Token\":\""+UUID64+"\"}";
            /*Client cliente = new Client("172.26.6.35",21000,"Hola");
            cliente.execute();
            cliente=null;*/
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
            JSONObject json = new JSONObject();
            json.put("Accion","Guardar");
            json.put("Dato",datoasignar);
            Client cliente = new Client("172.26.6.35",21000,json.toString());
            cliente.execute();
            cliente=null;

        }if (accion.equalsIgnoreCase("xFree")){
            String tokenrecibido = parser.getString("Token");
            String idLiberar = parser.getString("ID");
        }
        System.out.println(accion);
        return respuesta;


    }
    static String parserNode(String mensaje) throws JSONException{
        JSONObject parser = new JSONObject(mensaje);
        String respuesta="";
        String accion = parser.getString("Accion");
        if(accion.equalsIgnoreCase("NodeConexion")){
            String ip = parser.getString("Ip");
            int puerto = parser.getInt("puerto");
            int numero = parser.getInt("numero");
            int bytesDisponibles = parser.getInt("bytesDisp");
            String IDMeshNode = MapManager.getContador();

            //Instanciar el nodo para agregarlo a la lista

            MapManager.listaMeshNodos.agregarInicio(ip,puerto,numero,bytesDisponibles,IDMeshNode);
            System.out.println(MapManager.listaMeshNodos.mostrarFinInicio());

            //Aqui agrega el dato en la lista estatica




            respuesta="{\"Estado\":\"ConexionExitosa\"}";
        }
        return respuesta;
    }
}
