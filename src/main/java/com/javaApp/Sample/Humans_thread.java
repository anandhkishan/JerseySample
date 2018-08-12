package com.javaApp.Sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
class data{
	long id;
	String name;
	long points;
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPoints() {
		return points;
	}
	public void setPoints(long points) {
		this.points = points;
	}
}
class Processor1 implements Runnable{
	private  data name;
	private Connection con;
	
	public Processor1(data name,Connection con)
	{
		this.name=name;
		this.con = con;
	}
	Random rand = new Random();
	int  n = rand.nextInt(5000);
	public void run()
	{
		System.out.println("Query execution for "+name.getName()+" is carried out in :   "+Thread.currentThread().getName());
		System.out.println();
		
		
//			System.out.println(Thread.currentThread().getName()+"thread is sleeping");
//			Thread.sleep(2);
			String sql = "insert into test values(?,?,?);";
			try {
				PreparedStatement st = con.prepareStatement(sql);
				st.setLong(1, name.getId());
				st.setString(2,name.getName());
				st.setLong(3, name.getPoints());

				st.executeUpdate();
				
				}
				catch(Exception e) {
					System.out.println("Statement3 error");
				}
			
		
		
		System.out.println("Information for "+name.getName()+" has been successfully saved in the database ");
		System.out.println();
	}
}
public class Humans_thread {
	public static Scanner scanner=new Scanner(System.in);
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
	  Connection con = null;
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
	ArrayList<data> array=new ArrayList<data>();
//	LOGGER.info("_______BANK_______");
//	LOGGER.info("Enter the number of Customer");
//	
//	int noCustomers=scanner.nextInt();
//	String[] array = {"nagesh","dinesh","anandh","naren"};
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader("testing222.json");
		JSONArray a = (JSONArray) parser.parse(file);

		  for (Object o : a)
		  {
		    JSONObject person = (JSONObject) o;

//		    array.add(((String) person.get("name")));
		    data dt = new data();
		  //  System.out.println((person.get("points")).getClass().toString());
		    dt.setId((Long)person.get("id"));
		    dt.setName(((String) person.get("name")));
		    dt.setPoints((Long)person.get("highScore"));
		    array.add(dt);
		   
		  }
		  file.close();

	System.out.println("Enter the number of threads needed:");
	int counters=scanner.nextInt();
//	for(int i=0;i<noCustomers;i++)
//	
//	{
//		LOGGER.info("Enter customer details");
//		String customer=scanner.next();
//		array.add(customer);
//    }
	
	ExecutorService executor= Executors.newFixedThreadPool(counters);
	long start = System.nanoTime();
	for(data s:array)
	{
		executor.submit(new Processor1(s,con));
	}
	executor.shutdown();
	
	System.out.println("All counters are full");
	try {
	executor.awaitTermination(1,TimeUnit.DAYS);
    }
	catch(InterruptedException e)
	{
		
	}
	System.out.println("All tasks are completed");
	long time = System.nanoTime() - start;
	double timeInSeconds = time/1e9;
	System.out.println( "Total time for execution:"+timeInSeconds);
}
}
