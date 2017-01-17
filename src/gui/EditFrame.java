package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db.DatabaseHandle;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSpinner;

public class EditFrame extends JFrame {

	private JPanel contentPane;
	private JTextField authorsField;
	private JTextField titleField;
	private JTextField placeField;
	private JTextField pdateField;
	private JTextField pagesField;
	private JTextField categoryField;

	public EditFrame() {

		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		
		contentPane.add(new JLabel("Edytowanie publikacji/artyku³u"), "cell 0 0 2 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("Wybierz ID edytowanej publikacji:");
		contentPane.add(lblNewLabel, "cell 0 1");
		
		JSpinner spinnerId = new JSpinner(createSpinerModel("publications"));
		
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
						categoryField.setText(rs.getObject(7).toString());
					}
					else{
						JOptionPane.showMessageDialog(new JFrame(),"Nie ma publikacji o takim ID w bazie danych.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(spinnerId, "flowx,cell 1 1");
		
		
		JLabel lblNewLabel_1 = new JLabel("lub artyku³u:");
		contentPane.add(lblNewLabel_1, "cell 1 1");
		
		JSpinner spinnerIdArt = new JSpinner(createSpinerModel("articles"));
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
						categoryField.setText(rs.getObject(7).toString());
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
		contentPane.add(spinnerIdArt, "cell 1 1,alignx right");

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
		
		JLabel categoryLabel = new JLabel("Kategoria:");
		contentPane.add(categoryLabel, "cell 0 7,alignx left");
		
		categoryField = new JTextField();
		contentPane.add(categoryField, "cell 1 7,growx");
		categoryField.setColumns(10);
		
		JButton editPublicationButton = new JButton("Edytuj publikacjê");
		editPublicationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//	public void addArticle(String title, String authors, String place, String Pdate, String pages, int category){
				DatabaseHandle db = new DatabaseHandle();
				db.editPublication(spinnerId.getValue().toString(), authorsField.getText(), titleField.getText(), placeField.getText(), pdateField.getText(), pagesField.getText(), categoryField.getText());
				//TODO sprawdzenie poprawnoœci dodania

				JOptionPane.showMessageDialog(new JFrame(),"Edytowano publikacjê.");

			}
		});
		contentPane.add(editPublicationButton, "flowx,cell 1 8,alignx center");
		
		JButton editArticleButton = new JButton("Edytuj artyku³");
		editArticleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DatabaseHandle db = new DatabaseHandle();
				db.editArticle(spinnerIdArt.getValue().toString(), authorsField.getText(), titleField.getText(), placeField.getText(), pdateField.getText(), pagesField.getText(), categoryField.getText());
				//TODO sprawdzenie poprawnoœci dodania

				JOptionPane.showMessageDialog(new JFrame(),"Edytowano artyku³.");

			}
		});
		contentPane.add(editArticleButton, "cell 1 8,alignx leading");

		
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

}
