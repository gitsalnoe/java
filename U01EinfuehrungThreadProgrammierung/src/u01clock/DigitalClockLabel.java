package u01clock;

import java.util.Date;

import javax.swing.JLabel;

public class DigitalClockLabel extends JLabel implements Runnable{

	private JLabel outputLabel = null;
	private boolean stopped = false;
	
	public DigitalClockLabel(JLabel outputLabel) {
		this.outputLabel = outputLabel;
	}
	
	public boolean getStopped()	{
		return stopped;
	}
	
	public void setStopped(boolean stopped)	{
		this.stopped = stopped;
		
	}
	
	@Override
	public void run() {
		while (true)	{
			if (!this.stopped)	{	
				outputLabel.setText(new Date().toString().substring(11,19));	//Setzt das Uhr Objekt auf die Uhrzeit
				try {
					Thread.sleep(1000);	
				}	catch (InterruptedException e) {
					return;
				}
			}
			else	{
				try {
					Thread.sleep(1000);			// Damit die while-schliefe Zeit hat im eine Änderung bei this.stopped zu erkennen
				}	catch (InterruptedException e)	{
					return;
				}
			}
		}
	}
	
}