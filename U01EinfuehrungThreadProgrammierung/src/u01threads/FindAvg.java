package u01threads;

import java.awt.EventQueue;

import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class FindAvg extends FindMax{
	
	private double avg;

	public FindAvg(String name, JTextField textfield, JProgressBar progressbar) {
		super(name, textfield, progressbar);
	}
	
	public void run()	{
		avg = ((min + max) / (double)2) ;	// Berechnet den Durchschnittswert von min und max
		EventQueue.invokeLater(new Runnable()	{
			public void run()	{
				textField.setText(String.valueOf((int)avg));	// Setzt den Wert als int im textField ein
			}
		});
		progressBar.setValue(100); // Damit die progressBar gleich auf 100% geht
	}
}
