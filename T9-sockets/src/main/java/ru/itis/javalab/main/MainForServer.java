package ru.itis.javalab.main;

import com.beust.jcommander.JCommander;
import ru.itis.javalab.servers.SocketServer;
import ru.itis.javalab.utils.Args;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainForServer {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        SocketServer serverSocket = new SocketServer();
        serverSocket.start(args.port);
    }
}
