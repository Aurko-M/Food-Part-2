

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.ActionEvent;

public class LogManagerGUI extends GUIManager {

	private JPanel contentPane;
	private JTextField textFieldDate;
	private TableRowSorter<TableModel> sorter;
	private DefaultTableModel myTableModel;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public LogManagerGUI(Mediator m) {
		
		mediator = m;
		
		setBounds(100, 100, 357, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldDate = new JTextField();
		textFieldDate.setBounds(123, 56, 86, 20);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);

		JButton btnFilter = new JButton("Filter");
		btnFilter.setBounds(210, 56, 66, 23);
		contentPane.add(btnFilter);
		btnFilter.addActionListener(this);
		
		JButton btnRemoveRow = new JButton("Remove Row");
		btnRemoveRow.setBounds(125, 246, 130, 23);
		contentPane.add(btnRemoveRow);
		btnRemoveRow.addActionListener(this);

		createTable();

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 11, 55, 23);
		contentPane.add(btnBack);
		btnBack.addActionListener(this);

		JLabel lblLogManager = new JLabel("Log Manager");
		lblLogManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogManager.setBounds(107, 5, 120, 30);

		contentPane.add(lblLogManager);
	}

	private void createTable() {
		String lines[] = mediator.printLog("log.csv").split("\\r?\\n");
		System.out.println(lines.length);
		String[] columnNames = { "Date", "T", "Value" };
		Object[][] dummyData1 = new Object[lines.length][columnNames.length];
		for(int i = 0; i < lines.length; i++){
			String data[] = lines[i].split("\\s+");
			//System.out.println(data.length);
			//System.out.println(data[0]);
			dummyData1[i][0] = data[0] + "-" + data[1] + "-" + data[2];
			dummyData1[i][1] = data[3];
			dummyData1[i][2] = data[4];
		}
		// Create a table to holde the data
	
		myTableModel = new DefaultTableModel(dummyData1, columnNames) ;
		table = new JTable(myTableModel);
		table.setFillsViewportHeight(true);
		sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 100, 330, 130);
		// change the size of the collumns
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		//table.getColumnModel().getColumn(3).setPreferredWidth(10);
		//table.getColumnModel().getColumn(4).setPreferredWidth(30);
		//table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.setAutoCreateRowSorter(true);

		contentPane.add(scrollPane);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Filter") {
			String text = textFieldDate.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				sorter.setRowFilter(RowFilter.regexFilter(text));
			}
		} else if (ae.getActionCommand() == "Back") {
			dispose();
		} else if (ae.getActionCommand() == "Remove Row"){
			removeRowButtonPressed();
		}
	}
	
	private void removeRowButtonPressed(){
		String cellDate = (String) myTableModel.getValueAt(table.getSelectedRow(), 0);
		cellDate = cellDate.replaceAll("[\\s\\-()]", ",");
		System.out.println(cellDate);
		mediator.removeLog(cellDate);
		myTableModel.removeRow(table.getSelectedRow());
	}

}