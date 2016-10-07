package com.androidsrc.server;

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

    static String buscarEspacio(int size) {
        MeshNode auxiliar = listaMeshNodos.inicio;
        for (; auxiliar != null && !(size>auxiliar.getBytedisponibles()); auxiliar = auxiliar.siguiente) ;
        if (auxiliar == null) {
            return "Error, no hay campo";
        } else {
            auxiliar.setBytedisponibles(auxiliar.getBytedisponibles()-size);
            auxiliar.setBytesUso(auxiliar.getBytesUso()+size);
            return auxiliar.getId();
        }

    }

    static void Garbage(){
        new Thread(new Runnable(){
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                    MemoryBlock temp = MapManager.listaDeBloques.inicio;
                    for(int i = 0;i < MapManager.listaDeBloques.tamaño();i++){
                        if(temp.is_Free()) {

                            String idMeshNode = temp.getIdMeshNode();
                            String ip = MapManager.listaMeshNodos.buscar(idMeshNode).getIp();
                            int port = MapManager.listaMeshNodos.buscar(idMeshNode).getPort();
                            String idGarbage = temp.getUUIDspace();

                            int nuevosBytesDisp = MapManager.listaMeshNodos.buscar(idMeshNode).bytedisponibles + temp.getSize();
                            MapManager.listaMeshNodos.buscar(idMeshNode).setBytedisponibles(nuevosBytesDisp);

                            int nuevosBytesUso = MapManager.listaMeshNodos.buscar(idMeshNode).bytesUso - temp.getSize();
                            MapManager.listaMeshNodos.buscar(idMeshNode).setBytesUso(nuevosBytesUso);

                            String accionMensaje = "Garbage";
                            Client client = new Client(ip,port,"{\"Accion\":\""+accionMensaje+"\",\"UUIDEspacio\":\""+idGarbage+"\",\"BytesDisp\":\""+Integer.toString(nuevosBytesDisp)+"\"}");

                        }
                        temp = temp.siguiente;
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
