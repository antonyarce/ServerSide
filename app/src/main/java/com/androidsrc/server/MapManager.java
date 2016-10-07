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
            auxiliar.setBytesUso(size);
            auxiliar.setBytedisponibles(auxiliar.getBytedisponibles()-size);
            return auxiliar.getId();
        }

    }
}
