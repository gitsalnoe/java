package net.tfobz.chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientThread extends Thread {

    protected BufferedReader in;

    public void setReader(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {

                if (line.startsWith("SYS;")) {
                    System.out.println("Systemmeldung: " + line.substring(4));
                } 
                else if (line.startsWith("MSG;")) {
                    System.out.println("Nachricht: " + line);
                } 
                else if (line.startsWith("DM;")) {
                    System.out.println("Direktnachricht: " + line);
                } 
                else if (line.startsWith("ERROR;")) {
                    System.out.println("Fehler: " + line.substring(6));
                } 
                else {
                    System.out.println("Server: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Verbindung zum Server verloren");
        }
    }
}
