package gui;

//TODO funkcja String[] getValues() zwracaj¹ca w tablicy wszystkie wartoœci z pól

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import db.DatabaseHandle;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame{

	private static final long serialVersionUID = -8209693979309819481L;
	private JPanel contentPane;
	private JTextField authorsField;
	private JTextField titleField;;
	private JTextField placeField;
	private JTextField pdateField;
	private JTextField pagesField;
	private JTextField categoryField;
	private JButton addArticleButton;
	private JButton addPublicationButton;

	public AddFrame() {

		contentPane = new JPanel();
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
		
		contentPane.add(new JLabel("Dodawanie publikacji/artyku\u0142u"), "cell 0 0 2 1,alignx center");

		JLabel authorsLabel = new JLabel("Autor/Autorzy:");
		contentPane.add(authorsLabel, "cell 0 1,alignx left");
		
		authorsField = new JTextField();
		contentPane.add(authorsField, "cell 1 1,growx");
		authorsField.setColumns(10);
		
		JLabel titleLabel = new JLabel("Tytu\u0142:");
		contentPane.add(titleLabel, "cell 0 2,alignx left");
		
		titleField = new JTextField();
		contentPane.add(titleField, "cell 1 2,growx");
		titleField.setColumns(10);
		
		JLabel placeLabel = new JLabel("Miejsce:");
		contentPane.add(placeLabel, "cell 0 3,alignx left");
		
		placeField = new JTextField();
		contentPane.add(placeField, "cell 1 3,growx");
		placeField.setColumns(10);
		
		JLabel pdateLabel = new JLabel("Data:");
		contentPane.add(pdateLabel, "cell 0 4,alignx left");
		
		pdateField = new JTextField();
		contentPane.add(pdateField, "cell 1 4,growx");
		pdateField.setColumns(10);
		
		JLabel pagesLabel = new JLabel("Strony:");
		contentPane.add(pagesLabel, "cell 0 5,alignx left");
		
		pagesField = new JTextField();
		contentPane.add(pagesField, "cell 1 5,growx");
		pagesField.setColumns(10);
		
		addPublicationButton = new JButton("Dodaj publikacjê");
		addPublicationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseHandle db = new DatabaseHandle();
				db.addPublication(titleField.getText(), authorsField.getText(), placeField.getText(), pdateField.getText(), pagesField.getText());
				//TODO sprawdzenie poprawnoœci dodania
				clearFields();
				JOptionPane.showMessageDialog(new JFrame(),"Dodano publikacjê.");

			}
		});
		contentPane.add(addPublicationButton, "flowx,cell 1 7,alignx center");
		
		addArticleButton = new JButton("Dodaj artyku³");
		addArticleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DatabaseHandle db = new DatabaseHandle();
				db.addArticle(titleField.getText(), authorsField.getText(), placeField.getText(), pdateField.getText(), pagesField.getText());
				//TODO sprawdzenie poprawnoœci dodania
				clearFields();
				JOptionPane.showMessageDialog(new JFrame(),"Dodano artyku³.");

			}
		});
		contentPane.add(addArticleButton, "cell 1 7,alignx leading");

		
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	private void clearFields() {
		titleField.setText("");
		authorsField.setText("");
		placeField.setText("");
		pdateField.setText("");
		pagesField.setText("");
	}
	
}
