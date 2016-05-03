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
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class EditExerciseGUI extends GUIManager{

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldId;
	private JTextField textFieldCalories;
	private JComboBox exerciseList;
	private JLabel lblFeedBack;

	/**
	 * Create the frame.
	 */
	public EditExerciseGUI(Mediator m) {
		mediator = m;
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditAnExercise = new JLabel("Edit an Exercise");
		lblEditAnExercise.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditAnExercise.setBounds(103, 11, 136, 14);
		contentPane.add(lblEditAnExercise);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 9, 63, 23);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(89, 89, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("Description");
		lblId.setBounds(89, 130, 75, 14);
		contentPane.add(lblId);
		
		JLabel lblCalories = new JLabel("Calories");
		lblCalories.setBounds(89, 170, 75, 14);
		contentPane.add(lblCalories);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(177, 86, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(177, 127, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldCalories = new JTextField();
		textFieldCalories.setBounds(177, 167, 86, 20);
		contentPane.add(textFieldCalories);
		textFieldCalories.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(21, 227, 89, 23);
		btnCancel.addActionListener(this);
		contentPane.add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(124, 228, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		
		JButton btnEdit = new JButton("Confirm");
		btnEdit.setBounds(221, 227, 89, 23);
		btnEdit.addActionListener(this);
		contentPane.add(btnEdit);
		
		lblFeedBack = new JLabel("");
		lblFeedBack.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFeedBack.setBounds(70, 35, 200, 14);
		contentPane.add(lblFeedBack);
		
		setUpData();
		contentPane.add(exerciseList);
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Confirm") {
			// Perform steps to edit the Exercise
			confirmButtonPressed();
		} else if (ae.getActionCommand() == "Delete") {
			deleteButtonPressed();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		} else {
			if(exerciseList.getItemCount() > 0){
				String name = exerciseList.getSelectedItem().toString();
				Activity activity = mediator.getActivity(name);
				if(activity != null){
					textFieldName.setText(activity.getName());
					textFieldId.setText(activity.getDesc());
					textFieldCalories.setText(activity.getCalories() + "");
				}
			}
		}
	}

	private void deleteButtonPressed() {
		if(exerciseList.getSelectedItem() != null){
			mediator.deleteConsumable(exerciseList.getSelectedItem().toString()
				.charAt(0));
		}
		clearFields();
		//exerciseList.removeAllItems();
		exerciseList.revalidate();
		exerciseList.repaint();
		setUpData();
	}

	private void confirmButtonPressed() {
		// Validate the textFields
		String[] fields = new String[] { textFieldName.getText(), textFieldId.getText(),
				textFieldCalories.getText(),
				};
		if (isValidated(fields)) {
			// edit exercise
			//mediator.editExercise(exerciseList.getSelectedItem().toString().charAt(0),
			//		textFieldName.getText(),
			//		Double.parseDouble(textFieldCalories.getText()));						// edit exercise in mediator
			mediator.editActivity(exerciseList.getSelectedItem().toString(),
					textFieldName.getText(), textFieldId.getText(), Integer.parseInt(textFieldCalories.getText()));
			JOptionPane.showMessageDialog(contentPane, "Edited Exercise: "
					+ textFieldName.getText());
			dispose();
			clearFields();
		} else {
			lblFeedBack.setText("Invalid Field");
			lblFeedBack.setForeground(Color.red);
		}
	}

	private void clearFields() {
		// Clear all the textFields
		textFieldName.setText("");
		textFieldId.setText("");
		textFieldCalories.setText("");
	}

	private void setUpData() {
		ArrayList<Activity> activity = mediator.getWorkouts();

		ArrayList<String> createdExercise = new ArrayList<String>();
		for (int i = 0; i < activity.size(); i++) {
			if (activity.get(i).isAExercise()) {
				createdExercise.add(String.valueOf(activity.get(i).getName()));
			}
		}
		String[] activityStrings = new String[createdExercise.size()];
		for (int i = 0; i < createdExercise.size(); i++) {
			activityStrings[i] = createdExercise.get(i);
		}

		exerciseList = new JComboBox<String>(activityStrings);
		exerciseList.setBounds(176, 60, 86, 20);
		exerciseList.addActionListener(this);
	}

}
