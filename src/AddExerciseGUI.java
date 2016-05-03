import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

public class AddExerciseGUI extends GUIManager{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldId;
	private JTextField textFieldCalories;
	private JLabel lblFeedBack;

	/**
	 * Create the frame.
	 */
	public AddExerciseGUI(Mediator m) {
		
		mediator = m;
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddAnExercise = new JLabel("Add an Exercise");
		lblAddAnExercise.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddAnExercise.setBounds(101, 11, 137, 14);
		contentPane.add(lblAddAnExercise);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 9, 55, 23);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(83, 69, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("Description");
		lblId.setBounds(83, 121, 75, 14);
		contentPane.add(lblId);
		
		JLabel lblCalories = new JLabel("Calories");
		lblCalories.setBounds(83, 168, 75, 14);
		contentPane.add(lblCalories);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(171, 66, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(171, 118, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldCalories = new JTextField();
		textFieldCalories.setBounds(171, 165, 86, 20);
		contentPane.add(textFieldCalories);
		textFieldCalories.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(47, 227, 89, 23);
		btnCancel.addActionListener(this);
		contentPane.add(btnCancel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(197, 227, 89, 23);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		lblFeedBack = new JLabel("");
		lblFeedBack.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFeedBack.setBounds(70, 35, 200, 14);
		contentPane.add(lblFeedBack);
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Add") {
			// Perform steps to add a exercise
			addButtonPress();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}
	}
	
	/**
	 * Validate the text fields and add the exercise to the mediator
	 */
	private  void addButtonPress(){
		//Validate the textFields
		String[] fields = new String[]{textFieldName.getText(), textFieldCalories.getText()};
		if(isValidated(fields))
		{
			//Create a exercise.
			if(mediator.addExercise(textFieldName.getText(), textFieldId.getText(), Integer.parseInt(textFieldCalories.getText())))
			{
				JOptionPane.showMessageDialog(contentPane, "Added Exercise: " + textFieldName.getText());
			}
			//lblFeedBack.setText("Added " + textFieldName.getText());
			//lblFeedBack.setForeground(Color.green);
			
			//Clear all the textFields
			textFieldName.setText("");
			textFieldCalories.setText("");
			textFieldId.setText("");
		}else{
			lblFeedBack.setText("Invalid Field");
			lblFeedBack.setForeground(Color.red);
		}
	}

}
