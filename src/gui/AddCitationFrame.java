package gui;

import java.awt.EventQueue;
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

import javax.swing.JList;
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
		contentPane.add(spinnerCited, "cell 1 1");
		
		
		contentPane.add(new JLabel("ID artyku³u cytuj¹cego"), "cell 0 2");	
		JSpinner spinnerQuot = new JSpinner(createSpinerModel("articles"));
		contentPane.add(spinnerQuot, "cell 1 2");
		
		String[] options = {"brak danych","uznane","zg³oszone","nie uznane"};
		JList<String> list = new JList<String>(options);
		
		contentPane.add(new JLabel("New label"), "cell 0 3");		
		contentPane.add(list, "cell 1 3,grow");
		
		JList<String> list1 = new JList<String>(options);

		contentPane.add(new JLabel("New label"), "cell 0 4");
		contentPane.add(list1, "cell 1 4,grow");
		
		JButton addCitationButton = new JButton("Dodaj publikacje");
		addCitationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
