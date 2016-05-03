

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddRecipeGUI extends GUIManager {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldId;
	private JTextField textFieldIngredients;
	private JTextField textFieldQuantity;
	private ArrayList<Consumable> recipeFood = new ArrayList<Consumable>();
	private JComboBox recipeList;
	private JLabel lblFeedBack;
	private JTable table;
	private TableModel myTableModel;
	private Object[][] tableData = new Object[20][20];
	
	/**
	 * Create the frame.
	 */
	public AddRecipeGUI(Mediator m) {

		mediator = m;

		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(70, 52, 46, 14);
		contentPane.add(lblName);

		JLabel lblAddIngredients = new JLabel("Add Ingredients");
		lblAddIngredients.setBounds(70, 83, 106, 14);
		contentPane.add(lblAddIngredients);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(70, 116, 66, 14);
		contentPane.add(lblQuantity);

		textFieldName = new JTextField();
		textFieldName.setBounds(179, 49, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblId = new JLabel("C Id");
		lblId.setBounds(270, 52, 46, 14);
		contentPane.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(300, 49, 20, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(1);

		textFieldIngredients = new JTextField();
		textFieldIngredients.setBounds(179, 80, 86, 20);
		//contentPane.add(textFieldIngredients);
		textFieldIngredients.setColumns(10);

		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(179, 113, 86, 20);
		contentPane.add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
	

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(37, 247, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(205, 247, 89, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);

		JLabel lblAddRecipe = new JLabel("Add Recipe");
		lblAddRecipe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddRecipe.setBounds(118, 9, 120, 23);
		contentPane.add(lblAddRecipe);
		
		lblFeedBack = new JLabel("");
		lblFeedBack.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFeedBack.setBounds(70, 35, 200, 14);
		contentPane.add(lblFeedBack);
		
		//Create the table to populate
		String[] columnNames = {
				"Food","Quantity"
		};
		
		//myTableModel = new DefaultTableModel(tableData, columnNames);
		table = new JTable(tableData, columnNames);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 140, 300, 100);
		contentPane.add(scrollPane);
		
		setUpData();
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Add") {
			// Send the text from the textfields to be sanitized.
			addButtonPressed();
		} else if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}else{
			//update the table object.
			char id = recipeList.getSelectedItem().toString().charAt(0);
			Consumable cons = mediator.getConsumable(id);
			tableData[recipeFood.size()][0] = cons.getName();
			tableData[recipeFood.size()][1] = cons.getQuantity();
			recipeFood.add(cons);
			table.repaint();
		}
	}

	private void addButtonPressed() {
		// Validate the textFields
		String[] fields = new String[] { textFieldName.getText(),
				textFieldQuantity.getText() };
		if (isValidated(fields)) {
			// Create a recipe.
			String recipeList = "";
			for(int i = 0; i < recipeFood.size(); i++){
				recipeList += recipeFood.get(i).getId();
				recipeList += recipeFood.get(i).getQuantity();
			}
			mediator.createRecipe(textFieldId.getText().charAt(0), textFieldName.getText(), recipeList);

			lblFeedBack.setText("Added " + textFieldName.getText());
			lblFeedBack.setForeground(Color.green);

			// Clear all the textFields
			textFieldName.setText("");
			textFieldQuantity.setText("");
		} else {
			lblFeedBack.setText("Invalid Field");
			lblFeedBack.setForeground(Color.red);
		}
	}
	
	private void setUpData(){
		ArrayList<Consumable> recipes = mediator.getConsumables();
		String[] consumableStrings = new String[recipes.size()];
		for(int i = 0; i < recipes.size(); i++){
			consumableStrings[i] = String.valueOf(recipes.get(i).getId());
		}
		recipeList = new JComboBox<String>(consumableStrings);
		recipeList.setBounds(179, 80, 86, 20);
		contentPane.add(recipeList);
		recipeList.addActionListener(this);
	}

}