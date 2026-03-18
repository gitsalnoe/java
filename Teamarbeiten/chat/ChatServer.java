package net.tfobz.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ChatServer {

    public static final int PORT = 12345;

    protected static HashMap<String, ChatServerThread> clients = new HashMap<>();
    
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("ChatServer gestartet auf Port " + PORT);

            while (true) {
                Socket client = server.accept();
                System.out.println("Neuer Client verbunden");
                new ChatServerThread(client).start();
            }
        } catch (IOException e) {
            System.out.println("Serverfehler: " + e.getMessage());
        }
    }
}
