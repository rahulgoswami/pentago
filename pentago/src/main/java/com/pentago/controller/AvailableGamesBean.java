package com.pentago.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

import com.pentago.ejb.GameManagerEJB;
import com.pentago.ejb.PlayerInfoManagerEJB;
import com.pentago.myutil.MyUtil;
import com.pentago.persistence.GameVO;
import com.pentago.persistence.PlayerVO;

@Named(value="availableGamesBean")
@ApplicationScoped
public class AvailableGamesBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8197471435603860294L;

	/**
	 * 
	 */
	

	@Inject 
	private GameManagerEJB gameEJBApp;
	
	private List<String> players;
	
	private String playerSelected;
	
	private String message;
	
	private List<GameVO> gameLogs;
	
	public List<GameVO> getGameLogs() {
		return gameLogs;
	}

	public void setGameLogs(List<GameVO> gameLogs) {
		this.gameLogs = gameLogs;
	}


	public String getPlayerSelected() {
		return playerSelected;
	}

	public void setPlayerSelected(String playerSelected) {
		this.playerSelected = playerSelected;
	}

	public List<String> getPlayers() {
		return players;
	}

	public void setPlayers(List<String> players) {
		this.players = players;
	}

	
	public AvailableGamesBean(){
		 players=new ArrayList<String>();
		 System.out.println("=========HEY I AM in AvailableGames!!!!!!!========");
	 }
	
	public String populateAvailableGames(){
		System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPP=========HEY I AM in AvailableGames!!!!!!!========");
		players = gameEJBApp.returnAvailablePlayers();
		return "/secured/availGames"; 
	}
	public String populateGameLogs(){
		gameLogs=gameEJBApp.getGameLogs();
		return "/secured/gameLogs";
	}
	/*

	public void populateAvailablePlayers(){
		System.out.println("GGGGGGGGGGGGGGG");
		players = gameEJBApp.returnAvailablePlayers();
	}
	*/
	
	/*public void submitL(){
		msg="NNNNNNNNOOOO";
		message="UUUUUUUUU";
	}*/
	
	public void submit(){
		//msg="HHHHHHHh";
		if(playerSelected.equals("") && !gameEJBApp.returnAvailablePlayers().contains(playerSelected)){
			System.out.println("BBBBBBBBBBBBBBBBB");
			message="Player unavailable.Please select another player";
		}
		else if(gameEJBApp.returnAvailablePlayers().contains(playerSelected)){
			message="";
		}
		System.out.println("BEAAAAAAAAANNNNNNNNNN"+playerSelected);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
