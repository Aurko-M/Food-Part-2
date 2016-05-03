
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

public class DeleteWorkoutGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;

	/**
	 * Create the frame.
	 */
	public DeleteWorkoutGUI(Mediator m) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteWorkout = new JLabel("Delete Workout");
		lblDeleteWorkout.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDeleteWorkout.setBounds(104, 11, 136, 14);
		contentPane.add(lblDeleteWorkout);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(5, 9, 61, 23);
		contentPane.add(btnBack);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(83, 72, 46, 14);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(176, 69, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(59, 227, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(192, 227, 89, 23);
		contentPane.add(btnDelete);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Delete") {
		} else if (ae.getActionCommand() == "Cancel") {
			dispose();
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		}

	}

}
