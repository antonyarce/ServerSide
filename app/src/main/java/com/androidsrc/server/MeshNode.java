package com.androidsrc.server;

/**
 * Created by antonyarce on 12/9/16.
 */
public class MeshNode {
    String ip;
    int puerto;
    int num;
    int bytesTot;
    int bytesUso;
    int bytedisponibles;

    MeshNode(String ip, int puerto, int num, int bytesTot, int bytesUso){
        this.ip = ip;
        this.puerto = puerto;
        this.num = num;
        this.bytesTot = bytesTot;
        this.bytesUso = bytesUso;
    }

}
