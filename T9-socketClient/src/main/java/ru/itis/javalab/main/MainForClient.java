package ru.itis.javalab.main;

import com.beust.jcommander.JCommander;
import ru.itis.javalab.clients.SocketClient;
import ru.itis.javalab.utils.Args;

import java.util.Scanner;

public class MainForClient {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        SocketClient client = new SocketClient(args.serverIP, args.port);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }

}
