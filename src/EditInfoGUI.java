

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

public class EditInfoGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldCalories;
	private JTextField textFieldWeight;
	private JTextField textFieldAge;
	private JTextField textFieldName;
	/**
	 * Create the frame.
	 */
	public EditInfoGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(72, 59, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(72, 95, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(72, 137, 46, 14);
		contentPane.add(lblWeight);
		
		JLabel lblCalories = new JLabel("Max Calories");
		lblCalories.setBounds(72, 177, 66, 14);
		contentPane.add(lblCalories);
		
		textFieldCalories = new JTextField();
		textFieldCalories.setColumns(10);
		textFieldCalories.setBounds(177, 174, 86, 20);
		contentPane.add(textFieldCalories);
		
		textFieldWeight = new JTextField();
		textFieldWeight.setColumns(10);
		textFieldWeight.setBounds(177, 134, 86, 20);
		contentPane.add(textFieldWeight);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(177, 92, 86, 20);
		contentPane.add(textFieldAge);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(177, 56, 86, 20);
		contentPane.add(textFieldName);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(49, 227, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(206, 227, 89, 23);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(this);

		JLabel lblEditInformation = new JLabel("Edit Information");
		lblEditInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditInformation.setBounds(103, 11, 143, 34);
		contentPane.add(lblEditInformation);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(4, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Edit") {
			// Send the text from the textfields to be sanitized.
		} else if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}
	}

}
