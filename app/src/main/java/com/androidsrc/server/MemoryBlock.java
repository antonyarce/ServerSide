package com.androidsrc.server;

/**
 * Created by allan on 27/09/16.
 */
public class MemoryBlock {
    String idMeshNode;
    String UUIDBlock;
    int size;
    String idMeshNode2;

    MemoryBlock(String idMeshNode, int size, String idMeshNode2){
        this.idMeshNode=idMeshNode;
        this.idMeshNode2=idMeshNode2;
        this.size=size;
        UUIDBlock=GenerateUUID.crearUUID();

    }
    public String getUUUID(){
        return UUIDBlock;
    }

    public String getIdMeshNode(){
        return idMeshNode;
    }

    public String getIdMeshNode2(){
        return idMeshNode2;
    }
    public int getSize(){
        return size;
    }


}
