

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DeleteFoodGUI extends GUIManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
   private JComboBox foodList;

	/**
	 * Create the frame.
	 */
	public DeleteFoodGUI(Mediator m) {
   
      mediator = m;
   
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Id");
		lblName.setBounds(81, 85, 46, 14);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(178, 82, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(28, 227, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(217, 227, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);

		JLabel lblDeleteFood = new JLabel("Delete Food");
		lblDeleteFood.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDeleteFood.setBounds(118, 6, 119, 29);
		contentPane.add(lblDeleteFood);
      
      setUpData();
      contentPane.add(foodList);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Delete") {
			// Perform steps to delete the food
         deleteButtonPressed();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}
	}
   
   public void deleteButtonPressed(){
      mediator.deleteConsumable(textFieldName.getText().charAt(0));
      textFieldName.setText("");
   }
   
   private void setUpData(){
		ArrayList<Consumable> recipes = mediator.getConsumables();
		
      ArrayList<String> createdFoods = new ArrayList<String>();
		for(int i = 0; i < recipes.size(); i++){
			if(recipes.get(i).isAFood()){
            createdFoods.add(String.valueOf(recipes.get(i).getId()));
			}
		}
      
      String[] consumableStrings = new String[createdFoods.size()];
      for(int i = 0; i < createdFoods.size(); i++){
         consumableStrings[i] = createdFoods.get(i);
      }	
      	
      foodList = new JComboBox<String>(consumableStrings);
		foodList.setBounds(176, 60, 86, 20);
		foodList.addActionListener(this);
	}

}
