package u03ZustaendeThreads;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JPGImageCompressionGUI extends JFrame {
	private JPanel contentPane;
	private ImageComponent imageComponent;
	private JSpinner granulitatSpinner;
	private JProgressBar progressBar;
	private JButton btnOpen;
	
	private JButton btnCompressAndSave;
	private JButton btnCompress;
	
	private File currentFile;
	private BufferedImage originalImage;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPGImageCompressionGUI frame = new JPGImageCompressionGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JPGImageCompressionGUI() {
		setTitle("JPG-Compression");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		imageComponent = new ImageComponent();
		contentPane.add(imageComponent, BorderLayout.CENTER);

		JPanel panelControls = new JPanel();
		panelControls.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(panelControls, BorderLayout.SOUTH);

		/**
		 * Button für das Öffnen eines jpgs 
		 */
		btnOpen = new JButton("Open...");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(".")); 
				int result = chooser.showOpenDialog(JPGImageCompressionGUI.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						currentFile = chooser.getSelectedFile();
						imageComponent.setImage(currentFile);
						originalImage = imageComponent.getImage();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(JPGImageCompressionGUI.this, "Fehler: " + ex.getMessage());
					}
				}
			}
		});
		panelControls.add(btnOpen);

		granulitatSpinner = new JSpinner();
		granulitatSpinner.setModel(new SpinnerNumberModel(0.1, 0.01, 0.1, 0.01));
		panelControls.add(granulitatSpinner);

		/**
		 * Button für das Comprimieren und dann speichern des comprimierten jpgs
		 */
		btnCompressAndSave = new JButton("Compress and save");
		btnCompressAndSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setButtonsEnabled(false);	//Deaktiviert alle anderen Buttons
				progressBar.setValue(0);
				
				double granulitat = (Double) granulitatSpinner.getValue();
				final int steps = (int) Math.round(1.0 / granulitat) + 1;	// Berechnet die Bilder die gemacht werden müssen um von 0.0 zu 1.0 zu kommen
				
				// Zählt die fertigen Threads um den fortschitt zu berechnen und dann das ende erkennen
				final int[] finishedCounter = {0};

				// Schleife erstellt für jede Qualität einen eigenen Thread
				for (int i = 0; i <= steps; i++) {
					final double quality = i * granulitat; // hier auch granulitat nutzen
					if (quality > 1.0001) {
						break;
					}

					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								
								String name = currentFile.getName();	// Dateiname
								String baseName = name.substring(0, name.lastIndexOf('.'));	//Entfernt endung wie .jpg
								String path = currentFile.getParent() + File.separator + baseName + ".";	// Macht den Pfad zum Bild
								
								// Speichern
								JPGImageCompress.compressImage(originalImage, path, quality);
								
								// aktualisieren mit synchronized
								synchronized (finishedCounter) {
									finishedCounter[0]++;
									final int current = finishedCounter[0];
									
									SwingUtilities.invokeLater(new Runnable() {
										public void run() {
											int prozent = (int)((current / (double)steps) * 100);
											progressBar.setValue(prozent);
											
											// Buttons aktivieren wenn alle fertig sind
											if (current == steps) {
												setButtonsEnabled(true);
												JOptionPane.showMessageDialog(JPGImageCompressionGUI.this, "Fertig gespeichert!");
											}
										}
									});
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					});
					t.start();
				}
			}
		});
		panelControls.add(btnCompressAndSave);

		/**
		 * Animation des fortschritbalken für Kompress
		 */
		btnCompress = new JButton("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (originalImage == null) return;

				setButtonsEnabled(false);
				progressBar.setValue(0);
				
				final double granulitat = (Double) granulitatSpinner.getValue();
				final int steps = (int) Math.round(1.0 / granulitat) + 1;

				Thread animThread = new Thread(new Runnable() {
					public void run() {
						for (int i = 0; i <= steps; i++) {
							double quality = i * granulitat;
							
							try {
								// Bild komprimieren
								final BufferedImage compressed = JPGImageCompress.compressImage(originalImage, quality);
								
								// Bild anzeigen
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										imageComponent.setImage(compressed);
									}
								});

								// animation des Fortschrittsbalken updaten
								final int percent = (int)(((i + 1) / (double)(steps + 1)) * 100);
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										progressBar.setValue(percent);
									}
								});
								
								// warten
								Thread.sleep(500);
								
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						
						// original wiederherstellen und Buttons aktivieren
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								imageComponent.setImage(originalImage);
								setButtonsEnabled(true);
							}
						});
					}
				});
				animThread.start();
			}
		});
		panelControls.add(btnCompress);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		panelControls.add(progressBar);
	}

	/**
	 * Schaltet Buttons des JPanels panelControl ein oder aus
	 * @param enabled, ein (true) oder aussschalten (false)
	 */
	private void setButtonsEnabled(boolean enabled) {
		btnOpen.setEnabled(enabled);
		btnCompress.setEnabled(enabled);
		btnCompressAndSave.setEnabled(enabled);
		granulitatSpinner.setEnabled(enabled);
	}
}
