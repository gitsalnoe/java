package net.tfobz.chat;

import java.io.*;
import java.net.Socket;

public class ChatClient {

    public static final int PORT = 12345;

    private Socket socket;
    private PrintWriter out;

    public ChatClient(String host, String username, ChatClientThread listener) throws IOException {
        socket = new Socket(host, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        listener.setReader(in);
        listener.start();

        System.out.println("Verbunden mit Server " + host);
        out.println("LOGIN;" + username);
    }

    public void sendMessage(String msg) {
        System.out.println("Sende Nachricht: " + msg);
        out.println("MSG;" + msg);
    }

    public void sendDM(String target, String msg) {
        System.out.println("Sende Direktnachricht an " + target + ": " + msg);
        out.println("DM;" + target + ";" + msg);
    }

    public void logout() {
        System.out.println("Abmeldung vom Server");
        out.println("LOGOUT");
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Schließen der Verbindung");
        }
    }
    public void sendRaw(String msg) {
        out.println(msg);
    }

}
