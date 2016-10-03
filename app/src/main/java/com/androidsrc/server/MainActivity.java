package com.androidsrc.server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	Manager manager;
	TextView infoip, msg;
	TextView response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoip = (TextView) findViewById(R.id.infoip);
		msg = (TextView) findViewById(R.id.msg);
		manager = new Manager(this);
		//ServerCliente serverc = new ServerCliente(this);
		ServerCliente server = new ServerCliente(this, 9090);
		infoip.setText(server.getIpAddress()+":"+server.getPort());
	}

	public void pasar(){
		Intent i = new Intent(MainActivity.this,BotonesActivity.class);
		startActivity(i);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		manager.onDestroy();
	}



	
}