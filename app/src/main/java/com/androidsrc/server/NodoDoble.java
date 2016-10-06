package com.androidsrc.server;

/**
 * Created by antonyarce on 10/9/16.
 */

public class NodoDoble {
    public String ip;
    public int num, bytesTot, bytesUso, bytedisponibles;
    NodoDoble siguiente,anterior;

    //Constructor para cuando aun no hay nodos
    public NodoDoble(String IP,int NUM, int BYTESTOTALES){
        this(IP,NUM,BYTESTOTALES,null,null);
    }

    //Constructor para cuando ya hay nodos
    public NodoDoble(String IP,int NUM, int BYTESTOTALES, NodoDoble sig, NodoDoble ant) {
        siguiente=sig;
        anterior=ant;
    }

}