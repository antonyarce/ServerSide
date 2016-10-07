package com.androidsrc.server;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by allan on 03/10/16.
 */

public class NodeMapActivity extends Activity {
    ListView listN;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nodos);
        listN=(ListView)findViewById(R.id.listaN);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom);
        listN.setAdapter(adapter);
        recorrer();

    }

    public void mostrarEnLista(String texto){
        arrayList.add(texto);
        adapter.notifyDataSetChanged();
    }

    public void recorrer(){
        MeshNode auxiliar = MapManager.listaMeshNodos.inicio;
        for(;auxiliar!=null;auxiliar=auxiliar.siguiente){
            mostrarEnLista(auxiliar.toString());
        }
    }

    public void limpiar(){
        int count = adapter.getCount();
        for(int i = 0;1<count;i++){
            adapter.remove(adapter.getItem(count));
        }
        adapter.notifyDataSetChanged();
    }
}
