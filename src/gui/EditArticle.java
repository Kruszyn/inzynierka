package gui;

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

import db.DatabaseHandle;

public class EditArticle extends EditFrame {


	private static final long serialVersionUID = -5488964758369532955L;

	public EditArticle() {
		super();
		
		getContentPane().add(new JLabel("Edytowanie artyku³u"), "cell 0 0 2 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("Wybierz ID edytowanego artyku³u:");
		getContentPane().add(lblNewLabel, "cell 0 1");
		
		JSpinner spinnerIdArt = new JSpinner(createSpinnerModel("articles"));
		spinnerIdArt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				DatabaseHandle db = new DatabaseHandle();
				ResultSet rs = null;
				try {
					rs = db.stt.executeQuery("SELECT * FROM articles WHERE id=" +  spinnerIdArt.getValue().toString());
					if(rs.next()){
						authorsField.setText(rs.getObject(2).toString());
						titleField.setText(rs.getObject(3).toString());
						placeField.setText(rs.getObject(4).toString());
						pdateField.setText(rs.getObject(5).toString());
						pagesField.setText(rs.getObject(6).toString());
					}
					else{
						JOptionPane.showMessageDialog(new JFrame(),"Nie ma artyku³u o takim ID w bazie danych.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(spinnerIdArt, "flowx,cell 1 1");
		
		JButton editArticleButton = new JButton("Edytuj artyku³");
		editArticleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DatabaseHandle db = new DatabaseHandle();
				db.editArticle(spinnerIdArt.getValue().toString(), authorsField.getText(), titleField.getText(), placeField.getText(), pdateField.getText(), pagesField.getText());
				//TODO sprawdzenie poprawnoœci dodania

				JOptionPane.showMessageDialog(new JFrame(),"Edytowano artyku³.");

			}
		});
		getContentPane().add(editArticleButton, "cell 1 8,alignx leading");

		
	}

}
