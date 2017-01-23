package com;

import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CitationEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3921484689491913470L;
	private JPanel contentPane;
	private JSpinner spinnerId;
	private JSpinner spinnerCited;
	private JSpinner spinnerQuot;
	private Choice choice;
	private Choice choice2;
	private JButton editBtn;
	private JLabel lblNewLabel;
	/**
	 * Create the frame.
	 */
	public CitationEdit() {
		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		
		lblNewLabel = new JLabel("Edytowanie cytowania");
		contentPane.add(lblNewLabel, "cell 0 0 2 1,alignx center");
		
		contentPane.add(new JLabel("Wybierz ID cytowania:"), "cell 0 1,alignx center");
		
		spinnerId = new JSpinner(createSpinerModel("citations"));
		spinnerId.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				DatabaseHandle db = new DatabaseHandle();
				ResultSet rs = null;
				try {
					rs = db.stt.executeQuery("SELECT * FROM citations WHERE id=" +  spinnerId.getValue().toString());
					if(rs.next()){
						spinnerCited.setValue(rs.getObject(2));
						spinnerQuot.setValue(rs.getObject(3));
						choice.select(rs.getObject(4).toString());
						choice2.select(rs.getObject(5).toString());
					}
					else{
						JOptionPane.showMessageDialog(new JFrame(),"Nie ma cytowania o takim ID w bazie danych.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		contentPane.add(spinnerId, "flowx,cell 1 1");
	
		
		contentPane.add(new JLabel("ID artyku³u cytowanego"), "cell 0 2");	
		spinnerCited = new JSpinner(createSpinerModel("publications"));
		contentPane.add(spinnerCited, "cell 1 2,alignx left");
		
		
		contentPane.add(new JLabel("ID artyku³u cytuj¹cego"), "cell 0 3");	
		spinnerQuot = new JSpinner(createSpinerModel("articles"));
		contentPane.add(spinnerQuot, "cell 1 3,alignx left");
		
		choice = new Choice();
		choice.add("brak danych");
		choice.add("uznane");
		choice.add("nie uznane");
		choice.add("zg³oszone");
		
		contentPane.add(new JLabel("Google Schoolar"), "cell 0 4");		
		contentPane.add(choice, "cell 1 4,grow");
		
		choice2 = new Choice();
		choice2.add("brak danych");
		choice2.add("uznane");
		choice2.add("nie uznane");
		choice2.add("zg³oszone");
		
		contentPane.add(new JLabel("Web of Science"), "cell 0 5");
		contentPane.add(choice2, "cell 1 5,grow");
		

		
		editBtn = new JButton("Edytuj cytowanie");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseHandle db = new DatabaseHandle();		
				db.editCitation(spinnerId.getValue().toString(), spinnerCited.getValue().toString(), spinnerQuot.getValue().toString(), choice.getSelectedItem(), choice2.getSelectedItem());
				
				JOptionPane.showMessageDialog(new JFrame(),"Wykonano");

			}
		});
		contentPane.add(editBtn, "cell 0 6 2 1,alignx center");

		setContentPane(contentPane);
}

	private SpinnerModel createSpinerModel(String tableName) {
	
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
	
	public JPanel getContetnPane(){
		return contentPane;
	}
	
	public JButton getEditBtn(){
		return editBtn;
	}

	public JSpinner getSpinnerId() {
		return spinnerId;
	}
}
