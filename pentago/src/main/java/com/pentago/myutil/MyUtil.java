package com.pentago.myutil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.pentago.controller.Row;
import com.pentago.persistence.GameVO;

public class MyUtil {
	public static HttpSession getSession(){
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session;
	}
	
	public static void setSessionAttribute(String name,String value){
		HttpSession session=getSession();
		session.setAttribute(name, value);
	}
	
	public static Object getSessionAttribute(String name){
		HttpSession session=getSession();
		return session.getAttribute(name);
		
	}
	
	public static boolean updateStatistics(String username,String result){
		boolean a=true;
		return a;
	}
	
	public static List<Row> arrayToList(int arr[][]){
		List<Row> rows=new ArrayList<Row>();
		Row row;
		for(int i=0; i<6; i++){
			row= new Row(arr[i][0],arr[i][1],arr[i][2],arr[i][3],arr[i][4],arr[i][5]);
			rows.add(row);
		}
		return rows; 
	}  
	
	public static String listToString(List<Row> rows){
		String toString="";
		//Row row= new Row();
		for (Row row : rows) {
			toString=toString+row.col1+","+row.col2+","+row.col3+","+row.col4+","+row.col5+","+row.col6+";";
		}
		return toString;
	}
	
	public static List<Row> stringToList(String str){
		//String str="1,2,3,4,5,6;1,2,3,4,5,6;1,2,3,4,5,6;1,2,3,4,5,6;1,2,3,4,5,6;1,2,3,4,5,6;";
		List<Row> rows=new ArrayList<Row>();
		String[] R = str.split(";");
		for(int i=0;i<6;i++){
			//System.out.println("SSSSSSSSSSSSSS"+R[i]);
			String[] C = R[i].split(",");
			Row row=new Row(Integer.parseInt(C[0]),Integer.parseInt(C[1]),Integer.parseInt(C[2]),Integer.parseInt(C[3]),Integer.parseInt(C[4]),Integer.parseInt(C[5]));
			rows.add(row);
		}
		return rows;	
	}
	
	public static int[][] listToArray(List<Row> rows){
		int[][] arr=new int[6][6];
		Row row ;
		for(int i=0; i<6; i++){
			row = rows.get(i);
			arr[i][0]=row.getCol1();
			arr[i][1]=row.getCol2();
			arr[i][2]=row.getCol3();
			arr[i][3]=row.getCol4();
			arr[i][4]=row.getCol5();
			arr[i][5]=row.getCol6();
		}
		return arr;		
	}
}
