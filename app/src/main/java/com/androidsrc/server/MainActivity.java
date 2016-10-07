package com.androidsrc.server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	ServerClient server;
	ServerClientC serverC;
	TextView infoip, msg;
	TextView response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoip = (TextView) findViewById(R.id.infoip);
		msg = (TextView) findViewById(R.id.msg);
		server = new ServerClient(this);
		serverC = new ServerClientC(this, 9090);
		infoip.setText(server.getIpAddress()+":"+server.getPort());
	}

	public void pasar(){
		Intent i = new Intent(MainActivity.this,BotonesActivity.class);
		startActivity(i);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		server.onDestroy();
	}



	
}