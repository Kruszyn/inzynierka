package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JCheckBox;

public class PublicationEdit extends FrameEdit {


	private static final long serialVersionUID = -3454779540377722098L;
	private JSpinner spinnerId;
	private JCheckBox chckbxGoogle;
	private JCheckBox chckbxScience;
	
	public PublicationEdit() {

		super();
		getContentPane().add(new JLabel("Edytowanie publikacji"), "cell 0 0 2 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("Wybierz ID edytowanej publikacji:");
		getContentPane().add(lblNewLabel, "flowx,cell 0 1,alignx left");
		initValues();
		
		JLabel lblNewLabel_1 = new JLabel("Obecno�� publikacji w bazach danych:");
		getContentPane().add(lblNewLabel_1, "cell 0 7 3 1");
		
		chckbxGoogle = new JCheckBox("Google Schoolar");
		getContentPane().add(chckbxGoogle, "cell 0 8");
		
		spinnerId = new JSpinner(createSpinnerModel("publications"));
		spinnerId.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				DatabaseHandle db = new DatabaseHandle();
				ResultSet rs = null;
				try {
					rs = db.stt.executeQuery("SELECT * FROM publications WHERE id=" +  spinnerId.getValue().toString());
					if(rs.next()){
						authorsField.setText(rs.getObject(2).toString());
						titleField.setText(rs.getObject(3).toString());
						placeField.setText(rs.getObject(4).toString());
						pdateField.setText(rs.getObject(5).toString());
						pagesField.setText(rs.getObject(6).toString());
						chckbxGoogle.setSelected((boolean) rs.getObject(7));
						chckbxScience.setSelected((boolean) rs.getObject(8));
						
					}
					else{
						clearFields();
						JOptionPane.showMessageDialog(new JFrame(),"Nie ma publikacji o takim ID w bazie danych.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}


		});
		getContentPane().add(spinnerId, "cell 0 1,alignx left");
		
		JButton editPublicationButton = new JButton("Edytuj publikacj�");
		editPublicationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DatabaseHandle db = new DatabaseHandle();
				db.editPublication(spinnerId.getValue().toString(), authorsField.getText(), titleField.getText(),
						placeField.getText(), pdateField.getText(), pagesField.getText(), String.valueOf(chckbxGoogle.isSelected()), String.valueOf(chckbxScience.isSelected()));
				//TODO sprawdzenie poprawno�ci dodania

				JOptionPane.showMessageDialog(new JFrame(),"Edytowano publikacj�.");

			}
		});
		
		chckbxScience = new JCheckBox("Web of Science");
		getContentPane().add(chckbxScience, "cell 0 9");
		

		getContentPane().add(editPublicationButton, "flowx,cell 0 10,alignx center");
		
	}
	
	private void initValues() {
		DatabaseHandle db = new DatabaseHandle();
		ResultSet rs = null;
		Object min = null;
		try {
			rs = db.stt.executeQuery("SELECT MIN(id) FROM publications");
			if(rs.next()){
				min = rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		try {
			rs = db.stt.executeQuery("SELECT * FROM publications WHERE id=" + min.toString());
			if(rs.next()){
				authorsField.setText(rs.getObject(2).toString());
				titleField.setText(rs.getObject(3).toString());
				placeField.setText(rs.getObject(4).toString());
				pdateField.setText(rs.getObject(5).toString());
				pagesField.setText(rs.getObject(6).toString());
			}
			else{
				JOptionPane.showMessageDialog(new JFrame(),"Nie ma publikacji o takim ID w bazie danych.");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public JSpinner getSpinnerId() {
		return spinnerId;
	}


}
