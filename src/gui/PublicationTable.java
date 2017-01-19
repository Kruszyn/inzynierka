package gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

import db.DatabaseHandle;

public class PublicationTable  {
	
	private ResultSet rs;
	private JTable table;

	public PublicationTable() {
		DatabaseHandle db = new DatabaseHandle();
		
		try {
			this.rs = db.stt.executeQuery("SELECT * FROM publications");
			DefaultTableModel tableModel = buildTableModel();
			table = new JTable(tableModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private DefaultTableModel buildTableModel() throws SQLException {
		
	    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

	    
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= (columnCount-2); column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= (columnCount-2); columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	}

	public JTable getTable() {
		return table;
	}

}
