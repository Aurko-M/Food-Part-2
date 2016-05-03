
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EditRecipeGUI extends GUIManager {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldEditIngredients;
	private JTextField textFieldQuantity;
	private JComboBox recipeList;

	/**
	 * Create the frame.
	 */
	public EditRecipeGUI(Mediator m) {

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
		lblName.setBounds(70, 112, 126, 14);
		panel.add(lblName);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(70, 166, 86, 14);
		panel.add(lblQuantity);

		JLabel lblSelect = new JLabel("Select Recipe");
		lblSelect.setBounds(70, 56, 126, 14);
		panel.add(lblSelect);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(179, 109, 126, 20);
		panel.add(textFieldName);

		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(179, 163, 126, 20);
		panel.add(textFieldQuantity);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(37, 227, 89, 23);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);

		JButton btnAdd = new JButton("Confirm");
		btnAdd.setBounds(205, 227, 89, 23);
		panel.add(btnAdd);
		btnAdd.addActionListener(this);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(120, 227, 89, 23);
		panel.add(btnDelete);
		btnDelete.addActionListener(this);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		panel.add(btnBack);
		btnBack.addActionListener(this);

		JLabel lblEditRecipe = new JLabel("Edit Recipe");
		lblEditRecipe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditRecipe.setBounds(118, 9, 120, 23);
		panel.add(lblEditRecipe);

		setUpData();
		panel.add(recipeList);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Confirm") {
			// Send the text from the textfields to be sanitized.
			confirmButtonPressed();
		} else if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		} else if (ae.getActionCommand() == "Delete") {
			deleteButtonPressed();
		} else {
			if(recipeList.getItemCount() > 0){
				char id = recipeList.getSelectedItem().toString().charAt(0);
				Consumable cons = mediator.getConsumable(id);
				textFieldName.setText(cons.getName());
				textFieldQuantity.setText(cons.getQuantity() + "");
			}
		}
	}

	private void deleteButtonPressed() {
		mediator.deleteConsumable(recipeList.getSelectedItem().toString()
				.charAt(0));
		textFieldName.setText("");
		textFieldQuantity.setText("");
		recipeList.removeAllItems();
		recipeList.revalidate();
		recipeList.repaint();
		setUpData();
	}

	private void confirmButtonPressed() {
		// Validate the textFields
		String[] fields = new String[] { textFieldName.getText(),
				textFieldQuantity.getText() };
		if (isValidated(fields)) {
			// Create a food.
			mediator.editRecipeProperties(recipeList.getSelectedItem()
					.toString().charAt(0), textFieldName.getText(),
					Integer.parseInt(textFieldQuantity.getText()));

			JOptionPane.showMessageDialog(contentPane, "Eddited Recipe: "
					+ textFieldName.getText());

			// Clear all the textFields
			textFieldName.setText("");
			textFieldQuantity.setText("");
		}
	}

	private void setUpData() {
		ArrayList<Consumable> recipes = mediator.getConsumables();
		ArrayList<String> createdRecipes = new ArrayList<String>();
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).isARecipe()) {
				createdRecipes.add(String.valueOf(recipes.get(i).getId()));
			}
		}

		String[] consumableStrings = new String[createdRecipes.size()];
		for (int i = 0; i < createdRecipes.size(); i++) {
			consumableStrings[i] = createdRecipes.get(i);
		}

		recipeList = new JComboBox<String>(consumableStrings);
		recipeList.setBounds(180, 50, 86, 20);
		recipeList.addActionListener(this);
	}

}
