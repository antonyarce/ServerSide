package com.androidsrc.server;

import android.media.MediaActionSound;

/**
 * Created by allan on 27/09/16.
 */
public class MapManager {
    public static MemoryMap listaDeBloques = new MemoryMap();
    public static NodeMap listaMeshNodos = new NodeMap();
    private static int contador = 0;




    static String getContador() {
        contador = contador+1;
        return String.valueOf(contador);
    }

    /*static String buscarEspacio(int size) {
        MeshNode auxiliar = listaMeshNodos.inicio;
        for (; auxiliar != null && !(size>auxiliar.getBytedisponibles()); auxiliar = auxiliar.siguiente) ;
        if (auxiliar == null) {
            return "Error, no hay campo";
        } else {
            auxiliar.setBytedisponibles(auxiliar.getBytedisponibles()-size);
            auxiliar.setBytesUso(auxiliar.getBytesUso()+size);
            return auxiliar.getId();
        }

    }*/

    static String buscarEspacio(int size){
        int i=0;
        String idMeshNode = MapManager.listaMeshNodos.buscarespacio(size);
        return idMeshNode;
    }

    static void Garbage(){
        new Thread(new Runnable(){
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                    for(int i = 0;i < MapManager.listaDeBloques.tamaño();i++){
                        if(MapManager.listaDeBloques.buscarFree(i)) {

                            String idMeshNode = MapManager.listaDeBloques.buscarIdMesh(i);
                            String ip = MapManager.listaMeshNodos.buscarip(idMeshNode);
                            int port = MapManager.listaMeshNodos.buscarport(idMeshNode);
                            String idGarbage = MapManager.listaDeBloques.buscarUUID(i);

                            int nuevosBytesDisp = MapManager.listaMeshNodos.buscar(idMeshNode).bytedisponibles + MapManager.listaDeBloques.buscarSize(i);
                            MapManager.listaMeshNodos.buscar(idMeshNode).setBytedisponibles(nuevosBytesDisp);

                            int nuevosBytesUso = MapManager.listaMeshNodos.buscar(idMeshNode).bytesUso - MapManager.listaDeBloques.buscarSize(i);
                            MapManager.listaMeshNodos.buscar(idMeshNode).setBytesUso(nuevosBytesUso);

                            String accionMensaje = "Garbage";
                            Client client = new Client(ip,port,"{\"Accion\":\""+accionMensaje+"\",\"UUIDEspacio\":\""+idGarbage+"\",\"BytesDisp\":\""+Integer.toString(nuevosBytesDisp)+"\"}");

                        }
                    }
            }
        }).start();
    }

    static void Burping(){
        new Thread(new Runnable(){
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                MemoryBlock temp = MapManager.listaDeBloques.inicio;
                for(int i = 0;i < MapManager.listaDeBloques.tamaño();i++){
                    if(temp.is_Free()) {

                        String ip = MapManager.listaMeshNodos.buscar(temp.getIdMeshNode()).getIp();
                        int port = MapManager.listaMeshNodos.buscar(temp.getIdMeshNode()).getPort();
                        String idBurping = temp.getUUIDspace();

                        MapManager.listaDeBloques.borrar(idBurping);

                        String accionMensaje = "Burping";
                        Client client = new Client(ip,port,"{\"Accion\":\""+accionMensaje+"\",\"UUIDEspacio\":\""+idBurping+"\"}");

                    }
                    temp = temp.siguiente;
                }
            }
        }).start();
    }
}
