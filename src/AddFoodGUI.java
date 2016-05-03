

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFoodGUI extends GUIManager{

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldCarbs;
	private JTextField textFieldFat;
	private JTextField textFieldCalories;
	private JTextField textFieldProtein;
	private JTextField textFieldId;
	private JLabel lblFeedBack;

	/**
	 * Create the frame.
	 */
	public AddFoodGUI(Mediator m) {
		
		mediator = m;
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(70, 74, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(70, 50, 46, 14);
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(176, 47, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);

		JLabel lblCarbohydrates = new JLabel("Carbohydrates");
		lblCarbohydrates.setBounds(70, 100, 92, 14);
		contentPane.add(lblCarbohydrates);

		JLabel lblFat = new JLabel("Fat");
		lblFat.setBounds(70, 128, 46, 14);
		contentPane.add(lblFat);

		JLabel lblCalories = new JLabel("Calories");
		lblCalories.setBounds(70, 163, 86, 14);
		contentPane.add(lblCalories);

		JLabel lblProtein = new JLabel("Protein");
		lblProtein.setBounds(70, 200, 46, 14);
		contentPane.add(lblProtein);

		textFieldName = new JTextField();
		textFieldName.setBounds(176, 71, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldCarbs = new JTextField();
		textFieldCarbs.setBounds(176, 97, 86, 20);
		contentPane.add(textFieldCarbs);
		textFieldCarbs.setColumns(10);

		textFieldFat = new JTextField();
		textFieldFat.setBounds(176, 125, 86, 20);
		contentPane.add(textFieldFat);
		textFieldFat.setColumns(10);

		textFieldCalories = new JTextField();
		textFieldCalories.setBounds(176, 160, 86, 20);
		contentPane.add(textFieldCalories);
		textFieldCalories.setColumns(10);

		textFieldProtein = new JTextField();
		textFieldProtein.setBounds(176, 197, 86, 20);
		contentPane.add(textFieldProtein);
		textFieldProtein.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(21, 228, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);

		JButton btnEdit = new JButton("Add");
		btnEdit.setBounds(221, 228, 89, 23);
		contentPane.add(btnEdit);
		btnEdit.addActionListener(this);

		JLabel lblAddFood = new JLabel("Add Food");
		lblAddFood.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddFood.setBounds(134, 11, 86, 29);
		contentPane.add(lblAddFood);
		
		lblFeedBack = new JLabel("");
		lblFeedBack.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFeedBack.setBounds(70, 35, 200, 14);
		contentPane.add(lblFeedBack);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Add") {
			// Perform steps to add a food
			addButtonPress();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}
	}
	
	/**
	 * Validate the text fields and add the food to the mediator
	 */
	private  void addButtonPress(){
		//Validate the textFields
		String[] fields = new String[]{textFieldName.getText(), textFieldCalories.getText(), textFieldCarbs.getText(), 
				textFieldFat.getText(), textFieldProtein.getText(), textFieldId.getText()};
		if(isValidated(fields))
		{
			//Create a food.
			mediator.createFood(textFieldId.getText().charAt(0),textFieldName.getText(), Double.parseDouble(textFieldCalories.getText()), Double.parseDouble(textFieldCarbs.getText()),
					Double.parseDouble(textFieldFat.getText()), Double.parseDouble(textFieldProtein.getText()));
			
			JOptionPane.showMessageDialog(contentPane, "Added Food: " + textFieldName.getText());
			//lblFeedBack.setText("Added " + textFieldName.getText());
			//lblFeedBack.setForeground(Color.green);
			
			//Clear all the textFields
			textFieldName.setText("");
			textFieldCalories.setText("");
			textFieldCarbs.setText("");
			textFieldFat.setText("");
			textFieldProtein.setText("");
			textFieldId.setText("");
		}else{
			lblFeedBack.setText("Invalid Field");
			lblFeedBack.setForeground(Color.red);
		}
	}
	
	

}
