package com.pentago.controller;

public class MoveResult {

	String newBoard;
	String winner;
	
	public String getNewBoard() {
		return newBoard;
	}
	public void setNewBoard(String newBoard) {
		this.newBoard = newBoard;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	public MoveResult()
	{
		
	}
	public MoveResult(String newBoard, String winner)
	{
		this.winner = winner;
		this.newBoard = newBoard;
	}
}
