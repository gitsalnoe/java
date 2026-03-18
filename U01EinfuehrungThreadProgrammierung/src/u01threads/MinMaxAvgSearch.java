package u01threads;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MinMaxAvgSearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_0;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinMaxAvgSearch frame = new MinMaxAvgSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MinMaxAvgSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField_0 = new JTextField();
		textField_0.setBounds(43, 71, 104, 20);
		contentPane.add(textField_0);
		textField_0.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(43, 103, 104, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(43, 131, 104, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Min:");
		lblNewLabel.setBounds(12, 75, 28, 16);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Max:");
		lblNewLabel_1.setBounds(12, 105, 28, 16);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Avg:");
		lblNewLabel_2.setBounds(12, 133, 28, 16);
		contentPane.add(lblNewLabel_2);
		
		JProgressBar progressBar_0 = new JProgressBar();
		progressBar_0.setBounds(159, 71, 233, 20);
		contentPane.add(progressBar_0);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(159, 103, 233, 20);
		contentPane.add(progressBar_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setBounds(159, 131, 233, 20);
		contentPane.add(progressBar_2);

		JButton btnNewButton = new JButton("Start searching");
		btnNewButton.setBounds(143, 12, 120, 26);
	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindMin t1 = new FindMin("FindMin", textField_0, progressBar_0);
				t1.start();
				FindMax t2 = new FindMax("FindMax", textField_1, progressBar_1);
				t2.start();
				FindAvg t3 = new FindAvg("FindAvg", textField_2, progressBar_2);
				t3.start();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);	
	}
}
