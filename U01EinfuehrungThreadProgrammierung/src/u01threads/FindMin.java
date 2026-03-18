package u01threads;

import java.awt.EventQueue;

import javax.swing.*;

public class FindMin extends Thread{

	protected static int[] intfeld = new int[10000000];	
	protected static int min;
	protected JTextField textField;
	protected JProgressBar progressBar;

	static { 											// Füllt das Array intfeld mit zufalligen Werten zwischen 0 und Integer.MAX
		for (int i = 0; i < intfeld.length; i++)	{		
			intfeld[i] = (int)(Math.random() * (Integer.MAX_VALUE - 1));
		}	
	}
	
	public FindMin(String name, JTextField textfield, JProgressBar progressbar)	{
		super(name);
		this.textField = textfield;
		this.progressBar = progressbar;
	}
	
	public void run() {
		min = intfeld[0];	
		for (int i = 0; i < intfeld.length; i++) {		// Durchsucht das ganze Array mit Werten die kleiner als min sind und setzt diese als min
			if (intfeld[i] < min)	{
				min = intfeld[i];
			}
			if (i % (intfeld.length / 100) == 0)	 {	// Damit progressBar jedes 10000 mal weiter geht und das Programm schneller läuft
				final int progress = (int)((i / (double)intfeld.length) * 100);	// Gibt Zustand des Threads an
				EventQueue.invokeLater(new Runnable() {
					public void run()	{
						progressBar.setValue(progress); // Setzt den Zustand im progressBar ein
					}
				});
			}
		}
		
		EventQueue.invokeLater(new Runnable()	{
			public void run()	{
				textField.setText(String.valueOf(min));	//Setzt nach der Berechnung des Threads min im TextField ein
			}
		});
	}
}
