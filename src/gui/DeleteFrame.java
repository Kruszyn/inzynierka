package gui;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import db.DatabaseHandle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6566181059536485509L;
	private JPanel contentPane;

	public DeleteFrame() {
		
		contentPane = new JPanel();
		
		DatabaseHandle db = new DatabaseHandle();
		
		contentPane.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Usuñ publikacjê o ID:");
		contentPane.add(lblNewLabel, "cell 0 0");
		
		JSpinner spinnerPubl = new JSpinner(createSpinerModel("publications"));
		contentPane.add(spinnerPubl, "cell 1 0");
		
		JButton btnDeletePubl = new JButton("Usuñ publikacje");
		btnDeletePubl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deletePublication(spinnerPubl.getValue().toString());
				JOptionPane.showMessageDialog(new JFrame(),"Usuniêto publikacje o id: " + spinnerPubl.getValue());
			}
		});
		contentPane.add(btnDeletePubl, "cell 2 0,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Usuñ cytowanie o ID:");
		contentPane.add(lblNewLabel_1, "cell 0 1");
		
		JSpinner spinnerCit = new JSpinner(createSpinerModel("citations"));
		contentPane.add(spinnerCit, "cell 1 1");
		
		JButton btnDeleteCit = new JButton("Usuñ cytowanie");
		btnDeleteCit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteCitation(spinnerCit.getValue().toString());
				JOptionPane.showMessageDialog(new JFrame(),"Usuniêto cytowanie o id: " + spinnerCit.getValue());		
			}
		});
		contentPane.add(btnDeleteCit, "cell 2 1");
		
		JLabel lblNewLabel_2 = new JLabel("Usuñ artyku³ o ID:");
		contentPane.add(lblNewLabel_2, "cell 0 2");
		
		JSpinner spinnerArt = new JSpinner(createSpinerModel("articles"));
		contentPane.add(spinnerArt, "cell 1 2");
		
		JButton btnDeleteArt = new JButton("Usuñ artyku³");
		btnDeleteArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.deleteArticle(spinnerArt.getValue().toString());
				JOptionPane.showMessageDialog(new JFrame(),"Usuniêto artyku³ o id: " + spinnerArt.getValue());
			}
		});
		contentPane.add(btnDeleteArt, "cell 2 2,growx");
		
		
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
	/*
	public JPanel getContentPane(){
		return contentPane;
	}
	*/
}
