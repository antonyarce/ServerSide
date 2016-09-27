package com.androidsrc.server;

/**
 * Created by allan on 27/09/16.
 */
public class MemoryBlocks {
    String ip;
    int puerto;
    int num;
    int bytesTot;
    int bytesUso;

    MemoryBlocks(String ip, int puerto, int num, int bytesTot, int bytesUso){
        this.ip = ip;
        this.puerto = puerto;
        this.num = num;
        this.bytesTot = bytesTot;
        this.bytesUso = bytesUso;
    }
}
