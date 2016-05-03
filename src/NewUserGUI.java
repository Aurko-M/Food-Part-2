

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class NewUserGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAge;
	private JTextField textFieldWeight;
	private JTextField textFieldCalories;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUserGUI frame = new NewUserGUI();
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
	public NewUserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(78, 68, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(78, 104, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(78, 146, 46, 14);
		contentPane.add(lblWeight);
		
		JLabel lblMaxCalories = new JLabel("Max Calories");
		lblMaxCalories.setBounds(78, 186, 66, 14);
		contentPane.add(lblMaxCalories);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(183, 65, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(183, 101, 86, 20);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(183, 143, 86, 20);
		contentPane.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		textFieldCalories = new JTextField();
		textFieldCalories.setBounds(183, 183, 86, 20);
		contentPane.add(textFieldCalories);
		textFieldCalories.setColumns(10);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(143, 227, 89, 23);
		contentPane.add(btnSignUp);
		
		JLabel lblNewUser = new JLabel("New User");
		lblNewUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewUser.setBounds(136, 23, 96, 31);
		contentPane.add(lblNewUser);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
	}
}
