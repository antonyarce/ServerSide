package com.androidsrc.server;

/**
 * Created by allan on 27/09/16.
*/
public class MemoryMap {
    public MemoryBlock inicio,fin;

    public MemoryMap(){
        inicio=fin=null;
    }


    public boolean estaVacia(){
        return inicio==null;
    }

    public void setFree(String idBuscado){
        MemoryBlock auxiliar=inicio;
        for ( ; auxiliar != null && !idBuscado.equals(auxiliar.getUUIDspace()); auxiliar = auxiliar.siguiente);
        if(auxiliar==null){
            System.out.print("No existe id buscado");
        }else{
            auxiliar.set_Free(true);
        }
    }

    public void agregarFinal(String UUIDSPACE,String IDMESH,int SIZE){
        if (!estaVacia()){
            fin= new MemoryBlock(UUIDSPACE,IDMESH,SIZE,null,fin);
            fin.anterior.siguiente=fin;
        }else{
            inicio=fin=new MemoryBlock(UUIDSPACE,IDMESH,SIZE);
        }
    }

    public void agregarInicio(String UUIDSPACE,String IDMESH,int SIZE){
        if (!estaVacia()){
            inicio=new MemoryBlock(UUIDSPACE, IDMESH, SIZE, inicio, null);
            inicio.siguiente.anterior= inicio;
        }else{
            inicio=fin=new MemoryBlock( UUIDSPACE, IDMESH, SIZE);
        }
    }

    public boolean buscarFree(int posicion){
        if(tamaño() != 0) {
            MemoryBlock auxiliar = inicio;
            for(int i = 0; posicion <= tamaño(); i++){
                auxiliar = auxiliar.siguiente;
            }
            return auxiliar.is_Free();
        }
        System.out.print("Lista vacia");
        return false;
    }

    public String buscarIdMesh(int posicion){
        if(tamaño() != 0) {
            MemoryBlock auxiliar = inicio;
            for(int i = 0; posicion <= tamaño(); i++){
                auxiliar = auxiliar.siguiente;
            }
            return auxiliar.getIdMeshNode();
        }
        System.out.print("Lista vacia");
        return null;
    }

    public String buscarUUID(int posicion){
        if(tamaño() != 0) {
            MemoryBlock auxiliar = inicio;
            for(int i = 0; posicion <= tamaño(); i++){
                auxiliar = auxiliar.siguiente;
            }
            return auxiliar.getUUIDspace();
        }
        System.out.print("Lista vacia");
        return null;
    }

    public int buscarSize(int posicion){
        if(tamaño() != 0) {
            MemoryBlock auxiliar = inicio;
            for(int i = 0; posicion <= tamaño(); i++){
                auxiliar = auxiliar.siguiente;
            }
            return auxiliar.getSize();
        }
        System.out.print("Lista vacia");
        return 0 ;
    }


    public String mostrarInicioFin(){
        String datos="<=>";
        if(!estaVacia()){
            MemoryBlock auxiliar = inicio;
            while (auxiliar!=null){
                datos = datos + "{"+auxiliar.getUUIDspace()+"}"+"<=>";
                auxiliar=auxiliar.siguiente;
            }
        }
        return datos;
    }


    public String mostrarFinInicio(){
        String datos="<=>";
        if(!estaVacia()){
            MemoryBlock auxiliar = fin;
            while (auxiliar!=null){
                datos = datos + "{"+auxiliar.getUUIDspace()+"}"+"<=>";
                auxiliar=auxiliar.anterior;

            }
        }
        return datos;
    }

    public MemoryBlock buscar(String idBuscado){
        MemoryBlock auxiliar=inicio;
        for ( ; auxiliar != null && !idBuscado.equals(auxiliar.getUUIDspace()); auxiliar = auxiliar.siguiente);
        if(auxiliar==null){
            return null;
        }else{
            return auxiliar;
        }
    }

    public String buscaridMesh(String id) {
        MemoryBlock auxiliar = inicio;
        for (; auxiliar != null && !id.equals(auxiliar.getUUIDspace()); auxiliar = auxiliar.siguiente) ;
        if (auxiliar == null) {
            return null;
        } else {
            return auxiliar.getIdMeshNode();
        }

    }


    public void borrarInicio(){
        if(inicio==fin){
            inicio=fin=null;
        }else{
            inicio=inicio.siguiente;
            inicio.anterior=null;
        }
    }


    public void borrarFinal(){
        if(inicio==fin){
            inicio=fin=null;
        }else{
            fin=fin.anterior;
            fin.siguiente=null;
        }
    }


    public void borrar(String id) {
        MemoryBlock buscado = null;
        MemoryBlock iterador = inicio;
        if (inicio == fin) {
            inicio = fin = null;
        } else if (id.equals(inicio.getUUIDspace())) {
            inicio = inicio.siguiente;
            inicio.anterior = null;
        } else if (id.equals(fin.getUUIDspace())) {
            fin = fin.anterior;
            fin.siguiente = null;
        } else {
            while (buscado == null && iterador != null) {
                if (id.equals(iterador.getUUIDspace())) {
                    buscado = iterador;
                }
                iterador = iterador.siguiente;
            }
            buscado.anterior.siguiente = buscado.siguiente;
            buscado.siguiente.anterior = buscado.anterior;
        }
    }
    public int tamaño() {
        int cant = 0;
        MemoryBlock tmp = inicio;
        while (tmp != null) {
            tmp = tmp.siguiente;
            cant++;
        }
        return cant;
    }

    public void borrarPosicion(int pos) {
        MemoryBlock iterador = inicio;
        if (inicio == fin) {
            inicio = fin = null;
        } else if (pos <= tamaño() - 1) {
            if (pos == 0) {
                inicio = inicio.siguiente;
                inicio.anterior = null;
            } else if (pos == tamaño() - 1) {
                fin = fin.anterior;
                fin.siguiente = null;
            } else {
                for (int i = 0; i <= pos - 1; i++) {
                    iterador = iterador.siguiente;
                }
                MemoryBlock tmpsiguiente = iterador.siguiente;
                tmpsiguiente = tmpsiguiente.siguiente;
                iterador.siguiente = tmpsiguiente;
                if (tmpsiguiente != null) {
                    tmpsiguiente.anterior = iterador;
                }
            }
        } else if(pos >= tamaño()){
            System.out.println("Error posición es mayor al tamaño de la lista");
        }
    }
}
