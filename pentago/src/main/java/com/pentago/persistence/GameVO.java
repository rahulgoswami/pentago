package com.pentago.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GameVO implements Serializable {
	private static final long	serialVersionUID	= 13423235;

	@Id
	@GeneratedValue
	private long				id;

	@NotNull
	private String				player1;

	private String				player2;

	private String				winner;
	
	@Size(min = 0, max = 300)
	private String				gameMoves;

	@NotNull
	private String				gameStatus;

	private String 				currentPlayer;
	private String 				currentBoardStatus;
	
	/*
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				created				= new Date();

	@PrePersist
	private void onCreate() {
		created = new Date();
	}
	
*/	

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getGameMoves() {
		return gameMoves;
	}

	public void setGameMoves(String gameMoves) {
		this.gameMoves = gameMoves;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String getCurrentBoardStatus() {
		return currentBoardStatus;

	}

	public void setCurrentBoardStatus(String currentBoardStatus) {
		this.currentBoardStatus = currentBoardStatus;


	}
	
	public String getCurrentPlayer() {
		return currentPlayer;
	}



	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
}