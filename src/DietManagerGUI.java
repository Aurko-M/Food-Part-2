
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;







import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

public class DietManagerGUI extends GUIManager implements FocusListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private FoodManagerGUI foodGUI;
	private RecipeManagerGUI recipeGUI;
	private LogManagerGUI logGUI;
	private ClientManagerGUI clientGUI;
	private ExerciseManagerGUI exerciseGUI;
	private WorkoutManagerGUI workoutGUI;
	private JComboBox<String> consumableList;
	private JLabel lblTotalCaloriesCount;
	private ArrayList<Consumable> foodForToday = new ArrayList<Consumable>();
	private ArrayList<Activity> activityForToday = new ArrayList<Activity>();
	private double totalConsumableCalories = 0;
	private double totalActivityCalories = 0;
	
	private JTextArea foodList;
	private JTextArea exerciseList;
	private JComboBox<String> activityList;
	
	/**
	 * Create the frame.
	 */
	public DietManagerGUI(Mediator m) {	
		
		mediator = m;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		foodGUI = new FoodManagerGUI(m);
		recipeGUI = new RecipeManagerGUI(m);
		logGUI = new LogManagerGUI(m);
		exerciseGUI = new ExerciseManagerGUI(m);
		workoutGUI = new WorkoutManagerGUI(m);
		
		setBounds(100, 100, 500, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotalCalories = new JLabel("Total Calories:");
		lblTotalCalories.setBounds(130, 220, 120, 26);
		contentPane.add(lblTotalCalories);

		lblTotalCaloriesCount = new JLabel("0");
		lblTotalCaloriesCount.setBounds(170, 240, 120, 26);
		contentPane.add(lblTotalCaloriesCount);
		
		JButton btnSubmitFood = new JButton("Submit");
		btnSubmitFood.setBounds(130, 265, 90, 23);
		contentPane.add(btnSubmitFood);
		btnSubmitFood.addActionListener(this);
		
		JButton btnFoodManager = new JButton("Food Manager");
		btnFoodManager.setBounds(340, 40, 146, 23);
		contentPane.add(btnFoodManager);
		btnFoodManager.addActionListener(this);

		JButton btnRecipeManager = new JButton("Recipe Manager");
		btnRecipeManager.setBounds(340, 80, 146, 23);
		contentPane.add(btnRecipeManager);
		btnRecipeManager.addActionListener(this);

		JButton btnLogManager = new JButton("Log Manager");
		btnLogManager.setBounds(340, 120, 146, 23);
		contentPane.add(btnLogManager);
		btnLogManager.addActionListener(this);

		JButton btnClientManager = new JButton("Client Manager");
		btnClientManager.setBounds(340, 160, 146, 23);
		contentPane.add(btnClientManager);
		btnClientManager.addActionListener(this);
		
		JButton btnExerciseManager = new JButton("Exercise Manager");
		btnExerciseManager.setBounds(340, 200, 146, 23);
		contentPane.add(btnExerciseManager);
		btnExerciseManager.addActionListener(this);
		
		JButton btnWorkoutManager = new JButton("Workout Manager");
		btnWorkoutManager.setBounds(340, 240, 146, 23);
		contentPane.add(btnWorkoutManager);
		btnWorkoutManager.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Diet Manager App");
		lblNewLabel.setForeground(Color.red);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(120, 10, 250, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblLabelDietToday = new JLabel("Diet Today");
		lblLabelDietToday.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLabelDietToday.setBounds(50, 55, 250, 26);
		contentPane.add(lblLabelDietToday);
		
		JLabel lblLabelExerciswToday = new JLabel("Exercise Today");
		lblLabelExerciswToday.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLabelExerciswToday.setBounds(200, 55, 250, 26);
		contentPane.add(lblLabelExerciswToday);

		JButton btnSignOut = new JButton("Exit");
		btnSignOut.setBounds(4, 8, 89, 23);
		contentPane.add(btnSignOut);
		btnSignOut.addActionListener(this);
		
		foodList = new JTextArea();
		foodList.setBounds(10, 120, 150, 100);
		contentPane.add(foodList);
		
		exerciseList = new JTextArea();
		exerciseList.setBounds(180, 120, 150, 100);
		contentPane.add(exerciseList);
		
		consumableList = new JComboBox<String>();
		consumableList.setBounds(40, 90, 86, 20);
		setUpData();
		consumableList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(consumableList.getItemCount() > 0){
					char id = consumableList.getSelectedItem().toString().charAt(0);
					Consumable cons = mediator.getConsumable(id); 
					foodForToday.add(cons);
					foodList.append(cons.getName() + "\n");
					totalConsumableCalories += cons.getCalories();
					double netCalories = totalConsumableCalories - totalActivityCalories;
					lblTotalCaloriesCount.setText(netCalories + "");
				}
			}
		});
		
		activityList = new JComboBox<String>();
		activityList.setBounds(210, 90, 86, 20);
		setUpList();
		activityList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(activityList.getItemCount() > 0) {
					String name = activityList.getSelectedItem().toString();
					Activity act = mediator.getActivity(name); 
					activityForToday.add(act);
					exerciseList.append(act.getName() + "\n");
					totalActivityCalories += act.getCalories() * 30;
					double netCalories = totalConsumableCalories - totalActivityCalories;
					lblTotalCaloriesCount.setText(netCalories + "");
				}
				System.out.println("got that");
			}
		});

		contentPane.add(consumableList);
		contentPane.add(activityList);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Food Manager") {
			try {
				foodGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Recipe Manager") {
			try {
				recipeGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Log Manager") {
			try {
				logGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Client Manager") {
			try {
				clientGUI = new ClientManagerGUI(mediator);
				clientGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (ae.getActionCommand() == "Exercise Manager") {
			try {
				exerciseGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Workout Manager") {
			try {
				workoutGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Exit") {
			System.exit(0);
		} else if (ae.getActionCommand() == "Submit") {
			submitButtonPressed();
		} else{
			
		}
	}
	
	private void submitButtonPressed(){
		foodList.setText("");
		exerciseList.setText("");
		for(int i = 0; i < foodForToday.size(); i++){
			mediator.writeLog(foodForToday.get(i).getId());
		}
		for(int i = 0; i < activityForToday.size(); i++){
			mediator.writeLog(activityForToday.get(i).getName());
		}
		mediator.writeLog(totalConsumableCalories,totalActivityCalories);
		writeForMD();
		foodForToday = new ArrayList<Consumable>();
		activityForToday = new ArrayList<Activity>();
		lblTotalCaloriesCount.setText("0");
		
	}
	
	private void writeForMD(){
		ArrayList<ArrayList<String>> toSend = new  ArrayList<ArrayList<String>>();
		String values = "";
		ArrayList<String> foods = new ArrayList<String>();
		ArrayList<String> activities = new ArrayList<String>();
		ArrayList<String> summary = new ArrayList<String>();
		values += "fec";
		for(int i = 0; i < foodForToday.size(); i++){
			foods.add(foodForToday.get(i).getName());
			foods.add(foodForToday.get(i).getCalories() + "");
		}
		for(int i = 0; i < activityForToday.size(); i++){
			activities.add(activityForToday.get(i).getName());
			activities.add(activityForToday.get(i).getCalories() + "");
		}
		
		//Add the summary strings
		summary.add(((Client)mediator.getClient()).getWeight() + "");
		summary.add(((Client)mediator.getClient()).getMaxCalories() + "");
		summary.add(totalConsumableCalories + "");
		summary.add(totalActivityCalories + "");
		summary.add((totalConsumableCalories - totalActivityCalories) + "");
		
		
		toSend.add(foods);
		toSend.add(activities);
		toSend.add(summary);
		mediator.writeToMarkDown(toSend, values);
	}
	
	private void setUpData() {
		ArrayList<Consumable> consumables = mediator.getConsumables();
		String[] consumableStrings = new String[consumables.size()];
		consumableList.removeAllItems();
		for (int i = 0; i < consumables.size(); i++) {
			consumableStrings[i] = String.valueOf(consumables.get(i).getId());
			consumableList.addItem(String.valueOf(consumables.get(i).getId()));
		}
		
		consumableList.repaint();
		consumableList.revalidate();
	}
	
	private void setUpList() {
		ArrayList<Activity> activity = mediator.getWorkouts();
		String[] activityStrings = new String[activity.size()];
		activityList.removeAllItems();
		for (int i = 0; i < activity.size(); i++) {
			activityStrings [i] = String.valueOf(activity.get(i).getName());
			activityList.addItem(String.valueOf(activity.get(i).getName()));
		}
		
		activityList.repaint();
		activityList.revalidate();
	}
	

    public void focusGained(FocusEvent fe){
        System.out.println("Focus gained by box");
        setUpList();
        setUpData();
    }

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Focus lost");
	}
}
