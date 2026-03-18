package net.tfobz.chat;

import java.io.*;
import java.net.Socket;

public class ChatServerThread extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ChatServerThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String login = in.readLine();
            if (login == null || !login.startsWith("LOGIN;")) {
                out.println("ERROR;Ungültiger Login");
                return;
            }

            username = login.substring(6);

            synchronized (ChatServer.clients) {
                if (ChatServer.clients.containsKey(username)) {
                    out.println("ERROR;Benutzername existiert bereits");
                    return;
                }
                ChatServer.clients.put(username, this);
            }

            broadcast("SYS;" + username + " hat den Chat betreten");

            String line;
            while ((line = in.readLine()) != null) {
                handle(line);
            }

        } catch (IOException e) {
        } finally {
            disconnect();
        }
    }

    private void handle(String line) {
        if (line.equals("USERS")) {
            sendUserList();
            return;
        }

        String[] parts = line.split(";", 3);

        switch (parts[0]) {
            case "MSG":
                broadcast("MSG;" + username + ";" + parts[1]);
                break;

            case "DM":
                sendDM(parts[1], parts[2]);
                break;

            case "LOGOUT":
                disconnect();
                break;
        }
    }

    private void sendUserList() {
        StringBuilder sb = new StringBuilder();
        synchronized (ChatServer.clients) {
            for (String name : ChatServer.clients.keySet()) {
                sb.append(name).append(",");
            }
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        out.println("USERS;" + sb.toString());
    }

    private void sendDM(String target, String text) {
        ChatServerThread t = ChatServer.clients.get(target);
        if (t == null) {
            out.println("ERROR;Benutzer nicht gefunden");
            return;
        }
        t.out.println("DM;" + username + ";" + text);
    }

    private void broadcast(String msg) {
        synchronized (ChatServer.clients) {
            for (ChatServerThread t : ChatServer.clients.values()) {
                t.out.println(msg);
            }
        }
    }

    private void disconnect() {
        if (username != null) {
            synchronized (ChatServer.clients) {
                ChatServer.clients.remove(username);
            }
            broadcast("SYS;" + username + " hat den Chat verlassen");
            username = null;
        }
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
}
