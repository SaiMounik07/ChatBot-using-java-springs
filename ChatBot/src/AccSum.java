import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class AccSum extends JFrame implements ActionListener {

	private JPanel contentPane;
	JLabel logoLabel;
	static JTextArea textArea;
	JButton botButton;
	static AccSum frame;
	userBean obj;
	
	
	// Main Method 
	public static void main(String[] args,userBean b) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AccSum();
					frame.setVisible(true);
					Display(b);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Constructor
	public AccSum() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 620, 440);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Label for Logo
		logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon("D:\\COLLEGE\\T1 Training\\T1 Hack 2021\\acclogo.png"));
		logoLabel.setBounds(20, 24, 60, 51);
		contentPane.add(logoLabel);
		
		// Text area to display account details
		textArea =new JTextArea("") ;
		textArea.setFont(new Font("Arial Black", Font.PLAIN, 16));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(146, 41, 327, 328);
		contentPane.add(textArea);
		textArea.setEditable(false);
		
		// Button for Chatbot
		botButton = new JButton("");
		botButton.setIcon(new ImageIcon("D:\\COLLEGE\\T1 Training\\T1 Hack 2021\\bot.png"));
		botButton.setBounds(535, 343, 50, 50);
		contentPane.add(botButton); 
		
		botButton.addActionListener(this);
		
	}
	
	@Override
	// Method to Open bot frame and close current frame
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botButton) {
			this.frame.dispose();
			new bot1().main(null);
		}
	}
	
	// Method to display details of signed in user
	public static void Display(userBean o) {
		textArea.append("\nWelcome "+o.getName()+" !!\n\nPhone Number: "+o.getPhNum()+"\n\nAccount Number: "+o.getAccNum()+"\n\nBalance: Rs."+o.getAccBal());
	}
}
