package com.androidsrc.server;

/**
 * Created by antonyarce on 12/9/16.
 */
public class MeshNode {
    public String ip;
    public String id;
    public int num, port, bytesTot, bytesUso, bytedisponibles;
    public boolean _Free;
    MeshNode siguiente,anterior;
    NodeMapActivity activity;

    //Constructor para cuando aun no hay nodos
    public MeshNode(String IP,int PORT,int NUM, int BYTESTOTALES,String ID){
        this(IP,PORT,NUM,BYTESTOTALES,ID,null,null);
        String cadena = "IP: "+IP+", Puerto: "+port+", MemTot: "+bytesTot
                +", MemDisp: "+bytedisponibles;

    }

    //Constructor para cuando ya hay nodos
    public MeshNode(String IP,int PORT,int NUM, int BYTESTOTALES,String ID, MeshNode sig, MeshNode ant) {
        id=ID;
        ip=IP;
        num=NUM;
        port=PORT;
        bytesTot=bytedisponibles=BYTESTOTALES;
        bytesUso=0;
        siguiente=sig;
        anterior=ant;
        _Free=false;


    }

    public String toString(){
        return "IP: "+ip+", Puerto: "+port+", MemTot: "+bytesTot
                +", MemDisp: "+bytedisponibles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
