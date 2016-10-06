package com.androidsrc.server;

/**
 * Created by antonyarce on 12/9/16.
 */
public class MeshNode {
    public String ip;
    public int num, port, bytesTot, bytesUso, bytedisponibles;
    public boolean _Free;
    MeshNode siguiente,anterior;

    //Constructor para cuando aun no hay nodos
    public MeshNode(String IP,int PORT,int NUM, int BYTESTOTALES){
        this(IP,PORT,NUM,BYTESTOTALES,null,null);
    }

    //Constructor para cuando ya hay nodos
    public MeshNode(String IP,int PORT,int NUM, int BYTESTOTALES, MeshNode sig, MeshNode ant) {
        ip=IP;
        num=NUM;
        port=PORT;
        bytesTot=BYTESTOTALES;
        siguiente=sig;
        anterior=ant;
    }

    public int getBytedisponibles() {
        return bytedisponibles;
    }

    public void setBytedisponibles(int bytedisponibles) {
        this.bytedisponibles = bytedisponibles;
    }

    public int getBytesUso() {
        return bytesUso;
    }

    public void setBytesUso(int bytesUso) {
        this.bytesUso = bytesUso;
    }

    public int getBytesTot() {
        return bytesTot;
    }

    public void setBytesTot(int bytesTot) {
        this.bytesTot = bytesTot;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean is_Free() {
        return _Free;
    }

    public void set_Free(boolean _Free) {
        this._Free = _Free;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
