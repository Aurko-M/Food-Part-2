
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

public class EditWorkoutGUI extends GUIManager {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldDescription;
	private JComboBox ActivityList;

	/**
	 * Create the frame.
	 */
	public EditWorkoutGUI(Mediator m) {

		mediator = m;
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 62, 23);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);
		
		JLabel lblEditWorkout = new JLabel("Edit Workout");
		lblEditWorkout.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditWorkout.setBounds(110, 13, 118, 14);
		contentPane.add(lblEditWorkout);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(179, 96, 86, 20);
		contentPane.add(textFieldName);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(76, 99, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblselectWorkout = new JLabel("Select Workout");
		lblselectWorkout.setBounds(76, 55, 106, 14);
		contentPane.add(lblselectWorkout);
		
		JLabel lblId = new JLabel("Description");
		lblId.setBounds(76, 151, 76, 14);
		contentPane.add(lblId);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setColumns(10);
		textFieldDescription.setBounds(179, 158, 86, 20);
		contentPane.add(textFieldDescription);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(37, 227, 89, 23);
		btnCancel.addActionListener(this);
		contentPane.add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(120, 227, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(207, 227, 89, 23);
		btnEdit.addActionListener(this);
		contentPane.add(btnEdit);
		
		setUpData();
		contentPane.add(ActivityList);
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Edit") {
			// Send the text from the textfields to be sanitized.
			confirmButtonPressed();
		} else if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		} else if (ae.getActionCommand() == "Delete") {
			deleteButtonPressed();
		} else {
			if(ActivityList.getItemCount() > 0){
				String id = ActivityList.getSelectedItem().toString();
				Activity cons = mediator.getActivity(id);
				textFieldName.setText(cons.getName());
				textFieldDescription.setText(cons.getDesc());
			}
		}
	}

	private void deleteButtonPressed() {
		if(ActivityList.getSelectedItem() != null){
			//delete the workout
			mediator.deleteActivity(ActivityList.getSelectedItem().toString());
			//mediator.deleteConsumable(ActivityList.getSelectedItem().toString().charAt(0));
		}
		textFieldName.setText("");
		textFieldDescription.setText("");
		ActivityList.removeAllItems();
		ActivityList.revalidate();
		ActivityList.repaint();
		setUpData();
	}

	private void confirmButtonPressed() {
		// Validate the textFields
		String[] fields = new String[] { textFieldName.getText(),
				textFieldDescription.getText() };
		if (isValidated(fields)) {
			// Edit a Workout.
			mediator.editActivity(ActivityList.getSelectedItem().toString(),textFieldName.getText(),
					textFieldDescription.getText(),-1);
			
			//ActivityList.removeAllItems();
			ActivityList.revalidate();
			ActivityList.repaint();
			setUpData();
			
			JOptionPane.showMessageDialog(contentPane, "Eddited Workout: "
					+ textFieldName.getText());

			// Clear all the textFields
			textFieldName.setText("");
			textFieldDescription.setText("");
		}
	}

	private void setUpData() {
		ArrayList<Activity> activity = mediator.getWorkouts();  //  fix mediator
		ArrayList<String> createdActivities = new ArrayList<String>();
		for (int i = 0; i < activity.size(); i++) {
			if (activity.get(i).isAWorkoutExercise()) {
				createdActivities.add(String.valueOf(activity.get(i).getName()));
			}
		}

		String[] activityStrings = new String[createdActivities.size()];
		for (int i = 0; i < createdActivities.size(); i++) {
			activityStrings[i] = createdActivities.get(i);
		}

		ActivityList = new JComboBox<String>(activityStrings);
		ActivityList.setBounds(179, 50, 90, 20);
		ActivityList.addActionListener(this);
	}

}
