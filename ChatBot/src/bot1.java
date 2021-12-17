import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;


public class bot1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	
	JButton sendButton;
	JScrollPane scrollPane;
	JButton backButton; 
	JTextArea textArea;
	static bot1 frame;
	boolean flag=false;
	
	

	// Main Method
	public static void main(String[] args) {
		EventQueue.invokeLater(	new Runnable() {
									public void run() {
										try{
											frame = new bot1();
											frame.setVisible(true);
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}						
							);
	}


	//Contructor for bot1
	public bot1() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 620, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Textarea to display the Questions and Answers
		textArea = new JTextArea();
		textArea.setBackground(new Color(255, 250, 240));
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial", Font.BOLD, 16));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(1, 1, 425, 305);
		contentPane.add(textArea);
		
		// Textfield for User to enter query
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setBounds(25, 354, 444, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Button to send query
		sendButton = new JButton("Send");
		sendButton.setBounds(479, 356, 95, 28);
		sendButton.setActionCommand("Ask");
		contentPane.add(sendButton);
		
		// Button to come back to login
		backButton = new JButton("Back");
		backButton.setBounds(479, 25, 95, 28);
		contentPane.add(backButton);
		contentPane.setLayout(null);
		
		// Scroll pane for Autoscrolling
		scrollPane = new JScrollPane(
					textArea,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
				);
		scrollPane.setBounds(25, 22, 444, 307);
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		
		sendButton.addActionListener(this);
		backButton.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// back button
		if(e.getSource()==backButton) {
			this.frame.dispose();
			new login().main(null);
		}
		
		
		// send button
		if(e.getSource()==sendButton) {
			String message = textField.getText();
			String ans=" ";
			textField.setText("");
			textArea.append("\n User: "+message+"\n");
			// block for checking eligibility
			if(message.toLowerCase().equals("am i eligible for moratorium loan"))
			{
				flag=true;
				textArea.append("\n Bot: Please enter your Account number \n");
				ans="Please enter your Account number";
				try {
					new dataBase().Record(message,ans);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return ;
			}
			if(flag)
			{
				flag=false;
				boolean temp=false;
				try {
					temp=new dataBase().isEligible(message);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				if(temp)
				{
					textArea.append("\n Bot: Yes! You are eligible \n");
					ans="Yes! You are eligible";
				}
				else {
					textArea.append("\n Bot: No! You are not eligible \n");
					ans="No! You are not eligible";
				}
				try {
					new dataBase().Record(message,ans);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				return;
			}
			
			// block to print the reply
			try {
				ans= new dataBase().getResponse(message);
				new dataBase().Record(message,ans);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			textArea.append("\n Bot: "+ans+"\n\n");	
		}
	}
	
	
	
	
}
// End of Bot1