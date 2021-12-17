import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;


public class login extends JFrame implements ItemListener,ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	JLabel userLabel;
	JLabel passLabel;
	JButton loginButton;
	JCheckBox showPassword;
	JLabel logoLabel;
	JButton botButton;
	static login frame;
	
	// Main Method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor for login
	public login() {

		setResizable(false);
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 620, 440);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Label for Username
		userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		userLabel.setBounds(166, 199, 103, 27);
		contentPane.add(userLabel);
		
		//label for Password
		passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		passLabel.setBounds(166, 252, 91, 28);
		contentPane.add(passLabel);
		
		//Textfield for Entering Username
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField.setBounds(279, 199, 169, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Login Button
		loginButton = new JButton("Login");
		loginButton.setToolTipText("");
		loginButton.setBackground(new Color(255, 255, 255));
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		loginButton.setBounds(229, 339, 96, 32);
		contentPane.add(loginButton);
		
		//Textfield for Entering Password
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Times New Roman", Font.BOLD, 16));
		passwordField.setBounds(279, 253, 169, 32);
		contentPane.add(passwordField);
		
		//Checkbox to show Password
		showPassword = new JCheckBox("Show Password");
		showPassword.setBackground(SystemColor.info);
		showPassword.setBounds(279, 298, 117, 21);
		contentPane.add(showPassword);
		
		//Label for Bank Logo
		logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon("D:\\COLLEGE\\T1 Training\\T1 Hack 2021\\piggy.png"));
		logoLabel.setBackground(new Color(0, 0, 0));
		logoLabel.setBounds(112, 32, 400, 128);
		contentPane.add(logoLabel);
		
		//Button to open Chatbot
		botButton = new JButton("");
		botButton.setIcon(new ImageIcon("D:\\COLLEGE\\T1 Training\\T1 Hack 2021\\bot.png"));
		botButton.setBounds(546, 343, 50, 50);
		contentPane.add(botButton);
		
		showPassword.addItemListener(this);
		botButton.addActionListener(this);
		loginButton.addActionListener(this);
	}
	
	// Method to Show Password
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==showPassword) {
			if(showPassword.isSelected()) {
				passwordField.setEchoChar((char)0);
				
			}
			else {
				passwordField.setEchoChar('\u26ab');
			}
		}
	}
	
	// Method to close Login frame and open Bot frame
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==botButton) {
			this.frame.dispose();
			new bot1().main(null);
			
		}
		if(e.getSource()==loginButton) {
			userBean obj=null;
			try {
				obj = new dataBase().checkLogin(textField.getText(),passwordField.getText());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			JFrame f;
			if(obj==null) {
				 f= new JFrame();  
				    JOptionPane.showMessageDialog(f,"Username doesn't exist.","Alert",JOptionPane.WARNING_MESSAGE);  
			}
			else if(!obj.getPassword().equals(passwordField.getText()))
			{
				 f= new JFrame();  
				    JOptionPane.showMessageDialog(f,"Incorrect Password","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else  {
				this.frame.dispose();
				AccSum sum = new AccSum();
				sum.main(null,obj);
				//new AccSum(obj);
			}
			
		}
	}
}
// End of Login
