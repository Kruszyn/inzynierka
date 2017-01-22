package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseHandle {

	
	String url;
    public Statement stt = null;
	
	public DatabaseHandle(){	
		this.url = "jdbc:mysql://localhost:3306/inzynierka?useUnicode=true&characterEncoding=UTF-8";
	
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection(url, "root", "");
	        this.stt = con.createStatement();
	       // stt.execute("SET NAMES 'UTF8'");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace(); 
			throw new RuntimeException(e);
		}
	}
	
	public void addArticle(String title, String authors, String place, String Pdate, String pages){
		 
		String sql = "INSERT INTO articles(authors, title, place, Pdate, pages) Values( "
					+ "'" + authors + "', '" + title + "', '" + place + "', '" + Pdate + "', '" + pages + "')";
		
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void addPublication(String title, String authors, String place, String Pdate, String pages){
		
		String sql = "INSERT INTO publications(authors, title, place, Pdate, pages) Values( "
					+ "'" + authors + "', '" + title + "', '" + place + "', '" + Pdate + "', '" + pages  + "')";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void addCitation(String artCited, String artQuot, String gs, String wos){
	
		String sql = "INSERT INTO citations(artcytowany, artcytujacy, GoogleSchoolar, WebOfScience) Values( "
				+ "'" + artCited + "', '" + artQuot + "', '" + gs + "', '" + wos + "')";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}
	
	public void deleteArticle(String id){
	
		String sql = "DELETE FROM articles WHERE id = '" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void deletePublication(String id){
		
		String sql = "DELETE FROM publications WHERE id = '" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	
	}
	
	public void deleteCitation(String id){
		
		String sql = "DELETE FROM citations WHERE id = '" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void editCitation(String id, String artCited, String artQuot, String gs, String wos){
		
		
		String sql = "UPDATE citations SET artcytowany='" + artCited + "', artcytujacy='" + artQuot + "', GoogleSchoolar='"
				+ gs + "', WebOfScience='" + wos + "' WHERE id ='" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public void editPublication(String id, String authors, String title, String place, String pdate, String pages, String gs, String wos){
		
		
		String sql = "UPDATE publications SET authors='" + authors + "', title='" + title + "', place='" + place + "',"
				+ " Pdate='" + pdate + "', pages='" + pages + "', google_schoolar=" + gs + ", web_science=" + wos + " WHERE id ='" + id + "'";
		try {
			stt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public void editArticle(String id, String authors, String title, String place, String pdate, String pages){
		
		
		String sql = "UPDATE articles SET authors='" + authors + "', title='" + title + "', place='" + place + "', "
				+ "Pdate='"+ pdate + "', pages='" + pages + "' WHERE id ='" + id + "'";
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
