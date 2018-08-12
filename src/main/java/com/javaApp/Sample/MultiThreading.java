package com.javaApp.Sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class MultiThreading {
	Connection con = null;
		public MultiThreading(){
			
			String url = "jdbc:postgresql://localhost/sample";
			String username = "anandhk";
			String password = "jskanandh0606";
			try {
//			Class.forName("org.postgresql.Driver").newInstance();
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
		public void DBConnect(Humans[] a){
			ExecutorService executor= Executors.newFixedThreadPool(100);
			long start = System.nanoTime();
			for(Humans s:a)
			{
				executor.submit( new Processor(s,con));
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
//			System.out.println( "Total time for execution:"+timeInSeconds);
		}
}
