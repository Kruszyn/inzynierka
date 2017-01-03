package gui;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

import db.DatabaseHandle;

import java.awt.Component;

public class MainWindow {

	private JFrame frame;
	private JTable citations;
	private JTable articles;
	private JTable publications;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		publications = new JTable();
		publications.setAlignmentY(Component.TOP_ALIGNMENT);
		DatabaseHandle db = new DatabaseHandle();
		
		ResultSet rs=null;
		try {
			rs = db.stt.executeQuery("SELECT * FROM publications");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	    // It creates and displays the table
	    JTable table = null;
		try {
			table = new JTable(buildTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	    // Closes the Connection

	    JOptionPane.showMessageDialog(null, new JScrollPane(table));

		frame.getContentPane().add(publications);
	
	}

	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		
	    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
	
	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
