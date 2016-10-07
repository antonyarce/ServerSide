package com.androidsrc.server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by allan on 03/10/16.
 */

public class BotonesActivity extends Activity {
    Button nodos;
    Button memoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.botones);

        nodos = (Button)findViewById(R.id.nodo);
        memoria = (Button)findViewById(R.id.memoria);
        nodos.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(BotonesActivity.this,NodeMapActivity.class);
                startActivity(i);
            }
        });
        memoria.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(BotonesActivity.this,GraficNodes.class);
                startActivity(i);

            }
        });
    }


}
