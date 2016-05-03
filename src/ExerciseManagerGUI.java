import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ExerciseManagerGUI extends GUIManager {
	private JPanel contentPane;
	private AddExerciseGUI addGUI;
	private EditExerciseGUI editGUI;;
	
	/**
	 * Create the frame.
	 */
	public ExerciseManagerGUI(Mediator m) {
		
		mediator = m;
		
		addGUI = new AddExerciseGUI(m);
		editGUI = new EditExerciseGUI(m);
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddRecipe = new JButton("Add Exercise");
		btnAddRecipe.setBounds(118, 66, 109, 23);
		contentPane.add(btnAddRecipe);
		btnAddRecipe.addActionListener(this);

		JButton btnEditRecipe = new JButton("Edit Exercise");
		btnEditRecipe.setBounds(118, 123, 109, 23);
		contentPane.add(btnEditRecipe);
		btnEditRecipe.addActionListener(this);

		JLabel lblRecipeManager = new JLabel("Exercise Manager");
		lblRecipeManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRecipeManager.setBounds(99, 11, 200, 30);
		contentPane.add(lblRecipeManager);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Add Exercise") {
			try {
				addGUI = new AddExerciseGUI(mediator);
				addGUI.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ae.getActionCommand() == "Edit Exercise") {
			try {
				editGUI = new EditExerciseGUI(mediator);
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
