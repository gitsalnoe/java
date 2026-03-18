package net.gobbz.kontoverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.*;

public class KontoVerwaltungGUI extends JFrame{

    private static final int MAX_KONTEN = 100;
    private final Konto[] konten = new Konto[MAX_KONTEN];
    private static final double STARTZINSSATZ = 0.25;
    private static final double STARTUEBERZIEHUNG = -1000.0; 
    
    private int anzahlKonten = 0;
    
    private final JTextField tfEingabeOben1 = new JTextField(10);
    private final JTextField tfEingabeOben2 = new JTextField(10);
    
    private final JLabel lblKontoNr1 = new JLabel("Kontonummer 1:");
    private final JLabel lblKontoNr2 = new JLabel("Kontonummer 2:");
    
    private final JTextField tfKontonummer1 = new JTextField(10);
    private final JTextField tfKontonummer2 = new JTextField(10);

    private final JButton btnNeuesGehaltskonto = new JButton("Neues Gehaltskonto");
    private final JButton btnNeuesSparkonto = new JButton("Neues Sparkonto");
    private final JButton btnAnzeigen = new JButton("Anzeigen");
    private final JButton btnBuchen = new JButton("Buchen");
    private final JButton btnUeberweisen = new JButton("Überweisen");
    
    private final JTextArea taAusgabe = new JTextArea(15, 50);

