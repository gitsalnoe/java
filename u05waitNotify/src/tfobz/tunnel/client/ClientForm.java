package tfobz.tunnel.client;
import javax.swing.DefaultListModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**

    Diese Klasse erstellt die Benutzerschnittstelle und den GuidesMonitor zur

    Verwaltung der Gruppenf�hrer pro Eingang. Sie enth�lt auch die

    Ereignisbehandlungsmethoden f�r die beiden Kn�pfe. In diesen Methoden werden

    die Objekte vom Typ ClientThread zur Behandlung der Clientanfragen angelegt

    und die Threads gestartet.<br><br>

    <b>Ereignisbehandlungsmethode Besichtigung anfordern</b><br>

    Diese Methode kontrolliert zuerst, ob eine Besucherzahl ins Textfeld

    eingegeben wurde und konvertiert den Inhalt in eine Zahl. Diese Zahl

    darf nicht gr��er sein als das maximale Fassungsverm�gen des Tunnels.

    Dann wird der ClientThread gestartet, dem diese Besucheranzahl und

    die Referenzen auf das ClientForm sowie auf den GuidesMonitor

    �bergeben werden.<br><br>

    <b>Ereignisbehandlungsmethode Besichtigung beenden</b><br>

    Zuerst wird kontrolliert ob es �berhaupt Aktive Besichtigungen gibt,

    welche von diesem Eingang aus den Tunnel betreten haben. Sind solche

    vorhanden, dann wird kontrolliert, ob eine aktive Besichtigung

    ausgew�hlt wurde. Ist dies der Fall so wird aus dem Text des ausgew�hlten

    JList-Eintrages die Anzahl der Besucher ermittelt und in eine Zahl

    konvertiert. Dann wird der ClientThred gestartet, dem diese negative (!)

    Anzahl und Referenzen auf ClientForm und GuidesMonitor

    �bergeben werden
    */
    public class ClientForm extends JFrame {
    	    
    	    /**
    	        Monitor durch welchen am Eingang ein Fhrer reserviert werden kann
    	    */
    	    private GuidesMonitor fuehrerMonitor = null;
    	    private JLabel lblFuehrer;
    	    private JTextField txtBesucher;
    	    private JList<String> ersteBesuche;
    	    private JLabel lblVerfuegbareBesucher;
    	    private JTextArea txtProtokoll;

    	    /**
    	        Modell zur Verwaltung der Inhalte der JList
    	    */
    	    protected DefaultListModel<String> mActiveVisits = null;
    	    
    	    public ClientForm() {
    	        super("Entrance 1");
    	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        setSize(550, 400);
    	        setLocationRelativeTo(null);
    	        fuehrerMonitor = new GuidesMonitor(this);
    	        mActiveVisits = new DefaultListModel<>();
    	        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    	        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    	        //steuerung
    	        JPanel leftPanel = new JPanel();
    	        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

    	        //eingabe
    	        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 5, 5));
    	        pnlInput.add(new JLabel("Available guides:"));
    	        lblFuehrer = new JLabel(String.valueOf(fuehrerMonitor.getAvailableGuides()));
    	        pnlInput.add(lblFuehrer);
    	        pnlInput.add(new JLabel("Visitors:"));
    	        txtBesucher = new JTextField();
    	        pnlInput.add(txtBesucher);
    	        pnlInput.add(new JLabel("")); // Platzhalter
    	        JButton btnRequest = new JButton("Request visit");
    	        pnlInput.add(btnRequest);
    	        leftPanel.add(pnlInput);
    	        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    	        //liste
    	        leftPanel.add(new JLabel("Active visits:"));
    	        ersteBesuche = new JList<>(mActiveVisits);
    	        JScrollPane scrollList = new JScrollPane(ersteBesuche);
    	        leftPanel.add(scrollList);
    	        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));

    	        JButton btnFinish = new JButton("Finish visit");
    	        leftPanel.add(btnFinish);
    	        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    	        //status 
    	        JPanel pnlStatus = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    	        pnlStatus.add(new JLabel("Available Visitors: "));
    	        lblVerfuegbareBesucher = new JLabel("-");
    	        pnlStatus.add(lblVerfuegbareBesucher);
    	        leftPanel.add(pnlStatus);

    	        //logausgabe
    	        txtProtokoll = new JTextArea();
    	        txtProtokoll.setEditable(false);
    	        JScrollPane scrollLog = new JScrollPane(txtProtokoll);
    	        
    	        mainPanel.add(leftPanel, BorderLayout.WEST);
    	        mainPanel.add(scrollLog, BorderLayout.CENTER);
    	        add(mainPanel);

    	        //starten der besichtigung
    	        btnRequest.addActionListener(new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) {
    	                try {
    	                    int count = Integer.parseInt(txtBesucher.getText().trim());
    	                    if (count > 0 && count <= 50) {
    	                        new ClientThread(count, ClientForm.this, fuehrerMonitor).start();
    	                    } else {
    	                        JOptionPane.showMessageDialog(ClientForm.this, "Gültige Besucherzahl (1-50) eingeben!");
    	                    }
    	                } catch (NumberFormatException ex) {
    	                    JOptionPane.showMessageDialog(ClientForm.this, "Bitte eine gültige Zahl eingeben.");
    	                }
    	            }
    	        });

    	        //beenden der besichtigung
    	        btnFinish.addActionListener(new ActionListener() {
    	            @Override
    	            public void actionPerformed(ActionEvent e) {
    	                String selected = ersteBesuche.getSelectedValue();
    	                if (selected != null) {
    	                    try {
    	                        String numStr = selected.split(" ")[0]; // Extrahiert die Zahl am Anfang
    	                        int count = Integer.parseInt(numStr);
    	                        new ClientThread(-count, ClientForm.this, fuehrerMonitor).start();
    	                    } catch (Exception ex) {
    	                        ex.printStackTrace();
    	                    }
    	                } else {
    	                    JOptionPane.showMessageDialog(ClientForm.this, "Bitte eine Gruppe aus der Liste auswählen.");
    	                }
    	            }
    	        });
    	    }

    	    public void addLog(final String message) {
    	        SwingUtilities.invokeLater(new Runnable() {
    	            @Override
    	            public void run() {
    	                txtProtokoll.append(message + "\n");
    	                txtProtokoll.setCaretPosition(txtProtokoll.getDocument().getLength()); // Autoscroll
    	            }
    	        });
    	    }

    	    public void updateGuidesLabel(final int count) {
    	        SwingUtilities.invokeLater(new Runnable() {
    	            @Override
    	            public void run() {
    	                lblFuehrer.setText(String.valueOf(count));
    	            }
    	        });
    	    }

    	    public void updateAvailableVisitorsLabel(final int count) {
    	        SwingUtilities.invokeLater(new Runnable() {
    	            @Override
    	            public void run() {
    	                lblVerfuegbareBesucher.setText(String.valueOf(count));
    	            }
    	        });
    	    }

    	    public void addActiveVisitToList(final int count) {
    	        SwingUtilities.invokeLater(new Runnable() {
    	            @Override
    	            public void run() {
    	                mActiveVisits.addElement(count + " visitors");
    	            }
    	        });
    	    }

    	    public void removeActiveVisitFromList(final int count) {
    	        SwingUtilities.invokeLater(new Runnable() {
    	            @Override
    	            public void run() {
    	                String toRemove = count + " visitors";
    	                for (int i = 0; i < mActiveVisits.getSize(); i++) {
    	                    if (mActiveVisits.getElementAt(i).equals(toRemove)) {
    	                        mActiveVisits.remove(i);
    	                        break;
    	                    }
    	                }
    	            }
    	        });
    	    }

    	/**
    	    Legt das Formular an und macht es sichtbar. Beim Anlegen des Forumulas
    	    wird auch der GuidesMonitor angelegt. Nachdem das Formular angelegt wurde,
    	    werden in Abstnden von einer Sekunde Serveranfragen geschickt zur
    	    Ermittlung der verfgbaren Besucher, d. h. der Server antwortet und
    	    liefert die Anzahl je Besucheranzahl zurck die noch in den Tunnel
    	    eingelassen werden kann

    	    @param args
    	    */
    	    public static void main(String[] args) {
    	        SwingUtilities.invokeLater(new Runnable() {
    	            @Override
    	            public void run() {
    	                final ClientForm form = new ClientForm();
    	                form.setVisible(true);
    	                Timer timer = new Timer(1000, new ActionListener() {
    	                    @Override
    	                    public void actionPerformed(ActionEvent e) {
    	                        new ClientThread(0, form, form.fuehrerMonitor).start();
    	                    }
    	                });
    	                timer.start();
    	            }
    	        });
    	    }
}
