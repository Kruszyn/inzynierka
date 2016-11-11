package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandle {

	
	String url;
    public Statement stt = null;
	
	public DatabaseHandle(String user, String password){	
		this.url = "jdbc:mysql://localhost:3306/inzynierka";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection(url, user, password);
	        this.stt = con.createStatement();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace(); 
			throw new RuntimeException(e);
		}
	}
	
	public void addArticle(String title, String authors){
		String sql = "INSERT INTO articles(title, authors) Values( '" +
				title + "', '" + authors + "')";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	void displayDatabase(){
		ResultSet res = null;
		try {
			res = this.stt.executeQuery("SELECT * FROM articles");
			while (res.next())
			{
			    System.out.println(res.getString("id") + "." + res.getString("title") + " " +
			    		res.getString("authors") );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		DatabaseHandle db = new DatabaseHandle("root","");
		db.addArticle("tytu³ artyku³u", "autorzy artyku³u");
		db.displayDatabase();
        
	}
	
}
