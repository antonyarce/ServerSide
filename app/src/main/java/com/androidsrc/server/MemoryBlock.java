package com.androidsrc.server;

/**
 * Created by allan on 27/09/16.
 */
public class MemoryBlock {
    public String UUIDspace;
    public String idMeshNode;
    public int size;
    public boolean _Free;
    MemoryBlock siguiente,anterior;

    //Constructor para cuando aun no hay nodos
    public MemoryBlock(String ID,String IDMESH,int SIZE){

        this(ID,IDMESH,SIZE,null,null);
        /*String cadena = "IdMeshNode: "+idMeshNode+", UUIDspace: "+UUIDspace;
        MemoryMapActivity.mostrarEnLista(cadena);*/
    }

    //Constructor para cuando ya hay nodos
    public MemoryBlock(String ID,String IDMESH,int SIZE, MemoryBlock sig, MemoryBlock ant) {
        UUIDspace=ID;
        idMeshNode=IDMESH;
        size=SIZE;
        siguiente=sig;
        anterior=ant;

       /* String cadena = "IdMeshNode: "+idMeshNode+", UUIDspace: "+UUIDspace;
        MemoryMapActivity.mostrarEnLista(cadena);*/
    }

    public String getUUIDspace() {
        return UUIDspace;
    }

    public void setUUIDspace(String UUIDspace) {
        this.UUIDspace = UUIDspace;
    }

    public String getIdMeshNode() {
        return idMeshNode;
    }

    public void setIdMeshNode(String idMeshNode) {
        this.idMeshNode = idMeshNode;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean is_Free() {
        return _Free;
    }

    public void set_Free(boolean _Free) {
        this._Free = _Free;
    }


}
