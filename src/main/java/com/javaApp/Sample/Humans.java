package com.javaApp.Sample;

import javax.xml.bind.annotation.XmlRootElement;

//import org.codehaus.jackson.map.annotate.JsonRootName;
//@JsonRootName(value="humans")
@XmlRootElement
public class Humans {
	private int id;
	private String name;
	private int highscore;
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHighscore() {
		return highscore;
	}
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
	@Override
	public String toString() {
		return "Humans [name=" + name + ", points=" + highscore + "]";
	}
	
	 
}
