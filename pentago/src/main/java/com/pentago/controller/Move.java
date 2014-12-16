package com.pentago.controller;

public class Move {
	private int row;
	private int col;
	private int grid=1;
	private boolean clock;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getGrid() {
		return grid;
	}
	public void setGrid(int grid) {
		this.grid = grid;
	}
	public boolean isClock() {
		return clock;
	}
	public void setClock(boolean clock) {
		this.clock = clock;
	}
	public String toString(){
		String str="";
		str+="("+row+","+col+","+grid+","+(clock==true?"C":"AC")+")";
		return str;
	}
}