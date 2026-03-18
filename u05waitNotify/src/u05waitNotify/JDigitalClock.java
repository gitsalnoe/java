package u05waitNotify;

import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class JDigitalClock extends JLabel implements Runnable	{
	
	JLabel outputLabel;
	boolean stopped = false;
	
	public JDigitalClock(JLabel label)	{
		this.outputLabel = label;
	}
	
	public synchronized boolean getStopped()	{
		return stopped;
	}
	
	public synchronized void setStopped(boolean stopped)	{
		this.stopped = stopped;
		if (!stopped)	{
			notify();
		}
	}
	
	@Override
	public void run() {
		while (true)	{
			synchronized (this)	{
				if (stopped)	{
					try {
						wait();
						System.out.println("not waiting");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			outputLabel.setText(new Date().toString().substring(11,19));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}
	
	public static void main(String[] args) {
		
	}
	
}
