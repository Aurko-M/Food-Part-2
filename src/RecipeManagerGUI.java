

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecipeManagerGUI extends GUIManager {

	private JPanel contentPane;
	private AddRecipeGUI addGUI;
	private EditRecipeGUI editGUI;
	private DeleteRecipeGUI deleteGUI;
	
	/**
	 * Create the frame.
	 */
	public RecipeManagerGUI(Mediator m) {
		
		mediator = m;
		
		addGUI = new AddRecipeGUI(m);
		editGUI = new EditRecipeGUI(m);
		deleteGUI = new DeleteRecipeGUI();
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddRecipe = new JButton("Add Recipe");
		btnAddRecipe.setBounds(118, 66, 109, 23);
		contentPane.add(btnAddRecipe);
		btnAddRecipe.addActionListener(this);

		JButton btnEditRecipe = new JButton("Edit Recipe");
		btnEditRecipe.setBounds(118, 123, 109, 23);
		contentPane.add(btnEditRecipe);
		btnEditRecipe.addActionListener(this);

		JLabel lblRecipeManager = new JLabel("Recipe Manager");
		lblRecipeManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRecipeManager.setBounds(99, 11, 140, 30);
		contentPane.add(lblRecipeManager);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Add Recipe") {
			try {
				addGUI = new AddRecipeGUI(mediator);
				addGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Edit Recipe") {
			try {
            editGUI = new EditRecipeGUI(mediator);
				editGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Back") {
			dispose();
			addGUI.dispose();
			editGUI.dispose();
			deleteGUI.dispose();
		}
	}

}
