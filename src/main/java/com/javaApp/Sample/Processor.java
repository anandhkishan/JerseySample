package com.javaApp.Sample;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Processor implements Runnable{
	private  Humans name;
	private Connection con;
	
	public Processor(Humans name,Connection con)
	{
		this.name=name;
		this.con = con;
	}
	public void run()
	{
		System.out.println("Query execution for "+name.getName()+" is carried out in :   "+Thread.currentThread().getName());
		System.out.println();
		
		
//			System.out.println(Thread.currentThread().getName()+"thread is sleeping");
//			Thread.sleep(2);
			String sql = "insert into humans values(?,?,?);";
			try {
				PreparedStatement st = con.prepareStatement(sql);
				st.setLong(1, name.getId());
				st.setString(2,name.getName());
				st.setLong(3, name.getHighscore());

				st.executeUpdate();
				
				}
				catch(Exception e) {
					System.out.println("Statement3 error");
				}
			
		
		
		System.out.println("Information for "+name.getName()+" has been successfully saved in the database ");
		System.out.println();
	}
}
