package u01threads;

import java.awt.EventQueue;

import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class FindMax extends FindMin{

	protected static int max;
	
	public FindMax(String name, JTextField textfield, JProgressBar progressbar) {
		super(name, textfield, progressbar);
	}
	
	public void run()	{
		max = intfeld[0];
		for (int i = 0; i < intfeld.length; i++)	{	// Das gleiche wie bei FindMin aber statt das min zu setzen wird das grösste als max gesetzt
			if (intfeld[i] > max)	{
				max = intfeld[i];				
			}
			if (i % (intfeld.length / 100) == 0)	 {	// Das gleiche wie bei FindMin
				final int progress = (int)((i / (double)intfeld.length) * 100);	
				EventQueue.invokeLater(new Runnable() {
					public void run()	{
						progressBar.setValue(progress);
					}
				});
			}
		}
		EventQueue.invokeLater(new Runnable()	{
			public void run()	{
				textField.setText(String.valueOf(max));		//Setzt max in das textField hinein
			}
		});
	}
	
}
