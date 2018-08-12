package com.javaApp.Sample;

//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement
//public class databaseObj {
//	private int id;
//	private String name;
//	private int highScore;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getHighScore() {
//		return highScore;
//	}
//	public void setHighScore(int highScore) {
//		this.highScore = highScore;
//	}
//	
//	
//}

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class  databaseObj{
// AlienRepository ar = new AlienRepository();
 
 private int id;
 private String name;
 private int points;

 
 public databaseObj() {}

public void setId(int i) {
this.id = i;
}

 public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public int getPoints() {
return points;
}
public void setPoints(int i) {
this.points = i;
}
public int getId() {
// TODO Auto-generated method stub
return   id;
}
@Override
public String toString() {
return "databaseObj [id=" + id + ", name=" + name + ", points=" + points + "]";
}


}