package com.androidsrc.server;

/**
 * Created by antonyarce on 12/9/16.
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    Socket socket;
    JSONObject json;


    Client(String addr, int port,TextView textResponse) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse=textResponse;

    }


    @Override
    protected Void doInBackground(Void... arg0) {
        socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            JSONObject json = new JSONObject();

            json.put("dato", "hola mundo");

            //Recibe mensaje del servidor
            DataInputStream istream = new DataInputStream(socket.getInputStream());
            response = istream.readUTF();

            // Envia mensaje al servidor
            OutputStream ostream = socket.getOutputStream();
            ObjectOutput s = new ObjectOutputStream(ostream);
            s.writeUTF(json.toString());
            s.flush();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }

}
