package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseHandle {

	
	String url;
    public Statement stt = null;
	
	public DatabaseHandle(){	
		this.url = "jdbc:mysql://localhost:3306/inzynierka";
	
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection(url, "root", "");
	        this.stt = con.createStatement();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace(); 
			throw new RuntimeException(e);
		}
	}
	
	public void addArticle(String title, String authors, String place, String Pdate, String pages, String category){
		 
		String sql = "INSERT INTO articles(authors, title, place, Pdate, pages, category) Values( "
					+ "'" + authors + "', '" + title + "', '" + place + "', '" + Pdate + "', '" + pages + "', '" + category +"')";
		
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void addPublication(String title, String authors, String place, String Pdate, String pages, String category){
		
		String sql = "INSERT INTO publications(authors, title, place, Pdate, pages, category) Values( "
					+ "'" + authors + "', '" + title + "', '" + place + "', '" + Pdate + "', '" + pages  + "', '" + category + "')";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void addCitation(String artCited, String artQuot, String gs, String wos){
	
		String sql = "INSERT INTO citations(artcytowany, artcytujacy, gs, wos) Values( "
				+ "'" + artCited + "', '" + artQuot + "', '" + gs + "', '" + wos + "')";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}
	
	public void deleteArticle(int id){
	
		String sql = "DELETE FROM articles WHERE id = '" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void deletePublication(int id){
		
		String sql = "DELETE FROM publications WHERE id = '" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}
	
	public void deleteCitation(int id){
		
		String sql = "DELETE FROM citations WHERE id = '" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void editCitation(int id, String artCited, String artQuot, String gs, String wos){
		
		
		String sql = "UPDATE citations SET artcytowany='" + artCited + "', artcytujacy='" + artQuot + "', gs='" + gs + "', wos='" + wos + "' WHERE id ='" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/*
	  
	public static void main(String[] args){
		
		DatabaseHandle db = new DatabaseHandle();
		//db.addArticle(0,"ARTICLE TITLE", "AUTHORS", "place", "Pdate", "pages", 4);
		//db.deletePublication(855);
		//db.addCitation("1", "2", "b/d", "przyjete");
		//db.deleteCitation(2);
		db.editCitation(1, "188", "2", "przyjety", "adssa");
	}
	
	*/
}
