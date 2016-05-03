import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class WorkoutManagerGUI extends GUIManager {
	private JPanel contentPane;
	private CreateWorkoutGUI addGUI;
	private EditWorkoutGUI editGUI;
	
	/**
	 * Create the frame.
	 */
	public WorkoutManagerGUI(Mediator m) {
		
		mediator = m;
		
		addGUI = new CreateWorkoutGUI(m);
		editGUI = new EditWorkoutGUI(m);
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddRecipe = new JButton("Add Workout");
		btnAddRecipe.setBounds(118, 66, 109, 23);
		contentPane.add(btnAddRecipe);
		btnAddRecipe.addActionListener(this);

		JButton btnEditRecipe = new JButton("Edit Workout");
		btnEditRecipe.setBounds(110, 123, 129, 23);
		contentPane.add(btnEditRecipe);
		btnEditRecipe.addActionListener(this);

		JLabel lblRecipeManager = new JLabel("Workout Manager");
		lblRecipeManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRecipeManager.setBounds(99, 11, 200, 30);
		contentPane.add(lblRecipeManager);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Add Workout") {
			try {
				addGUI = new CreateWorkoutGUI(mediator);
				addGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Edit Workout") {
			try {
				editGUI = new EditWorkoutGUI(mediator);
				editGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Back") {
			dispose();
			addGUI.dispose();
			editGUI.dispose();
		}
	}
}
