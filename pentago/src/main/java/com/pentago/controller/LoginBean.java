package com.pentago.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import com.pentago.ejb.PlayerInfoManagerEJB;
import com.pentago.myutil.MyUtil;
import com.pentago.persistence.PlayerVO;
import com.pentago.persistence.StatisticsVO;


//import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import java.awt.List;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6812744870157853093L;
	private String userName;
	private String password;
	private String message;
	
	
	private boolean loggedIn;
	
	@Inject 
	private PlayerInfoManagerEJB playerEJB;
	private SaltHash passwordtohash = new SaltHash();
	
	/*@PostConstruct
	public void postConstruct() {
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	}*/
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getLoggedIn() {
	    return loggedIn;
	}
	 
	public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	}
	
	public String validateLogin(){
		System.out.println("========in validateLogin=======");
		String stored_pwd = null,hashed_pwd=null;
		String stored_salt=null;
		ArrayList<PlayerVO> results = playerEJB.loginCheck(userName,password);	
		System.out.println("========retrieved=======");
		if(results==null){
			results= new ArrayList<PlayerVO>();
		}
		if(results.size()!=0){
			System.out.println("========retrieved=======11111111");
			for(PlayerVO player : results)
			{
				stored_pwd = player.getPassword();
				System.out.println("========stored pwd======" +stored_pwd);
				stored_salt = player.getSalt();
				System.out.println("========stored salt=======" + stored_salt);
			}
			hashed_pwd = passwordtohash.getSecurePassword(this.password, stored_salt);
			System.out.println("========hashed pwd=======" + hashed_pwd);
			if(hashed_pwd.equals(stored_pwd))
			{
				System.out.println("========are equal!!=======" );
				MyUtil.setSessionAttribute("userName", userName);
				loggedIn=true;
				//session.setAttribute("userName", userName);
				return "/secured/postLogin";
			}
			System.out.println("========are not equal!!=======" );
			message="Invalid Username or Password.";
			return "index";
		}
		else
			message="Invalid Username or Password.";
			return "index";
	}
	
	public void doLogout() throws IOException{
		System.out.println("in logout");
		
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		//System.out.println("session is :"+session);
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");  
	}
	
	/*public String logout(){
		loggedIn=false;
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "index";
	}*/
}
