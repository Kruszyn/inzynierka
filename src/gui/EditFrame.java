package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import db.DatabaseHandle;
import net.miginfocom.swing.MigLayout;


//TODO Spinner wywala gdy pusta baza danych
public class EditFrame extends JFrame {

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

	public EditFrame() {

		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
			
		JLabel authorsLabel = new JLabel("Autor/Autorzy:");
		contentPane.add(authorsLabel, "cell 0 2,alignx left");
		
		authorsField = new JTextField();
		contentPane.add(authorsField, "cell 1 2,growx");
		authorsField.setColumns(10);
		
		JLabel titleLabel = new JLabel("Tytu\u0142:");
		contentPane.add(titleLabel, "cell 0 3,alignx left");
		
		titleField = new JTextField();
		contentPane.add(titleField, "cell 1 3,growx");
		titleField.setColumns(10);
		
		JLabel placeLabel = new JLabel("Miejsce:");
		contentPane.add(placeLabel, "cell 0 4,alignx left");
		
		placeField = new JTextField();
		contentPane.add(placeField, "cell 1 4,growx");
		placeField.setColumns(10);
		
		JLabel pdateLabel = new JLabel("Data:");
		contentPane.add(pdateLabel, "cell 0 5,alignx left");
		
		pdateField = new JTextField();
		contentPane.add(pdateField, "cell 1 5,growx");
		pdateField.setColumns(10);
		
		JLabel pagesLabel = new JLabel("Strony:");
		contentPane.add(pagesLabel, "cell 0 6,alignx left");
		
		pagesField = new JTextField();
		contentPane.add(pagesField, "cell 1 6,growx");
		pagesField.setColumns(10);
		


		setContentPane(contentPane);
		

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
