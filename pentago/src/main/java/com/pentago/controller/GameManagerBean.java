package com.pentago.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pentago.config.Configuration;
import com.pentago.ejb.GameManagerEJB;
import com.pentago.ejb.PlayerInfoManagerEJB;
import com.pentago.myutil.MyUtil;
import com.pentago.persistence.GameVO;
import com.pentago.persistence.PlayerVO;
import com.pentago.persistence.StatisticsVO;

@Named(value="gameManagerBean")
@SessionScoped
public class GameManagerBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2817178807569558355L;

	@Inject 
	private GameManagerEJB gameEJB;
	
	private String player1UserName;
	private String player2UserName;
	private Move nextMove;
	private List<StatisticsVO> statistics;
	private List<Integer> grids;
	private String statusMsg;
	private List<Row> rows;
	private long gameId;
	private String userName;
	private List<String> allPlayers;
	private long timer;
	private String timerMsg;
	//	private GameVO gameInfo;
	

	public String getTimerMsg() {
		return timerMsg;
	}

	public void setTimerMsg(String timerMsg) {
		this.timerMsg = timerMsg;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public List<Integer> getGrids() {
		return grids;
	}

	public void setGrids(List<Integer> grids) {
		this.grids = grids;
	}

	public Move getNextMove() {
		return nextMove;
	}

	public void setNextMove(Move nextMove) {
		this.nextMove = nextMove;
	}
	public List<StatisticsVO> getStatistics() {
		return statistics;
	}
	
	public void setStatistics(List<StatisticsVO> statistics) {
		this.statistics = statistics;
	}
	
	@PostConstruct
    public void init() {
		System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIi");
		statistics= gameEJB.findStatistics();
    }
	
	public void populateStatistics(){
		System.out.println("IIIIIIIIIIIIIIIIITTTTTTTTTTTTTTTTTTTTTTT");
		statistics= gameEJB.findStatistics();
	}
	
	public GameManagerBean(){
		nextMove=new Move();
		statistics = new ArrayList<StatisticsVO>();
		grids=new ArrayList<Integer>();
		statusMsg="";//"Waiting for other Player to join...";
		 grids.add(1);
		 grids.add(2);
		 grids.add(3);
		 grids.add(4);
		 userName=(String) MyUtil.getSessionAttribute("userName");
		 System.out.println("=========HEY I AM INSIDE GameManager !!!!!!!========");
	 }
	
	public String getPlayer2UserName() {
		return player2UserName;
	}

	/*public void checkForPlayer2(){ //submit()
		System.out.println("====checkForPlayer2() is getting called==========");
		GameVO game=getCurrentGameById();
		
		if(game.getPlayer2()!=null && game.getGameStatus()!="finished")
		{
			//System.out.println("====INSIDE checkForPlayer2============"+game.getPlayer2());
			player2UserName=game.getPlayer2();
			statusMsg= player2UserName+" has joined the game. Please make your move.";
			if(game.getCurrentPlayer()!=null ){
				//System.out.println("====INSIDE checkForPlayer2============"+game.getCurrentPlayer());
				statusMsg=game.getCurrentPlayer()+"'s turn";
				if(game.getCurrentBoardStatus()!=null){
					System.out.println("the value retrieved from board is:" +game.getCurrentBoardStatus());
					rows= MyUtil.stringToList(game.getCurrentBoardStatus());
					return;
				}
			}
			else{
				statusMsg="Sorry! There was an error!";
				return;
			}
		}
		else if(game.getGameStatus()=="finished"){
			
			statusMsg=player2UserName+"has forfeited. YOU WIN!";
			return;
		}
		else{
			rows= MyUtil.stringToList("0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;");
			statusMsg="You cannot play yet. Waiting for other player to join...";
			return;
		}
		//System.out.println("HHHHHHHHHHHHHHHHXXXXXXXX:"+ nextMove.getRow());
		//System.out.println("HHHHHHHHHHHHHHHHYYYYYYYYY:"+ nextMove.getCol());
	}
	*/
	
	public void checkForPlayer2(){ //submit()
		
		System.out.println("====INSIDE checkForPlayer2============");
		GameVO game=getCurrentGameById();
		if(game.getPlayer2()!=null && !game.getGameStatus().equals(Configuration.GAME_STATUS_FINISHED))
		{
			//System.out.println("====INSIDE checkForPlayer2============"+game.getPlayer2());
			player2UserName=game.getPlayer2();
			statusMsg= player2UserName+" has joined the game. Please make your move.";
			if(game.getCurrentPlayer()!=null ){
				//System.out.println("====INSIDE checkForPlayer2============"+game.getCurrentPlayer());
				statusMsg=game.getCurrentPlayer()+"'s turn";
				if(game.getCurrentBoardStatus()!=null){
					rows= MyUtil.stringToList(game.getCurrentBoardStatus());
					return;
				}
			}
			else{
				statusMsg="Sorry! There was an error!";
				return;
			}
		}
		else if(game.getGameStatus().equals(Configuration.GAME_STATUS_FINISHED)){
			System.out.println("HHHHHHHHHHHHHHHHXXXXXXXX:");
			rows=  MyUtil.stringToList(game.getCurrentBoardStatus());
			statusMsg=game.getWinner()+" has WON!";
			return;
		}
		else{
			rows= MyUtil.stringToList("0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;");
			statusMsg="Waiting for other player to join...";
			return;
		}
		//System.out.println("HHHHHHHHHHHHHHHHXXXXXXXX:"+ nextMove.getRow());
		//System.out.println("HHHHHHHHHHHHHHHHYYYYYYYYY:"+ nextMove.getCol());
	}
	
	
	
	public void displayInfo(String infoMsg){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", infoMsg));
	}
	
	/*this method is used for checking the time */
	public void checkTimer()
	{
		System.out.println("=======in checkTimer=======");
		GameVO game = getCurrentGameById();
		long time_diff = System.currentTimeMillis() - game.getLastMoveTimeStamp();
		System.out.println("=======time diff is======"+time_diff+"game object is+"+game.getPlayer1());
		if(game.getCurrentPlayer().equals(getOtherPlayerName()))
		{
			 if(time_diff < 900000){
				 timerMsg = "Time left to make a move";
			 }
			 else{
				 updateAfterTimer();
			 }
				 
			//displayInfo("all is well");
		}
	/*	else{
			if(time_diff < 900000){
				timerMsg = "Your timer has been set";
			 }
			else
				updateAfterTimer();
			//displayInfo("Your timer has been set");
		}
	*/	
		//return "";
	}
	
	public void updateAfterTimer()
	{
		String loser=getOtherPlayerName();
		
		String winner=getMyUserName();
		
		GameVO gameInfo=getMyNewOrCurrentGame();
		gameInfo.setGameStatus(Configuration.GAME_STATUS_FINISHED);
		gameInfo.setWinner(winner);
		gameInfo.setCurrentPlayer(getOtherPlayerName());
		gameEJB.updateCurrentGameState(gameInfo);
		gameEJB.updateStatistics(winner,Configuration.STATISTICS_RESULT_WIN);
		gameEJB.updateStatistics(loser,Configuration.STATISTICS_RESULT_LOSS);
		statusMsg = loser+" ran out of time. You have won the game!!";
		displayInfo("The game has ended! Please exit to continue");
		//return "postLogin";
		
	}
	
	public void playMove(){
		/*	String newBoard=	GameEngine.playMove(gameInfo.getBoard(),gameVO.getMove());
		
		 */

//		String winner="none";
		
		System.out.println("INSIDE playMove : ");
		if(player2UserName!=null){
			
			GameVO gameInfo=getCurrentGameById();
			if(gameInfo.getCurrentPlayer().equals(MyUtil.getSessionAttribute("userName")) 
					&& !gameInfo.getGameStatus().equals(Configuration.GAME_STATUS_FINISHED))
			{
				
				int piece;
				
				String currentBoardStatus=gameInfo.getCurrentBoardStatus();
				String result;
				if(player1UserName.equals((String)MyUtil.getSessionAttribute("userName"))){
					piece=1;
				}
				else
					piece=-1;
				
				MoveResult moveResult=GameEngine.playMove(currentBoardStatus,nextMove,piece);
				if(moveResult==null){
					displayInfo("Illegal move");
					rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
					return;
					//return "Illegal move";
				}
				else{
					System.out.println("LEGAL MOVE");
					
					timer = System.currentTimeMillis();
					System.out.println("the current time in ms:" +timer);
					gameInfo.setLastMoveTimeStamp(timer);
					
					gameInfo.setCurrentPlayer(getOtherPlayerName());
					if(gameInfo.getGameMoves()==null||gameInfo.getGameMoves().equals(""))
						gameInfo.setGameMoves(""+nextMove.toString());
					else{
						//Set Game move. Move stored as eg: (row,column,grid,"clockwise")
						gameInfo.setGameMoves(gameInfo.getGameMoves()+","+nextMove.toString());
					}
					String winner= moveResult.getWinner();
					
					switch(winner)
					{
					case "1":
						System.out.println("PLAY MOVE: TTTT : CASE 1");
						gameInfo.setWinner(player1UserName);
						gameInfo.setCurrentBoardStatus(moveResult.getNewBoard());
						gameInfo.setGameStatus("finished");
						gameInfo.setCurrentPlayer(getOtherPlayerName());
						result=gameEJB.updateCurrentGameState(gameInfo);
						if(gameEJB.updateStatistics(player1UserName,Configuration.STATISTICS_RESULT_WIN) &&
								gameEJB.updateStatistics(player2UserName,Configuration.STATISTICS_RESULT_LOSS)){
							displayInfo(player1UserName+" WINS!!");
							statusMsg= player1UserName+ " WINS !!";
							rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
							return;
							//return player1UserName+" wins!!";
						}
						else{
							displayInfo("Some error occured");
							rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
							return;
							//return "Some error occured";
						}
					case "-1":
						System.out.println("PLAY MOVE: TTTT : CASE -1");
						gameInfo.setWinner(player2UserName);
						gameInfo.setCurrentBoardStatus(moveResult.getNewBoard());
						gameInfo.setGameStatus("finished");
						gameInfo.setCurrentPlayer(getOtherPlayerName());
						result=gameEJB.updateCurrentGameState(gameInfo);
						if(gameEJB.updateStatistics(player1UserName,Configuration.STATISTICS_RESULT_LOSS) &&
								gameEJB.updateStatistics(player2UserName,Configuration.STATISTICS_RESULT_WIN)){
							displayInfo(player2UserName+" wins!!");
							statusMsg= player2UserName+ " WINS !!";
							rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
							return;
							//	return player2UserName+" wins!!";
						}
						else{
							displayInfo("Some error occured");
							rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
							return;
							//return "Some error occured";
						}
					case "0":
						System.out.println("PLAY MOVE: TTTT : CASE 0");
						gameInfo.setWinner("#match drawn#");
						gameInfo.setCurrentPlayer(getOtherPlayerName());
						gameInfo.setCurrentBoardStatus(moveResult.getNewBoard());
						gameInfo.setGameStatus("finished");
						result=gameEJB.updateCurrentGameState(gameInfo);
						if(gameEJB.updateStatistics(player1UserName,Configuration.STATISTICS_RESULT_DRAW) && 
								gameEJB.updateStatistics(player2UserName,Configuration.STATISTICS_RESULT_DRAW)){
							displayInfo("Game is a draw!!");
							rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
							return;
							//	return "Match drawn!!";
						}
						else{
							displayInfo("Some error occured");
							statusMsg= player1UserName+ " WINS !!";
							rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
							return;
							//return "Some error occured";
						}
					default:
						System.out.println("PLAY MOVE: TTTT : CASE NONE");
						gameInfo.setCurrentBoardStatus(moveResult.getNewBoard());
						result=gameEJB.updateCurrentGameState(gameInfo);
						//displayInfo("Sorry! It is not your turn...");
						//displayInfo("Last move by:"+getOtherPlayerName());
						rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
						return;
						// return "Next to move:"+getOtherPlayerName();
					}
				}
			}
			else if(gameInfo.getGameStatus().equals(Configuration.GAME_STATUS_FINISHED)){
				displayInfo("Your game has ended! Please EXIT the game to proceed...");
				//gameInfo.setCurrentPlayer("abc");//Nayana commented on 12/16 to (doubtful)
				
				statusMsg= gameInfo.getWinner()+" has WON the game!";
				//rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
				return;
			}
			else{
				displayInfo("Sorry! It is not your turn...");
				rows=MyUtil.stringToList(gameInfo.getCurrentBoardStatus());
				return;
			}
		}
	}
	
	public String exitGame(){
		
		GameVO myGameObj=getCurrentGameById();
		if(myGameObj!= null && myGameObj.getGameStatus().equals(Configuration.GAME_STATUS_NEW)){
			gameEJB.deleteGameObject(myGameObj.getId());
			statusMsg="";
			return "postLogin";
		}
		else if(myGameObj!= null && myGameObj.getGameStatus().equals(Configuration.GAME_STATUS_FINISHED)){
			statusMsg="";
			return "postLogin";
		}
		String loser=getMyUserName();
		
		String winner=getOtherPlayerName();
		
		GameVO gameInfo=getMyNewOrCurrentGame();
		gameInfo.setGameStatus(Configuration.GAME_STATUS_FINISHED);
		gameInfo.setWinner(winner);
		gameEJB.updateCurrentGameState(gameInfo);
		gameEJB.updateStatistics(winner,Configuration.STATISTICS_RESULT_WIN);
		gameEJB.updateStatistics(loser,Configuration.STATISTICS_RESULT_LOSS);
		statusMsg="You have lost the previous game.";
		return "postLogin";
		
	}
	
	private String getOtherPlayerName() {
		// TODO Auto-generated method stub
		if(player1UserName.equals((String)MyUtil.getSessionAttribute("userName")))
			return player2UserName;
		else
			return player1UserName;
	}
	public void setPlayer2UserName(String player2UserName) {
		this.player2UserName = player2UserName;
	}


	public String getPlayer1UserName() {
		return player1UserName;
	}


	public void setPlayer1UserName(String player1UserName) {
		this.player1UserName = player1UserName;
	}
	
	public void populateAvailablePlayers(){
		System.out.println("GGGGGGGGGGGGGGG");
		setAllPlayers(gameEJB.returnAvailablePlayers());
	}
	
	public String populateAvailableGames(){
		statusMsg="";
		System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPP=========HEY I AM in AvailableGames!!!!!!!========");
		setAllPlayers(gameEJB.returnAvailablePlayers());
		return "/secured/availGames"; 
	}

		
	public String createNewGame(){
		statusMsg="";
		player2UserName=null;
		System.out.println("====createNewGame() is getting called==========");
		player1UserName=(String)MyUtil.getSessionAttribute("userName");
		System.out.println("USERNAME:" + player1UserName);
		if(isValidGameJoiningOrCreation()){
			GameVO gameInfo=new GameVO();
			gameInfo.setGameStatus("new");
			gameInfo.setPlayer1(player1UserName);
			gameInfo.setCurrentPlayer(player1UserName);
			gameInfo.setCurrentBoardStatus("0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;0,0,0,0,0,0;");
			gameInfo.setLastMoveTimeStamp(System.currentTimeMillis());
			String status=gameEJB.createGameInstance(gameInfo);
			getMyNewOrCurrentGame(); // To save current game's ID in 'gameID' for future use. 
			if(status.equals("success")){
				System.out.println("SUCCESSS");
				return "/secured/newGame";
			}
			else{
				System.out.println("NOPE");
				return "/secured/postLogin";
			}
		}
		else{
			statusMsg="You have already created a game. You need to complete it!";
			return "/secured/postLogin";
		}
	}
	
	private boolean isValidGameJoiningOrCreation() {
		// TODO Auto-generated method stub
		GameVO myCurrentGame=getMyNewOrCurrentGame();
		if(myCurrentGame!=null && !getMyUserName().equals(myCurrentGame.getPlayer1()) && !getMyUserName().equals(myCurrentGame.getPlayer2())){
			System.out.println("GGGGG=======isValidGameJoiningOrCreation=======GGGGGGGGGG:"+getMyUserName().equals(myCurrentGame.getPlayer1()));
			return true;
		}
		else{
			System.out.println("GGGGG=======isValidGameJoiningOrCreation=======:"+myCurrentGame);
			return false;
		}
	}
/*  private void populateNewGameInfo(){
				
	}
	*/
	
	public String joinNewGame(){
		statusMsg="";
		//player1UserName=p;
		List<String> states=new ArrayList<String>();
		if(isValidGameJoiningOrCreation()){
			states.add("new");
			
			List<GameVO> newGamesList=gameEJB.getNewGameObject(player1UserName,states);
			if(newGamesList.size()!=0){
				GameVO game=newGamesList.get(0);
				player2UserName=(String)MyUtil.getSessionAttribute("userName");
				game.setPlayer2(player2UserName);
				game.setGameStatus("current");
				game.setLastMoveTimeStamp(System.currentTimeMillis());
				//game.setLastPlayerMoved(player2UserName);
				//gameInfo=game;
				String result=gameEJB.updateCurrentGameState(game);
				System.out.println("INSIDE JOIN NEW GAME : PLayer2"+player2UserName+"player1: "+player1UserName);
				getMyNewOrCurrentGame();
				//getCurrentGameById(); // To save current game's ID in 'gameID' for future use.
				if(result.equals("success")){
					System.out.println("XXXXXXXXXXXX");
					return "/secured/newGame";
				}
				else{
					statusMsg="Some internal error occured. Please try in some time";
					return "/secured/availGames";
				}
			}
			else{
				System.out.println("GGGGG=======joinNewGame======");
				statusMsg="Please select a game to join.";
				return "/secured/availGames"; 
			}

		}
		else{
			System.out.println("GGGGG=======joinNewGame======");
			statusMsg="You already have a current game going on!";
			return "/secured/availGames"; 
		}
		
	}
	
	public String joinMyExistingGame(){
		statusMsg="";
		GameVO currGame=getMyNewOrCurrentGame();
		
		if(getMyUserName().equals(currGame.getPlayer1()) || getMyUserName().equals(currGame.getPlayer2())){
			player1UserName=currGame.getPlayer1();
			player2UserName=currGame.getPlayer2();
			return "/secured/newGame";
		}
		else{
			statusMsg="You don't have any ongoing games. Please create or join a game";
			return "/secured/postLogin";
		}
	}
	private GameVO getCurrentGameById(){
		GameVO game=gameEJB.getGameObjectById(gameId);
		return game;
	}
	
	private GameVO getMyNewOrCurrentGame(){
		System.out.println("in getMyNewOrCurrentGame");
		List<String> states=new ArrayList<String>();
		states.add("new");
		states.add("current");
		List<GameVO> myGameList=gameEJB.getGameObject((String)MyUtil.getSessionAttribute("userName"),states);
		System.out.println("GAMEID111:");
		if(myGameList.size()>0){
			
			gameId = myGameList.get(0).getId();
			System.out.println("GAMEID:"+gameId+myGameList.get(0));
			return myGameList.get(0);
		}
		else{
			System.out.println("GAMEID2222:");
			return new GameVO();
		}
	}
	
	private String getMyUserName(){
		return (String)MyUtil.getSessionAttribute("userName");
	}
	public List<String> getAllPlayers() {
		return allPlayers;
	}

	public void setAllPlayers(List<String> allPlayers) {
		this.allPlayers = allPlayers;
	}
	public void doLogout() throws IOException{
		System.out.println("in game manager bean logout");
		//GameVO myGameObj=getMyNewOrCurrentGame();
		GameVO myGameObj=getCurrentGameById();
		if(myGameObj!= null && myGameObj.getGameStatus().equals(Configuration.GAME_STATUS_NEW)){
			//gameEJB.deleteGameObject(myGameObj.getId());
			/*HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.invalidate();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");*/
			//return "postLogin";
		}
		else if(myGameObj!= null && myGameObj.getGameStatus().equals(Configuration.GAME_STATUS_FINISHED)){
			//gameEJB.deleteGameObject(myGameObj.getId());
			HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.invalidate();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			//return "postLogin";
		}
		/*
		else{
		String loser=getMyUserName();
		
		String winner=getOtherPlayerName();
		
		GameVO gameInfo=getMyNewOrCurrentGame();
		gameInfo.setGameStatus(Configuration.GAME_STATUS_FINISHED);
		gameInfo.setWinner(winner);
		gameEJB.updateCurrentGameState(gameInfo);
		gameEJB.updateStatistics(winner,Configuration.STATISTICS_RESULT_WIN);
		gameEJB.updateStatistics(loser,Configuration.STATISTICS_RESULT_LOSS);
		}*/
		HttpSession session=(HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		//System.out.println("session is :"+session);
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");  
	}
}
