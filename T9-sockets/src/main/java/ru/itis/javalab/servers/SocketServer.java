package ru.itis.javalab.servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;


public class SocketServer {


    public void start(int port) {
        ServerSocket server;
        List<Socket> clients;
        //TODO: PrintWriterAndClient обёртку и передовать в proses вместо сlient, чтоб часто не создовать PrintWriter

        try {
            server = new ServerSocket(port);
            clients = new LinkedList<>();
            while (true) {
                Socket client = server.accept();
                clients.add(client);
                (new Thread(() ->
                    proses(client,clients)
                )).start();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void proses(Socket client, List<Socket> clients) {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String messageFromClient = fromClient.readLine();
            while (messageFromClient != null) {
                for (Socket otherClient : clients) {
                    if (otherClient != client) {
                        PrintWriter toClient = new PrintWriter(otherClient.getOutputStream(), true);
                        toClient.println(messageFromClient);
                    }

                }

                messageFromClient = fromClient.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


}
