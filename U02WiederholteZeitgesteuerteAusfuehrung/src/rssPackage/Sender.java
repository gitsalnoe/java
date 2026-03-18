package rssPackage;

import java.util.ArrayList;
import java.util.List;

public class Sender {
    private String titel;
    private List<Nachricht> nachrichten = new ArrayList<>();

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<Nachricht> getNachrichten() {
        return nachrichten;
    }

    public void addNachricht(Nachricht nachricht) {
        this.nachrichten.add(nachricht);
    }
}