package com.androidsrc.server;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
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
		ServerCliente serverc = new ServerCliente(this);
		infoip.setText(manager.getIpAddress()+":"+manager.getPort());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		manager.onDestroy();
	}

	
}