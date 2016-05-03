

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ClientManagerGUI extends GUIManager implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldCalories;
	private JTextField textFieldWeight;
	private JTextField textFieldAge;
	private JLabel lblFeedBack;
	private Client client;

	/**
	 * Create the frame.
	 */
	public ClientManagerGUI(Mediator m) {
		
		mediator = m;
		client = mediator.getClient();
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(70, 54, 46, 14);
		contentPane.add(lblName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(70, 87, 92, 14);
		contentPane.add(lblAge);

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(70, 128, 46, 14);
		contentPane.add(lblWeight);

		JLabel lblCalories = new JLabel("Max Calories");
		lblCalories.setBounds(70, 163, 86, 14);
		contentPane.add(lblCalories);

		textFieldName = new JTextField();
		textFieldName.setBounds(176, 51, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldAge = new JTextField();
		textFieldAge.setBounds(176, 87, 86, 20);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);

		textFieldWeight = new JTextField();
		textFieldWeight.setBounds(176, 125, 86, 20);
		contentPane.add(textFieldWeight);
		textFieldWeight.setColumns(10);

		textFieldCalories = new JTextField();
		textFieldCalories.setBounds(176, 160, 86, 20);
		contentPane.add(textFieldCalories);
		textFieldCalories.setColumns(10);

		JLabel lblClientManager = new JLabel("Client Manager");
		lblClientManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClientManager.setBounds(109, 21, 162, 23);
		contentPane.add(lblClientManager);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(21, 228, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);

		JButton btnEdit = new JButton("Confirm");
		btnEdit.setBounds(221, 228, 89, 23);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(this);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
		setUpData();
	}
	

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Confirm") {
			confirmButtonPress();
			
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}
	}
	private  void confirmButtonPress(){
		String[] fields = new String[]{textFieldName.getText(), textFieldAge.getText(), textFieldWeight.getText(), 
				textFieldCalories.getText()};
		if(isValidated(fields))
		{
			
			mediator.changeClientInfo(textFieldName.getText(), Integer.parseInt(textFieldAge.getText()), 
					Double.parseDouble(textFieldWeight.getText()), Double.parseDouble(textFieldCalories.getText()));
			JOptionPane.showMessageDialog(contentPane, "Updated Client: " + textFieldName.getText());
			//lblFeedBack.setText("Added " + textFieldName.getText());
			//lblFeedBack.setForeground(Color.green);
			
		}else{
			lblFeedBack.setText("Invalid Field");
			lblFeedBack.setForeground(Color.red);
		}
	}
	private void setUpData(){
		textFieldName.setText(client.getName().toString());
		textFieldAge.setText(client.getAge()+"");
		textFieldWeight.setText(client.getWeight()+"");
		textFieldCalories.setText(client.getMaxCalories()+"");
	}

}
