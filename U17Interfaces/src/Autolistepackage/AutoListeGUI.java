package Autolistepackage;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import iteratorPackage.MeinIterator;
import iteratorPackage.MeineDefaultListe;

public class AutoListeGUI extends JFrame {
	private MeineDefaultListe autoListe = new MeineDefaultListe();
	private MeinIterator autoIterator = autoListe.elemente();
	private JLabel nameLabel = null;
	private JLabel zulassungLabel = null;
	private JTextField nameText = null;
	private JTextField zulassungText = null;
	private JButton nextb = null;
	private JButton newb = null;
	private JButton deleteb = null;
	
	public AutoListeGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int height = 190;
		int width = 390;
		this.setBounds(100, 100, width, height);
		this.setResizable(false);
		this.setTitle("AutoListe");
		this.getContentPane().setLayout(null);
		
		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		nameLabel.setBounds(-48, 31, 150, 20);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_A);
		this.getContentPane().add(nameLabel);
		
		zulassungLabel = new JLabel();
		zulassungLabel.setText("Erstzulassung:");
		zulassungLabel.setBounds(-48, 60, 150, 20);
		zulassungLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		zulassungLabel.setDisplayedMnemonic(KeyEvent.VK_S);
		this.getContentPane().add(zulassungLabel);
		
		nameText = new JTextField();
		nameText.setBounds(112, 30, 120, 24);
		nameText.setHorizontalAlignment(SwingConstants.LEFT);
		this.getContentPane().add(nameText);
		nameLabel.setLabelFor(nameText);
		
		zulassungText = new JTextField();
		zulassungText.setBounds(112, 59, 120, 24);
		zulassungText.setHorizontalAlignment(SwingConstants.LEFT);
		this.getContentPane().add(zulassungText);
		zulassungLabel.setLabelFor(zulassungText);
		
		nextb = new JButton();
		nextb.setText("Nächstes");
		nextb.setBounds(10, 98, 120, 42);
		nextb.setMnemonic(KeyEvent.VK_N);
		nextb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (autoListe.istLeer()) {
					JOptionPane.showMessageDialog(
							AutoListeGUI.this,
							"Kein nächstes element",
							"liste ist leer",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					int zulassung = Integer.parseInt(zulassungText.getText());
					Auto neuesAutoObjekt = new Auto(nameText.getText(), zulassung);
					autoIterator.setzenAktuellesElement(neuesAutoObjekt);
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(
							AutoListeGUI.this,
							"geben sie einen gültigen wert ein",
							" ",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Auto auto = (Auto) autoIterator.naechstesElement();
				
				if (auto == null) {
					JOptionPane.showMessageDialog(
							AutoListeGUI.this,
							"ende der Liste erreicht",
							" ",
							JOptionPane.INFORMATION_MESSAGE);
					
					auto = (Auto)autoIterator.naechstesElement();
				}
				
				if (auto != null) {
					nameText.setText(auto.getName());
					zulassungText.setText(String.valueOf(auto.getErstzulassung()));
				} else {
					nameText.setText("");
					zulassungText.setText("");
				}
			}
		});
		
		this.getContentPane().add(nextb);
	
		newb = new JButton();
		newb.setText("Neu");
		newb.setBounds(140, 98, 100, 42);
		newb.setMnemonic(KeyEvent.VK_E);
		newb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (autoListe.istLeer()) {
					autoIterator.einfuegenElement(new Auto());
					nameText.setText("");
					zulassungText.setText("");
				}
				else {
					try {
						int zulassung = Integer.parseInt(zulassungText.getText());
						Auto a = new Auto(nameText.getText(), zulassung);
						autoIterator.setzenAktuellesElement(a);
						
						autoIterator.einfuegenElement(new Auto());
						
						nameText.setText("");
						zulassungText.setText("");
					} catch (NumberFormatException err) {
						JOptionPane.showMessageDialog(
								AutoListeGUI.this,
								"gültigen wert eingeben bitte",
								" ",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		this.getContentPane().add(newb);
		
		deleteb = new JButton();
		deleteb.setText("Löschen");
		deleteb.setBounds(250, 98, 110, 42);
		deleteb.setMnemonic(KeyEvent.VK_L);
		deleteb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (autoListe.istLeer()) {
					JOptionPane.showMessageDialog(
							AutoListeGUI.this,
							"löschen nicht möglich ",
							"liste ist leer",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				autoIterator.loeschenAktuellesElement();
				
				nameText.setText("");
				zulassungText.setText("");
				
				if (autoListe.istLeer()) {
					JOptionPane.showMessageDialog(
							AutoListeGUI.this,
							"liste ist leer",
							"",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Auto auto = (Auto) autoIterator.naechstesElement();
					
					if (auto == null) {
						JOptionPane.showMessageDialog(
								AutoListeGUI.this,
								"ende der Liste erreicht",
								" ",
								JOptionPane.INFORMATION_MESSAGE);
						auto = (Auto)autoIterator.naechstesElement();
					}
					
					nameText.setText(auto.getName());
					zulassungText.setText(String.valueOf(auto.getErstzulassung()));
				}
			}
		});
		this.getContentPane().add(deleteb);
		
		this.getRootPane().setDefaultButton(newb);
	}
	
	public static void main(String[] args) {
			AutoListeGUI autogui = new AutoListeGUI();
			autogui.setVisible(true);
	}

}
