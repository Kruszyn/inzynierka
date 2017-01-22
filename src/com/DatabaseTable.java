package com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class DatabaseTable{

	private ResultSet rs;
	private JTable table; 
	
	public DatabaseTable(int x){
		DatabaseHandle db = new DatabaseHandle();
		String dbName = " ";
		
		if(x==0){
			dbName="articles";
		}else if(x==1){
			dbName="citations";
		}
		
		try {
			rs = db.stt.executeQuery("SELECT * FROM " + dbName);
			DefaultTableModel tableModel = buildTableModel();
			table = new JTable(tableModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	private DefaultTableModel buildTableModel() throws SQLException {
		
	    ResultSetMetaData metaData = (ResultSetMetaData) this.rs.getMetaData();

	    
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    
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

	
	public JTable getTable() {
		return table;
	}


}
