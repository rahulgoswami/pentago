package com.pentago.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SaltHash {
	
	public SaltHash()
	{
		
	}
	public String getSalt()
	{
		System.out.println("in salt");
		byte[] salt = new byte[128];
		 
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(salt);
			//System.out.println("salt in bytes"+ salt );
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("salt in String"+ salt.toString() );
		return Arrays.toString(salt);
		
	}
	
	public String getSecurePassword(String password,String salt)
	{
		
		String generatedPassword = null;
		  // Create MessageDigest instance for MD5
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			 //add the salt
	        md.update(salt.getBytes());
	        //Add password bytes to digest
	        md.update(password.getBytes());
	        //Get the hash's bytes
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        //Get complete hashed password in hex format
	       generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generatedPassword;
	}

 }