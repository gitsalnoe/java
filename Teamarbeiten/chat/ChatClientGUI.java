package net.tfobz.chat;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.tfobz.chat.*;

/**
 * Diese Klasse erstellt ein GUI welches es ermöglicht sich an einem ChatServer anzumelden, öffentliche Nachrichten und Commands zu senden und
 * unterstützt private Chats auf separaten Fenster mit anderen Users.
 * 
 */
public class ChatClientGUI extends JFrame {
	private JPanel contentPane;
    private JTextField nameField;
    private JTextField inputField;
    private JTextPane mainPane;
    private ChatClient client;
    private String user; //Namen des Benutzers

    private Map<String, PrivateChatWindow> privatFenster = new HashMap<>();
    private Map<String, Integer> ungeleseneNachrichten = new HashMap<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {	//startet das Chat GUI
            @Override
            public void run() {
                new ChatClientGUI().setVisible(true);
            }
        });
    }
    
    /**
     * Meldet den Benutzer mit dem Text von "name" beim Server an und startet den Thread zur verarbeitung von Nachrichten
     */
    private void login() {
        String name = nameField.getText().trim();		
        if (name.isEmpty() || client != null)	{
        	return;
        }	
        try {
            ChatClientThread t = new ChatClientThread() {
                @Override
                public void run() {
                    try {
                        String line;
                        while ((line = in.readLine()) != null) {
                            final String msg = line;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    handleServer(msg);
                                }
                            });
                        }
                    } catch (Exception e) {
                        append("[System] Verbindung verloren");
                    }
                }
            };

            client = new ChatClient("localhost", name, t);
            user = name;

            setTitle("ChatClientGUI von " + name);
            nameField.setEditable(false);			
            append("[System] Willkommen " + name);
            append("[System] /help für Befehle");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server nicht erreichbar");
        }
    }

    /**
     * Entscheidet was mit der von Server empfangenen Nachricht passiert
     * SYS: zeigt meldung an das user chat verlassen hat und schliesst sein privates Fenster
     * MSG: zeigt Nachricht im hauptfenster
     * DM: erhöht zähler der ungelesenen narchrichten
     * USERS: Zeigt liste alle angemeldeten users
     * ERROR: gibt fehlermeldung aus
     * @param msg, empfangene Nachricht 
     * 
     */
    private void handleServer(String msg) {
        if (msg.startsWith("SYS;")) {
            append("[System] " + msg.substring(4));
            if (msg.contains("hat den Chat verlassen")) {
                String u = msg.substring(4).split(" ")[0];
                PrivateChatWindow pc = privatFenster.remove(u);
                ungeleseneNachrichten.remove(u);
                if (pc != null) 	{
                	pc.dispose();
                }
            }
        }
        else if (msg.startsWith("MSG;")) {
            String[] p = msg.split(";", 3);
            append(p[1] + ": " + p[2]);
        }
        else if (msg.startsWith("DM;")) {
            String[] p = msg.split(";", 3);
            String from = p[1];
            String text = p[2];
            if (from.equals(user))	{
            	return;
            }
            ungeleseneNachrichten.put(from, ungeleseneNachrichten.getOrDefault(from, 0) + 1);
            PrivateChatWindow pc = privatFenster.get(from);
            if (pc == null) {
                pc = new PrivateChatWindow(user, from);
                privatFenster.put(from, pc);
            }
            pc.add(from + ": " + text);
            append("[System] Neue Privatnachricht von " + from);
        }
        else if (msg.startsWith("USERS;")) {
            append("[System] Online: " + msg.substring(6));
        }
        else if (msg.startsWith("ERROR;")) {
            append("[Fehler] " + msg.substring(6));
        }
    }

    /**
     * verarbeitet Befehle mit "/"
     * 
     * @param cmd
     */
    private void handleCommand(String cmd) {
        switch (cmd) {
            case "/help":
                append("[System] Befehle:");
                append("[System] /help");
                append("[System] /users");
                append("[System] /notifications");
                append("[System] /clear");
                break;

            case "/users":
                client.sendRaw("USERS");
                break;

            case "/notifications":
                int sum = 0;
                for (int i : ungeleseneNachrichten.values()) {
                    sum += i;
                }
                append("[System] Ungelesene Direktnachrichten: " + sum);
                break;

            case "/clear":
                mainPane.setText("");
                break;

            default:
                append("[System] Unbekannter Befehl");
        }
    }

    /**
     * Um text im Hauptfenster zu zeigen
     * @param s, der Text
     */
    private void append(String s) {
        mainPane.setText(mainPane.getText() + s + "\n");
    }

    public ChatClientGUI() {
        setTitle("ChatClientGUI");
        setSize(540, 460);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane = new JPanel(null);
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);

        JLabel l = new JLabel("Name:");
        l.setBounds(10,10,50,25);
        contentPane.add(l);

        nameField = new JTextField();
        nameField.setBounds(70,10,300,25);
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        contentPane.add(nameField);

        JButton logout = new JButton("Abmelden");
        logout.setBounds(390,10,120,25);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client != null) client.logout();
                client = null;
                user = null;
                privatFenster.clear();
                ungeleseneNachrichten.clear();
                nameField.setEditable(true);
                mainPane.setText("");
                setTitle("ChatClientGUI");
            }
        });
        contentPane.add(logout);

        mainPane = new JTextPane();
        mainPane.setEditable(false);
        JScrollPane sp = new JScrollPane(mainPane);
        sp.setBounds(10,45,500,280);
        contentPane.add(sp);

        inputField = new JTextField();
        inputField.setBounds(10,335,500,25);
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client == null) return;
                String txt = inputField.getText().trim();
                if (txt.isEmpty()) return;

                if (txt.startsWith("/")) handleCommand(txt);
                else client.sendMessage(txt);

                inputField.setText("");
            }
        });
        contentPane.add(inputField);

        JButton dmBtn = new JButton("Direktnachrichten");
        dmBtn.setBounds(10,370,500,25);
        dmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client == null) return;
                String target = JOptionPane.showInputDialog(ChatClientGUI.this,"Benutzername");
                if (target == null || target.trim().isEmpty() || target.equals(user)) return;

                ungeleseneNachrichten.remove(target);
                
                PrivateChatWindow pc = privatFenster.get(target);
                if (pc == null) {
                    pc = new PrivateChatWindow(user, target);
                    privatFenster.put(target, pc);
                }
                pc.setVisible(true);
            }
        });
        contentPane.add(dmBtn);
    }

    /**
     * Erstellt ein separates Fenster für ein privaten Chat mit einem anderern User
     */
    private class PrivateChatWindow extends JFrame {
        private JTextPane pane = new JTextPane();
        private JTextField input = new JTextField();
        private String to;	//Empfänger der Privaten Nachrichten

        public PrivateChatWindow(final String from, final String to) {
            this.to = to;
            setTitle("DM: " + from + " → " + to);
            setSize(500,400);
            setLayout(null);

            pane.setEditable(false);
            JScrollPane sp = new JScrollPane(pane);
            sp.setBounds(10,10,460,300);
            add(sp);

            input.setBounds(10,320,360,25);
            input.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    send();
                }
            });
            add(input);

            JButton b = new JButton("Senden");
            b.setBounds(380,320,90,25);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    send();
                }
            });
            add(b);
        }
        
        /**
         * Um private Nachrichten zu senden. 
         * Liest zuerst Text aus m und sendet ihn als "DM" zum anderen Client
         */
        private void send() {
            String m = input.getText().trim();
            if (m.isEmpty()) {
               	return;
            }
            client.sendDM(to, m);
            add("Ich: " + m);
            input.setText("");
        }
        
        /**
         * Zeigt empfangene Nachricht im privaten Fenster an.
         * Macht das gleiche wie append aber setzt den Text auf pane;
         * @param s
         */
        public void add(String s) {
            pane.setText(pane.getText() + s + "\n");
        }
    }
}