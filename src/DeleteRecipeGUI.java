

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteRecipeGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldName;

	/**
	 * Create the frame.
	 */
	public DeleteRecipeGUI() {
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(21, 16, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);

		JLabel lblDeleteRecipe = new JLabel("Delete Recipe");
		lblDeleteRecipe.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDeleteRecipe.setBounds(129, 11, 119, 29);
		contentPane.add(lblDeleteRecipe);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(92, 90, 46, 14);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(189, 87, 86, 20);
		contentPane.add(textFieldName);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(39, 232, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(this);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(228, 232, 89, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Delete") {
			// Remove the recipe from the db.
		} else if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}

	}

}