

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditLogGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textFieldRecipe;
	private JTextField textFieldCalories;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditLogGUI frame = new EditLogGUI();
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
	public EditLogGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRecipe = new JLabel("Recipe");
		lblRecipe.setBounds(95, 83, 46, 14);
		contentPane.add(lblRecipe);
		
		JLabel lblTargetCalories = new JLabel("Target Calories");
		lblTargetCalories.setBounds(82, 146, 85, 14);
		contentPane.add(lblTargetCalories);
		
		textFieldRecipe = new JTextField();
		textFieldRecipe.setBounds(185, 80, 86, 20);
		contentPane.add(textFieldRecipe);
		textFieldRecipe.setColumns(10);
		
		textFieldCalories = new JTextField();
		textFieldCalories.setBounds(185, 143, 86, 20);
		contentPane.add(textFieldCalories);
		textFieldCalories.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(43, 227, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(212, 227, 89, 23);
		contentPane.add(btnConfirm);
		btnConfirm.addActionListener(this);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
		
		JLabel lblEditLog = new JLabel("Edit Log");
		lblEditLog.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditLog.setBounds(133, 11, 96, 19);
		contentPane.add(lblEditLog);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Confirm") {
			// Send the text from the textfields to be sanitized.
		} else if (e.getActionCommand() == "Delete") {
			dispose();
		} else if (e.getActionCommand() == "Back") {
			dispose();
		}
	}

}
