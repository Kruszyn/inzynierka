package gui;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import db.DatabaseHandle;

import javax.swing.JOptionPane;

public class AddCitationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3403375760718429116L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public AddCitationFrame() {
		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
		
		contentPane.add(new JLabel("Dodawanie cytowania"), "cell 0 0 2 1,alignx center");

		
	
		contentPane.add(new JLabel("ID artyku³u cytowanego"), "cell 0 1");	
		JSpinner spinnerCited = new JSpinner(createSpinerModel("publications"));
		contentPane.add(spinnerCited, "cell 1 1,alignx center");
		
		
		contentPane.add(new JLabel("ID artyku³u cytuj¹cego"), "cell 0 2");	
		JSpinner spinnerQuot = new JSpinner(createSpinerModel("articles"));
		contentPane.add(spinnerQuot, "cell 1 2,alignx center");
		
		Choice choice = new Choice();
		choice.add("brak danych");
		choice.add("uznane");
		choice.add("nie uznane");
		choice.add("zg³oszone");
		
		contentPane.add(new JLabel("Google schoolar"), "cell 0 3");		
		contentPane.add(choice, "cell 1 3,grow");
		
		Choice choice2 = new Choice();
		choice2.add("brak danych");
		choice2.add("uznane");
		choice2.add("nie uznane");
		choice2.add("zg³oszone");
		
		contentPane.add(new JLabel("Baza dancyh 2"), "cell 0 4");
		contentPane.add(choice2, "cell 1 4,grow");
		
		JButton addCitationButton = new JButton("Dodaj cytowanie");
		addCitationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//public void addCitation(String artCited, String artQuot, String gs, String wos){

				DatabaseHandle db = new DatabaseHandle();
				db.addCitation( spinnerCited.getValue().toString(), spinnerQuot.getValue().toString(), choice.getSelectedItem(), choice2.getSelectedItem());
				//TODO sprawdzenie poprawnoœci dodania
				JOptionPane.showMessageDialog(new JFrame(),"Dodano cytowanie.");

			}
		});
		contentPane.add(addCitationButton, "cell 0 5 2 1,alignx center");

		setContentPane(contentPane);
	}
	
	private SpinnerModel createSpinerModel(String tableName)  {

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

}
