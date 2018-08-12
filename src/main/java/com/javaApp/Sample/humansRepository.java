package com.javaApp.Sample;
import java.sql.*;

import java.util.List;
import java.util.ArrayList;

public class humansRepository {
	List<Humans> humans;
	Connection con = null;
	public humansRepository() {
//		humans = new ArrayList<Humans>();
//		Humans h1 = new Humans();
//		h1.setId(101);
//		h1.setName("Anandh Kishan");
//		h1.setPoints(22);
//		Humans h2 = new Humans();
//		h2.setId(102);
//		h2.setName("Nageshwara Sairam");
//		h2.setPoints(21);
//		Humans h3 = new Humans();
//		h3.setId(103);
//		h3.setName("Dinesh Kumar");
//		h3.setPoints(22);
//		humans.add(h1);
//		humans.add(h2);
//		humans.add(h3);
		String url = "jdbc:postgresql://localhost/sample";
		String username = "anandhk";
		String password = "jskanandh0606";
		try {
//		Class.forName("org.postgresql.Driver").newInstance();
			 try {
		         // dynamically load the Hive JDBC driver
		         Class.forName("org.postgresql.Driver").newInstance();
		      } catch (ClassNotFoundException e) {
		         System.out.println("there is an error in class");
		      }
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			System.out.println("Error captured");
		}
		
	}
	public List<Humans> getHumans() {
		List<Humans> humans = new ArrayList<Humans>();
		String sql = "select * from humans";
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			Humans h = new Humans();
			h.setId(rs.getInt(1));
			h.setName(rs.getString(2));
			h.setHighscore(rs.getInt(3));
			humans.add(h);
		}
		}
		catch(SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		
		}
		return humans;
	}
	public Humans getHuman(int id) {
		String sql = "select * from humans where id="+id;
		Humans h = new Humans();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next())
		{
			
			h.setId(rs.getInt(1));
			h.setName(rs.getString(2));
			h.setHighscore(rs.getInt(3));
			
		}
		}
		catch(Exception e) {
			System.out.println("Statement2 error");
		}
		return h;
	}
	
	public void update(Humans h1) {
		// TODO Auto-generated method stub
		String sql = "update humans set name=?,highscore=? where id=?;";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,h1.getName());
			st.setInt(2, h1.getHighscore());
			st.setInt(3, h1.getId());
			st.executeUpdate();
			
			}
			catch(Exception e) {
				System.out.println("Statement3 error");
			}
		
	}
	public void create(Humans h1) {
		// TODO Auto-generated method stub
		String sql = "insert into humans values(?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,h1.getId());
			st.setString(2, h1.getName());
			st.setInt(3, h1.getHighscore());
			st.executeUpdate();
			
			}
			catch(Exception e) {
				System.out.println("Statement3 error");
			}
		
	}
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from humans where id =?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1,id);
			st.executeUpdate();
			
			}
			catch(Exception e) {
				System.out.println("Statement3 error");
			}
		
	}
	
}
