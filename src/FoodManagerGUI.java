

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoodManagerGUI extends GUIManager {

	private JPanel contentPane;
	private AddFoodGUI addGUI;
	private EditFoodGUI editGUI;
	private DeleteFoodGUI deleteGUI;
	
	/**
	 * Create the frame.
	 */
	public FoodManagerGUI(Mediator m) {
		
		mediator = m;
		
		addGUI = new AddFoodGUI(m);
		editGUI = new EditFoodGUI(m);
		deleteGUI = new DeleteFoodGUI(m);
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddFood = new JButton("Add Food");
		btnAddFood.setBounds(123, 69, 89, 23);
		contentPane.add(btnAddFood);
		btnAddFood.addActionListener(this);

		JButton btnEditFood = new JButton("Edit Food");
		btnEditFood.setBounds(123, 129, 89, 23);
		contentPane.add(btnEditFood);
		btnEditFood.addActionListener(this);

		JLabel lblFoodManager = new JLabel("Food Manager");
		lblFoodManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFoodManager.setBounds(109, 21, 122, 23);
		contentPane.add(lblFoodManager);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Add Food") {
			try {
				addGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Edit Food") {
			try {
				editGUI = new EditFoodGUI(mediator);
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