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
            System.out.println(accion);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }if(accion.equalsIgnoreCase("xmalloc")){

            //Parsea los datos
            String tokenrecibido = parser.getString("Token");
            String size = parser.getString("Size");

            //Busca el id del primer MeshNode que cuenta con espacio suficiente
            String idMeshNode = MapManager.buscarEspacio(Integer.parseInt(size));;

            //Crea un MemoryBlock
            String UUIDEspacio = GenerateUUID.crearUUID();
            MapManager.listaDeBloques.agregarInicio(UUIDEspacio,idMeshNode,Integer.parseInt(size));

            System.out.println("Se agregó");

            //Mensaje para API en C++
            respuesta="{\"Token\":\""+tokenrecibido+"\",\"UUIDEspacio\":\""+UUIDEspacio+"\"}";
            System.out.println(accion);

            //Se comunica con el celular con el ip y puerto obtenido
            String iptofind = MapManager.listaMeshNodos.buscarip(idMeshNode);

            System.out.println("Este es el IP"+iptofind);
            int porttofind = MapManager.listaMeshNodos.buscarport(idMeshNode);
            String accionMensaje = "Agregar1";
            Client client = new Client(iptofind,porttofind,"{\"Accion\":\""+accionMensaje+"\",\"UUIDEspacio\":\""+UUIDEspacio+"\",\"Size\":\""+size+"\"}");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }if (accion.equalsIgnoreCase("xMalloc2")){
            String tokenrecibido = parser.getString("Token");
            String size = parser.getString("Size");
            String datoguardar = parser.getString("Dato");

            //Busca el id del primer MeshNode que cuenta con espacio suficiente
            String idMeshNode = MapManager.buscarEspacio(Integer.parseInt(size));;

            //Crea un MemoryBlock
            String UUIDEspacio = GenerateUUID.crearUUID();
            MapManager.listaDeBloques.agregarInicio(UUIDEspacio,idMeshNode,Integer.parseInt(size));

            //Mensaje para API en C++
            respuesta="{\"Token\":\""+tokenrecibido+"\",\"UUIDEspacio\":\""+UUIDEspacio+"\"}";
            System.out.println(accion);

            //Se comunica con el celular con el ip y puerto obtenido
            String iptofind= MapManager.listaMeshNodos.buscarip(idMeshNode);
            int porttofind = MapManager.listaMeshNodos.buscarport(idMeshNode);
            String accionMensaje = "Agregar2";
            Client client = new Client(iptofind,porttofind,"{\"Accion\":\""+accionMensaje+"\",\"UUIDEspacio\":\""+UUIDEspacio+"\",\"Size\":\""+size+"\",\"Dato\":\""+datoguardar+"\"}");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
        }if (accion.equalsIgnoreCase("xAssign")){
            String tokenrecibido = parser.getString("Token");
            String datoasignar = parser.getString("Dato");
            String idEspacio = parser.getString("UUID");

            String idMeshNode = MapManager.listaDeBloques.buscaridMesh(idEspacio);
            String iptofind = MapManager.listaMeshNodos.buscarip(idMeshNode);
            int porttofind = MapManager.listaMeshNodos.buscarport(idMeshNode);
            String accionMensaje="Asignar";
            Client client = new Client(iptofind,porttofind,"{\"Accion\":\""+accionMensaje+"\",\"UUIDEspacio\":\""+idEspacio+"\",\"Dato\":\""+datoasignar+"\"}");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }if (accion.equalsIgnoreCase("xFree")){
            //Parsea los datos
            String tokenrecibido = parser.getString("Token");
            String idLiberar = parser.getString("UUID");

            //Settea el MemoryBlock como libre usando el idLiberar
            MapManager.listaDeBloques.setFree(idLiberar);


            //Obtengo el id del MeshNode al que pertenece y sus atributos
            String idMeshNode = MapManager.listaDeBloques.buscaridMesh(idLiberar);
            String iptofind = MapManager.listaMeshNodos.buscarip(idMeshNode);
            int porttofind = MapManager.listaMeshNodos.buscarport(idMeshNode);
            String accionMensaje = "Liberar";
            Client client = new Client(iptofind,porttofind,"{\"Accion\":\""+accionMensaje+"\",\"UUIDEspacio\":\""+idLiberar+"\"}");

        }
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
