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
}
