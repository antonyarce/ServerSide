package com.androidsrc.server;

/**
 * Created by antonyarce on 10/9/16.
 */



// Lista que tiene como NodoDobles, objetos de tipo MeshNode
public class NodeMap {

    public MeshNode inicio, fin;

    public NodeMap() {
        inicio = fin = null;
    }


    //Retorna un boolean indicando si la lista está vacía
    public boolean estaVacia() {
        return inicio == null;
    }




    public void agregarFinal(String ip, int port, int num, int bytesTotales,String id) {
        if (!estaVacia()) {
            fin = new MeshNode(ip, port, num, bytesTotales,id, null, fin);
            fin.anterior.siguiente = fin;
        } else {
            inicio = fin = new MeshNode(ip, port, num, bytesTotales,id);
        }
    }

    public int tamaño() {
        int cant = 0;
        MeshNode tmp = inicio;
        while (tmp != null) {
            tmp = tmp.siguiente;
            cant++;
        }
        return cant;
    }

    public void agregarInicio(String ip, int port, int num, int bytesTotales,String id) {
        if (!estaVacia()) {
            inicio = new MeshNode(ip, port, num, bytesTotales, id, inicio, null);
            inicio.siguiente.anterior = inicio;
        } else {
            inicio = fin = new MeshNode(ip, port, num, bytesTotales,id);
        }
    }


    public String mostrarInicioFin() {
        String datos = "<=>";
        if (!estaVacia()) {
            MeshNode auxiliar = inicio;
            while (auxiliar != null) {
                datos = datos + "{" + auxiliar.getId() + "}" + "<=>";
                auxiliar = auxiliar.siguiente;

            }
        }
        return datos;
    }


    public String mostrarFinInicio() {
        String datos = "<=>";
        if (!estaVacia()) {
            MeshNode auxiliar = fin;
            while (auxiliar != null) {
                datos = datos + "{" + auxiliar.getId() + "}" + "<=>";
                auxiliar = auxiliar.anterior;

            }
        }
        return datos;
    }

    //
    public MeshNode buscar(String id) {
        MeshNode auxiliar = inicio;
        for (; auxiliar != null && !id.equals(auxiliar.getId()); auxiliar = auxiliar.siguiente) ;
        if (auxiliar == null) {
            return null;
        } else {
            return auxiliar;
        }

    }

    public String buscarip(String id) {
        MeshNode auxiliar = inicio;
        for (; auxiliar != null && !id.equals(auxiliar.getId()); auxiliar = auxiliar.siguiente) ;
        if (auxiliar == null) {
            return null;
        } else {
            return auxiliar.getIp();
        }

    }

    public int buscarport(String id) {
        MeshNode auxiliar = inicio;
        for (; auxiliar != null && !id.equals(auxiliar.getId()); auxiliar = auxiliar.siguiente) ;
        if (auxiliar == null) {
            return 0;
        } else {
            return auxiliar.getPort();
        }

    }

    public String buscarespacio(int size){
        MeshNode auxiliar = inicio;
        for (; auxiliar != null && !(size>auxiliar.getBytedisponibles()); auxiliar = auxiliar.siguiente);
        if (auxiliar == null){
            return null;
        }else{
            auxiliar.bytesUso=auxiliar.bytesUso+size;
            auxiliar.setBytedisponibles(auxiliar.bytedisponibles-size);
            return auxiliar.getId();
        }
    }


    public void borrarInicio() {
        if (inicio == fin) {
            inicio = fin = null;
        } else {
            inicio = inicio.siguiente;
            inicio.anterior = null;
        }
    }


    public void borrarFinal() {
        if (inicio == fin) {
            inicio = fin = null;
        } else {
            fin = fin.anterior;
            fin.siguiente = null;
        }
    }


    public void borrar(String id) {
        MeshNode buscado = null;
        MeshNode iterador = inicio;
        if (inicio == fin) {
            inicio = fin = null;
        } else if (id.equals(inicio.getId())) {
            inicio = inicio.siguiente;
            inicio.anterior = null;
        } else if (id.equals(fin.getId())) {
            fin = fin.anterior;
            fin.siguiente = null;
        } else {
            while (buscado == null && iterador != null) {
                if (id.equals(iterador.getId())) {
                    buscado = iterador;
                }
                iterador = iterador.siguiente;
            }
            buscado.anterior.siguiente = buscado.siguiente;
            buscado.siguiente.anterior = buscado.anterior;
        }
    }

    public void borrarPosicion(int pos) {
        MeshNode iterador = inicio;
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
                MeshNode tmpsiguiente = iterador.siguiente;
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
