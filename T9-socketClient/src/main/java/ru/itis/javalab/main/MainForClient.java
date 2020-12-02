package ru.itis.javalab.main;

import ru.itis.javalab.clients.SocketClient;

import java.util.Scanner;

/**
 * 30.11.2020
 * 07. Sockets
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainForClient {
    public static void main(String[] args) {
        //TODO: JCommander
        SocketClient client = new SocketClient("localhost", 727);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }

}