    public KontoVerwaltungGUI() {
        super("Kontoverwaltung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        setSize(700, 400);
        
        JPanel pnlKontos = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlKontos.add(tfEingabeOben1);
        pnlKontos.add(tfEingabeOben2);
        pnlKontos.add(lblKontoNr1);
        pnlKontos.add(lblKontoNr2);
        pnlKontos.add(tfKontonummer1);
        pnlKontos.add(tfKontonummer2);
        
        JPanel pnlButtons = new JPanel(new GridLayout(3, 2, 2, 2));
        pnlButtons.add(btnNeuesGehaltskonto);
        pnlButtons.add(btnNeuesSparkonto);
        pnlButtons.add(btnAnzeigen);
        pnlButtons.add(new JLabel("")); // um das Grid zu füllen
        pnlButtons.add(btnBuchen);
        pnlButtons.add(btnUeberweisen);

        JPanel pnlOben = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlOben.add(pnlKontos);
        pnlOben.add(pnlButtons);
        
        add(pnlOben, BorderLayout.NORTH);
        
        taAusgabe.setEditable(false);
        taAusgabe.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(taAusgabe), BorderLayout.CENTER);
        
        /**
         * Dieser button erstellt ein neues gehaltskonto und zeigt sie in taAusgabe an
         */
        btnNeuesGehaltskonto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (anzahlKonten >= MAX_KONTEN) {
                    JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, "maximale Anzahl an kontos", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Konto neuesKonto = new Gehaltskonto();
                konten[anzahlKonten] = neuesKonto;
                anzahlKonten++;
                taAusgabe.append("neues Gehaltskonto: " + neuesKonto.toString() + "\n");
            }
        });
        
        /**
         * Erstellt ein neues Sparkonto, liest erste Zahlung und Sparrate aus den
         * oberen Eingabefeldern, gibt die Kontodaten aus und löscht die Felder wieder.
         */
        btnNeuesSparkonto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (anzahlKonten >= MAX_KONTEN) {
                    JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, "maximale anzahl an konten", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    double ersteZahlung = Double.parseDouble(tfEingabeOben1.getText());
                    double sparrate = Double.parseDouble(tfEingabeOben2.getText());
                    Konto neuesKonto = new Sparkonto(ersteZahlung, sparrate);
                    konten[anzahlKonten] = neuesKonto;
                    anzahlKonten++;
                    taAusgabe.append("Neues Sparkonto: " + neuesKonto.toString() + "\n");
                    tfEingabeOben1.setText("");
                    tfEingabeOben2.setText("");
                } catch (KontoException ex) {
                    JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, ex.getMessage(), "KontoExceptionFehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /**
         * Zeigt die Daten eines Kontos an Die Kontonummer wird aus dem Textfeld
         * genommen, die Daten werden ausgegeben und das Feld wird danach gelöscht.
         */
        btnAnzeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    int kontoNr = Integer.parseInt(tfKontonummer1.getText());
                    Konto konto = findeKonto(kontoNr);
                    if (konto != null) {
                        taAusgabe.append("Anzeigen: " + konto.toString() + "\n");
                    } else {
                        JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, "Konto mit nummer:" + kontoNr + " nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                    tfKontonummer1.setText("");
            }
        });

        /**
         * bucht einen Betrag auf ein Konto, der Betrag kommt aus dem ersten Eingabefeld,
         * die Kontonummer aus dem unterem Textfeld. Nach Erfolg werden alle
         * Eingaben gelöscht und die daten ausgegebne
         */
        btnBuchen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    double betrag = Double.parseDouble(tfEingabeOben1.getText());
                    int kontoNr = Integer.parseInt(tfKontonummer1.getText()); // Buchen auf KontoNr 1
                    Konto konto = findeKonto(kontoNr);
                    
                    if (konto != null)			 {
                        konto.buchen(betrag);
                        taAusgabe.append("Buchen erfolgreich: " + konto.toString() + "\n");
                        tfEingabeOben1.setText("");
                        tfKontonummer1.setText("");
                        tfKontonummer2.setText(""); // Alle eingaben löschen
                    } else {
                        JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, "Konto mit nummer " + kontoNr + " nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (KontoException ex) {
                    JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, ex.getMessage(), "Fehler beim Buchen", JOptionPane.ERROR_MESSAGE);
                		}	
            }
        });

        /**
         * überweist einen Betrag von einem Konto auf ein anderes. Der Betrag wird aus
         * dem ersten Eingabefeld genommen und die quell und zielkontos aus den Kontonummer Feldern
         * geholt. Bei Erfolg wird eine Meldung ausgegeben und die Felder werden gelöscht.
         */
        btnUeberweisen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double betrag = Double.parseDouble(tfEingabeOben1.getText());
                    int vonKontoNr = Integer.parseInt(tfKontonummer1.getText());
                    int aufKontoNr = Integer.parseInt(tfKontonummer2.getText());
                    Konto vonKonto = findeKonto(vonKontoNr);
                    Konto aufKonto = findeKonto(aufKontoNr);
                    if (vonKonto == null) {
                        JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, "Quellkonto " + vonKontoNr + " nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (aufKonto == null) {
                        JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, "Zielkonto " + aufKontoNr + " nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    	}

                    vonKonto.ueberweisen(aufKonto, betrag);
                    taAusgabe.append("Überweisen von Konto " + vonKontoNr + " nach Konto " + aufKontoNr + " Betrag " + betrag + " erfolgreich \n");
                    
                    tfEingabeOben1.setText("");
                    tfKontonummer1.setText("");
                    tfKontonummer2.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, "Ungültige Eingabe für Betrag oder Kontonummern.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                } catch (KontoException ex) {
                    JOptionPane.showMessageDialog(KontoVerwaltungGUI.this, ex.getMessage(), "Überweisungsfehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    /**
     * Sucht das Konto anhand seiner nummer im array
     * @param kontoNummer, die zu suchende nummer des kontos
     * @return Das konto Objekt oder null wenn es nicht gefunden wird
     */
    private Konto findeKonto(int kontoNummer) {
        for (int i = 0; i < anzahlKonten; i++) {
            if (konten[i].getKontoNummer() == kontoNummer) {
                return konten[i];
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
    	
        try {
			Konto.setStartzinssatz(STARTZINSSATZ);
		} catch (KontoException e) {
			e.printStackTrace();
		}
        try {
			Gehaltskonto.setStartueberziehung(STARTUEBERZIEHUNG);
		} catch (KontoException e) {
			e.printStackTrace();
		}

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KontoVerwaltungGUI().setVisible(true);
            }
        });
    } 


}
