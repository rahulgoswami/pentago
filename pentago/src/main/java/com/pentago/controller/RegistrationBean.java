package com.pentago.controller;

import java.io.Serializable;


//import javax.faces.bean.RequestScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import com.pentago.ejb.PlayerInfoManagerEJB;
import com.pentago.persistence.PlayerVO;


//import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "registrationBean")
@RequestScoped

public class RegistrationBean implements Serializable
{
	@Inject 
	private PlayerInfoManagerEJB playerEJB;
	private SaltHash passwordtohash = new SaltHash();
	private PlayerVO playerInfo;
	
	
	public RegistrationBean(){
		 System.out.println("=========HEY I AM HERE!!!!!!!========");
	 }
	
	
	public PlayerVO getPlayerInfo() {
		if(playerInfo==null){
			playerInfo=new PlayerVO();
		}
		return playerInfo;
	}

	public void setPlayerInfo(PlayerVO playerInfo) {
		this.playerInfo = playerInfo;
	}

	 	  
	 public String savePlayerInfo(){
		
		 String salt = passwordtohash.getSalt();
		 String pwd = passwordtohash.getSecurePassword(playerInfo.getPassword(), salt);
		 playerInfo.setSalt(salt);
		 playerInfo.setPassword(pwd);
		 System.out.println("Length of Salt is "+salt.length());
		 System.out.println("Length of password "+pwd.length());
		 String status=playerEJB.savePlayerInfo(playerInfo);
		 if(status=="success")
			 return "index";
	     else
	    	 return "registration";

				 
		 
	 }
}