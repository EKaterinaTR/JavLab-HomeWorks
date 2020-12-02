package ru.itis.javalab.main;

import ru.itis.javalab.servers.SocketServer;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainForServer {
    public static void main(String[] args) {
        //TODO: JCommander
        SocketServer serverSocket = new SocketServer();
        serverSocket.start(727);
    }
}
