package com.pentago.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class NewGameBean {
	
	private String move;
	private int x;
	private int y;
	private boolean clockwise;
	private List<Integer> quadrants;
	private List<Row> rows;
	Row row; 
	Row row1;
	private int quadrant;
	private String statusMsg;
	
	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(int quadrant) {
		this.quadrant = quadrant;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean getClockwise() {
		return clockwise;
	}

	public void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}

	public List<Integer> getQuadrants() {
		return quadrants;
	}

	public void setQuadrant(List<Integer> quadrants) {
		this.quadrants = quadrants;
	}

	public NewGameBean(){
		rows= new ArrayList<Row>();
		 row = new Row(1,-1,1,1,0,-1);
		 row1 = new Row(1,-1,1,1,0,-1);
		 quadrants=new ArrayList<Integer>();
		 quadrants.add(1);
		 quadrants.add(2);
		 quadrants.add(3);
		 quadrants.add(4);
		 
		 statusMsg = "Waiting for Player2...";
		 	rows.add(row);
			rows.add(row);
			rows.add(row);
			rows.add(row);
			rows.add(row);
			rows.add(row);
		
	}
	
	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
	
	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}
	
	public void submit(){
		System.out.println("HHHHHHHHHHHHHHHH:"+clockwise);
		System.out.println("HHHHHHHHHHHHHHHH:"+quadrant);
		System.out.println("HHHHHHHHHHHHHHHHXXXXXXXX:"+x);
		System.out.println("HHHHHHHHHHHHHHHHYYYYYYYYYY:"+y);
	}
	
}
