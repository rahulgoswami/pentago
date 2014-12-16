package com.pentago.controller;

public  class Row{
	public int col1,col2,col3,col4,col5,col6;
	
	public Row(int col1, int col2, int col3, int col4, int col5, int col6){
		this.col1=col1;  
		this.col2=col2;
		this.col3=col3;
		this.col4=col4; 
		this.col5=col5; 
		this.col6=col6;
	}
	
	public Row(){
		
	}
	public int getCol1() {
		return col1;
	}
	public void setCol1(int col1) {
		this.col1 = col1;
	}
	public int getCol2() {
		return col2;
	}
	public void setCol2(int col2) {
		this.col2 = col2;
	}
	public int getCol3() {
		return col3;
	}
	public void setCol3(int col3) {
		this.col3 = col3;
	}
	public int getCol4() {
		return col4;
	}
	public void setCol4(int col4) {
		this.col4 = col4;
	}
	public int getCol5() {
		return col5;
	}
	public void setCol5(int col5) {
		this.col5 = col5;
	}
	public int getCol6() {
		return col6;
	}
	public void setCol6(int col6) {
		this.col6 = col6;
	}
	
}