package u05waitNotify;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DigitalClockFrame extends JFrame {
	
	private JDigitalClock clock = null;
	  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DigitalClockFrame frame = new DigitalClockFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public DigitalClockFrame() {
		setTitle("DigitalClockFrame");
		setBounds(100, 100, 450, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel clockLabel = new JLabel("clock");
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setBounds(41, 31, 353, 70);
		clockLabel.setFont(new Font("Serif", Font.PLAIN, 80));
		getContentPane().add(clockLabel);
		
		JButton stopButton = new JButton("Stop");
		stopButton.setBounds(41, 116, 98, 25);
		getContentPane().add(stopButton);
		stopButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e)	{
				clock.setStopped(true);
				System.out.println("stop");
			}
		});
		
		JButton continueButton = new JButton("Continue");
		continueButton.setBounds(296, 115, 98, 25);
		getContentPane().add(continueButton);
		continueButton.addActionListener(new ActionListener()	{
			public void actionPerformed(ActionEvent e)	{
				clock.setStopped(false);
			}
		});
	
		clock = new JDigitalClock(clockLabel);	// DigitalClockLabel Objekt welche die Uhrzeit im clockLabel ausgibt
		Thread t = new Thread(clock);	//Thread von clock
		t.start();
		
	}
}
