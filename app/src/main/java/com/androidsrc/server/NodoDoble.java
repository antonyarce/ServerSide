package com.androidsrc.server;

/**
 * Created by antonyarce on 10/9/16.
 */

public class NodoDoble {
    public MeshNode dato;
    NodoDoble siguiente,anterior;

    //Constructor para cuando aun no hay nodos
    public NodoDoble(MeshNode ele){
        this(ele,null,null);
    }

    //Constructor para cuando ya hay nodos
    public NodoDoble(MeshNode ele, NodoDoble sig, NodoDoble ant) {
        dato=ele;
        siguiente=sig;
        anterior=ant;
    }

}