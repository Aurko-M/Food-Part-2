import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteExerciseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteExerciseGUI frame = new DeleteExerciseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeleteExerciseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteAnExercise = new JLabel("Delete an Exercise");
		lblDeleteAnExercise.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDeleteAnExercise.setBounds(105, 11, 162, 14);
		contentPane.add(lblDeleteAnExercise);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 9, 61, 23);
		contentPane.add(btnBack);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(85, 70, 46, 14);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(181, 67, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(58, 227, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDelete.setBounds(194, 227, 89, 23);
		contentPane.add(btnDelete);
	}

}
