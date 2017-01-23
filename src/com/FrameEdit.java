package com;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;


//TODO Spinner wywala gdy pusta baza danych
public class FrameEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1597437777762610784L;
	private JPanel contentPane;
	protected JTextField authorsField;
	protected JTextField titleField;
	protected JTextField placeField;
	protected JTextField pdateField;
	protected JTextField pagesField;

	public FrameEdit() {

		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[396.00][]", "[][][][][][][][][][][][]"));
		
		JLabel authorsLabel = new JLabel("Autor/Autorzy:");
		contentPane.add(authorsLabel, "flowx,cell 0 2,alignx left");
		
		JLabel titleLabel = new JLabel("Tytu\u0142:");
		contentPane.add(titleLabel, "flowx,cell 0 3,alignx left");
		
		JLabel placeLabel = new JLabel("Miejsce:");
		contentPane.add(placeLabel, "flowx,cell 0 4,alignx left");
		
		JLabel pdateLabel = new JLabel("Data:");
		contentPane.add(pdateLabel, "flowx,cell 0 5,alignx left");
		


		setContentPane(contentPane);
		
		JLabel pagesLabel = new JLabel("Strony:");
		contentPane.add(pagesLabel, "flowx,cell 0 6,alignx left");
		
		placeField = new JTextField();
		contentPane.add(placeField, "cell 0 4,growx");
		placeField.setColumns(10);
		
		pdateField = new JTextField();
		contentPane.add(pdateField, "cell 0 5,growx");
		pdateField.setColumns(10);
		
		pagesField = new JTextField();
		contentPane.add(pagesField, "cell 0 6,growx");
		pagesField.setColumns(10);
		
		titleField = new JTextField();
		contentPane.add(titleField, "cell 0 3,growx");
		titleField.setColumns(10);
		
		authorsField = new JTextField();
		contentPane.add(authorsField, "cell 0 2 2 1,growx");
		authorsField.setColumns(10);
		

	}
	

	protected SpinnerModel createSpinnerModel(String tableName) {
		
		DatabaseHandle db = new DatabaseHandle();
		String sql = "SELECT MAX(id) FROM " + tableName;
		ResultSet rs = null;

		try {
			rs = db.stt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		long x = 10000;
		try {
			if(rs.next())x = rs.getLong(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		SpinnerModel sm = null;
		
		sm = new SpinnerNumberModel(1, 1, x, 1);
		
		return sm;
	}
	
	public void clearFields(){
		authorsField.setText("");
		titleField.setText("");
		placeField.setText("");
		pdateField.setText("");
		pagesField.setText("");
	}
}
