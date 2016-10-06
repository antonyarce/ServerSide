package com.androidsrc.server;

/**
 * Created by antonyarce on 12/9/16.
 */

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress;
    int dstPort;
    String response = "";
    Socket socket;
    JSONObject json;
    String mensaje;


    Client(String addr, int port,String mensaje) {
        dstAddress = addr;
        dstPort = port;
        this.mensaje=mensaje;
        System.out.println(mensaje);
    }


    @Override
    protected Void doInBackground(Void... arg0) {
        socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);





            // Envia mensaje al servidor
            OutputStream ostream = socket.getOutputStream();
            ObjectOutput s = new ObjectOutputStream(ostream);
            s.writeUTF(mensaje);
            s.flush();

            //Recibe mensaje del servidor
            DataInputStream istream = new DataInputStream(socket.getInputStream());
            response = istream.readUTF();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        }finally {
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
        super.onPostExecute(result);
    }

}
