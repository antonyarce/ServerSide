package com.androidsrc.server;

/**
 * Created by allan on 12/09/16.
 */
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;


public class ServerCliente {
    MainActivity activity;
    ServerSocket serverSocket;
    String message = "";
    String texto;
    int socketServerPORT;

    public ServerCliente(MainActivity activity, int port) {
        this.activity = activity;
        socketServerPORT = port;
        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.start();
    }

    public int getPort() {
        return socketServerPORT;
    }

    public void onDestroy() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private class SocketServerThread extends Thread {

        int count = 0;

        @Override
        public void run() {
            try {
                // create ServerSocket using specified port
                serverSocket = new ServerSocket(socketServerPORT);

                while (true) {
                    // block the call until connection is created and return
                    // Socket object
                    Socket socket = serverSocket.accept();
                    count++;

                    // Recibe, si es mensaje del cliente
                    if (socketServerPORT == 9090) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        texto = in.readLine();
                        System.out.println(texto);
                    }

                    /*message += "#" + count + " from "
                            + socket.getInetAddress() + ":"
                            + socket.getPort() +" "+texto+"\n";*/


                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //activity.msg.setText(message);
                        }
                    });

                    SocketServerReplyThread socketServerReplyThread =
                            new SocketServerReplyThread(socket, count);
                    socketServerReplyThread.run();

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private class SocketServerReplyThread extends Thread {

        private Socket hostThreadSocket;
        int cnt;

        SocketServerReplyThread(Socket socket, int c) {
            hostThreadSocket = socket;
            cnt = c;
        }

        @Override
        public void run() {
            OutputStream outputStream;
            try {
                //Envia, si recibio del cliente
                if (socketServerPORT == 9090) {
                    outputStream = hostThreadSocket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    printStream.print("Hola desde el manager");
                    printStream.close();

                    // Si la conexion es con el nodo
                } else if (socketServerPORT == 8080) {
                    // Envia mensaje al nodo
                    DataOutputStream ostream = new DataOutputStream(hostThreadSocket.getOutputStream());
                    ostream.writeUTF("Hola desde el manager");
                    ostream.flush();

                    // Recibe mensaje del nodo
                    InputStream istream = hostThreadSocket.getInputStream();
                    ObjectInput in = new ObjectInputStream(istream);
                    message = in.readUTF();
                    //json = new JSONObject(message);
                }

                /*message += "replayed: " + msgReply + "\n";*/

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.msg.setText(message);
                    }
                });

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                message += "Something wrong! " + e.toString() + "\n";
            }

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    activity.msg.setText(message);
                }
            });
        }

    }

    public String getIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress
                            .nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += "Server running at : "
                                + inetAddress.getHostAddress();
                    }
                }
            }

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }
        return ip;
    }
}
