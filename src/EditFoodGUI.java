

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
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EditFoodGUI extends GUIManager {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldCarbohydrates;
	private JTextField textFieldFat;
	private JTextField textFieldCalories;
	private JTextField textFieldProtein;
	private JComboBox foodList;
	private JLabel lblFeedBack;

	/**
	 * Create the frame.
	 */
	public EditFoodGUI(Mediator m) {

		mediator = m;

		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 341, 261);
		contentPane.add(panel);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(70, 90, 46, 14);
		panel.add(lblName);

		JLabel lblSelectFood = new JLabel("Select Food");
		lblSelectFood.setBounds(70, 66, 86, 14);
		panel.add(lblSelectFood);

		JLabel lblCarbohydrates = new JLabel("Carbohydrates");
		lblCarbohydrates.setBounds(70, 120, 92, 14);
		panel.add(lblCarbohydrates);

		JLabel lblFat = new JLabel("Fat");
		lblFat.setBounds(70, 148, 46, 14);
		panel.add(lblFat);

		JLabel lblCalories = new JLabel("Calories");
		lblCalories.setBounds(70, 173, 86, 14);
		panel.add(lblCalories);

		JLabel lblProtein = new JLabel("Protein");
		lblProtein.setBounds(70, 200, 46, 14);
		panel.add(lblProtein);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(176, 91, 86, 20);
		panel.add(textFieldName);

		textFieldCarbohydrates = new JTextField();
		textFieldCarbohydrates.setColumns(10);
		textFieldCarbohydrates.setBounds(176, 117, 86, 20);
		panel.add(textFieldCarbohydrates);

		textFieldFat = new JTextField();
		textFieldFat.setColumns(10);
		textFieldFat.setBounds(176, 145, 86, 20);
		panel.add(textFieldFat);

		textFieldCalories = new JTextField();
		textFieldCalories.setColumns(10);
		textFieldCalories.setBounds(176, 170, 86, 20);
		panel.add(textFieldCalories);

		textFieldProtein = new JTextField();
		textFieldProtein.setColumns(10);
		textFieldProtein.setBounds(176, 197, 86, 20);
		panel.add(textFieldProtein);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(21, 228, 89, 23);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);

		JButton btnEdit = new JButton("Confirm");
		btnEdit.setBounds(221, 228, 89, 23);
		panel.add(btnEdit);
		btnEdit.addActionListener(this);

		JLabel lblEditFood = new JLabel("Edit Food");
		lblEditFood.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditFood.setBounds(134, 11, 86, 20);
		panel.add(lblEditFood);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(124, 228, 89, 23);
		panel.add(btnDelete);
		btnDelete.addActionListener(this);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		panel.add(btnBack);
		btnBack.addActionListener(this);

		lblFeedBack = new JLabel("");
		lblFeedBack.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFeedBack.setBounds(70, 35, 200, 14);
		contentPane.add(lblFeedBack);

		setUpData();
		panel.add(foodList);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Confirm") {
			// Perform steps to edit the food
			confirmButtonPressed();
		} else if (ae.getActionCommand() == "Delete") {
			deleteButtonPressed();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		} else {
			if(foodList.getItemCount() > 0){
				char id = foodList.getSelectedItem().toString().charAt(0);
				Consumable cons = mediator.getConsumable(id);
				textFieldName.setText(cons.getName());
				textFieldCarbohydrates.setText(cons.getCarbohydrates() + "");
				textFieldFat.setText(cons.getFat() + "");
				textFieldCalories.setText(cons.getCalories() + "");
				textFieldProtein.setText(cons.getProtein() + "");
			}
		}
	}

	private void deleteButtonPressed() {
		mediator.deleteConsumable(foodList.getSelectedItem().toString()
				.charAt(0));
		clearFields();
		//foodList.removeAllItems();
		foodList.revalidate();
		foodList.repaint();
		setUpData();
	}

	private void confirmButtonPressed() {
		// Validate the textFields
		String[] fields = new String[] { textFieldName.getText(),
				textFieldCalories.getText(), textFieldCarbohydrates.getText(),
				textFieldFat.getText(), textFieldProtein.getText() };
		if (isValidated(fields)) {
			// Create a food.
			mediator.editFood(foodList.getSelectedItem().toString().charAt(0),
					textFieldName.getText(),
					Double.parseDouble(textFieldCalories.getText()),
					Double.parseDouble(textFieldCarbohydrates.getText()),
					Double.parseDouble(textFieldFat.getText()),
					Double.parseDouble(textFieldProtein.getText()));

			JOptionPane.showMessageDialog(contentPane, "Added Food: "
					+ textFieldName.getText());

			clearFields();
		} else {
			lblFeedBack.setText("Invalid Field");
			lblFeedBack.setForeground(Color.red);
		}
	}

	private void clearFields() {
		// Clear all the textFields
		textFieldName.setText("");
		textFieldCalories.setText("");
		textFieldCarbohydrates.setText("");
		textFieldFat.setText("");
		textFieldProtein.setText("");
	}

	private void setUpData() {
		ArrayList<Consumable> recipes = mediator.getConsumables();

		ArrayList<String> createdFoods = new ArrayList<String>();
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).isAFood()) {
				createdFoods.add(String.valueOf(recipes.get(i).getId()));
			}
		}

		String[] consumableStrings = new String[createdFoods.size()];
		for (int i = 0; i < createdFoods.size(); i++) {
			consumableStrings[i] = createdFoods.get(i);
		}

		foodList = new JComboBox<String>(consumableStrings);
		foodList.setBounds(176, 60, 86, 20);
		foodList.addActionListener(this);
	}

}