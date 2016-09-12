package com.androidsrc.server;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Server server;
	TextView infoip, msg;
	Button buttonConnect;

	TextView response;
	String addr = "192.168.100.18";
	int port = 21000;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoip = (TextView) findViewById(R.id.infoip);
		msg = (TextView) findViewById(R.id.msg);
		server = new Server(this);
		infoip.setText(server.getIpAddress()+":"+server.getPort());

		response = (TextView) findViewById(R.id.responseTextView);
		buttonConnect = (Button) findViewById(R.id.connectButton);

		buttonConnect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Client myClient = new Client(addr, port, response);
				myClient.execute();

			}
		});

		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		server.onDestroy();
	}

	
}