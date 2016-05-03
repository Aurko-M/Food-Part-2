
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

public class CreateWorkoutGUI extends GUIManager {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldId;
	private JTextField textFieldAddActivities;
	private ArrayList<Activity> workoutExercise = new ArrayList<Activity>();
	private JComboBox workoutList;
	private JTable table;
	private TableModel myTableModel;
	private Object[][] tableData = new Object[20][20];
	private JLabel lblFeedBack;


	/**
	 * Create the frame.
	 */
	public CreateWorkoutGUI(Mediator m) {
		mediator = m;
				
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateWorkout = new JLabel("Create Workout");
		lblCreateWorkout.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCreateWorkout.setBounds(107, 11, 136, 14);
		contentPane.add(lblCreateWorkout);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(8, 9, 62, 23);
		btnBack.addActionListener(this);
		contentPane.add(btnBack);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(74, 57, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("Description");
		lblId.setBounds(74, 89, 76, 14);
		contentPane.add(lblId);
		
		JLabel lblAddActivity = new JLabel("Add Activity");
		lblAddActivity.setBounds(74, 117, 100, 14);
		contentPane.add(lblAddActivity);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(157, 54, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(157, 86, 86, 20);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(56, 247, 89, 23);
		btnCancel.addActionListener(this);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(199, 247, 89, 23);
		btnCreate.addActionListener(this);
		contentPane.add(btnCreate);
		
		//Create the table to populate
		String[] columnNames = {
				"Exercise","Calories"
		};
				
		//myTableModel = new DefaultTableModel(tableData, columnNames);
		table = new JTable(tableData, columnNames);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 140, 300, 100);
		contentPane.add(scrollPane);
		
		lblFeedBack = new JLabel("");
		lblFeedBack.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblFeedBack.setBounds(70, 35, 200, 14);
		contentPane.add(lblFeedBack);
		
		setUpData();
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Create") {
			// Send the text from the textfields to be sanitized.
			addButtonPressed();
		} else if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}else{
			//update the table object.
			String name = workoutList.getSelectedItem().toString();
			Activity cons = mediator.getActivity(name);
			tableData[workoutExercise.size()][0] = cons.getName();
			tableData[workoutExercise.size()][1] = cons.getCalories();
			workoutExercise.add(cons);
			table.repaint();
		}
	}
	
	private void addButtonPressed() {
		// Validate the textFields
		String[] fields = new String[] { textFieldName.getText(), textFieldId.getText() };
		if (isValidated(fields)) {
			//create a workout
			int totalCalories = 0;
			for(int i = 0; i < workoutExercise.size(); i++){
				totalCalories += workoutExercise.get(i).getCalories();
			}
			//mediator.createRecipe(textFieldId.getText().charAt(0), textFieldName.getText(), workoutList);
			//addWorkout(String name, String desc, int calories, ArrayList<Activity> workouts)
			mediator.addWorkout(textFieldName.getText(),textFieldId.getText(),totalCalories,workoutExercise);
			lblFeedBack.setText("Added " + textFieldName.getText());
			lblFeedBack.setForeground(Color.green);

			// Clear all the textFields
			textFieldName.setText("");
			textFieldId.setText("");
		} else {
			lblFeedBack.setText("Invalid Field");
			lblFeedBack.setForeground(Color.red);
		}
	}
	
	private void setUpData(){
		ArrayList<Activity> workouts = mediator.getWorkouts();
		String[] consumableStrings = new String[workouts.size()];
		for(int i = 0; i < workouts.size(); i++){
			consumableStrings[i] = String.valueOf(workouts.get(i).getName());
		}
		workoutList = new JComboBox<String>(consumableStrings);
		workoutList.setBounds(155, 115, 96, 20);
		contentPane.add(workoutList);
		workoutList.addActionListener(this);
	}
	
}
