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
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        listN.setAdapter(adapter);
        //arrayList.add()
        //adapter.notifyDataSetChanged();
    }
}
