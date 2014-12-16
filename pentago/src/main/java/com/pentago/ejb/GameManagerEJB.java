package com.pentago.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pentago.config.Configuration;
import com.pentago.myutil.MyUtil;
import com.pentago.persistence.GameVO;
import com.pentago.persistence.PlayerVO;
import com.pentago.persistence.StatisticsVO;

@Stateless
public class GameManagerEJB {
	@PersistenceContext(unitName = "pentagoDS")
	private EntityManager em;
	
	private List<StatisticsVO> statistics=new ArrayList<StatisticsVO>();
	
	public String createGameInstance(GameVO game){
		try{
			game=em.merge(game);
			em.persist(game);
			return "success";
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "failure";
		}
	}
	
	public List<GameVO> getNewGameObject(String player1UserName, List<String> states) {
		System.out.println("PLAYER 111111:"+player1UserName);
		final Query query = em.createQuery("from GameVO g where g.player1=:player1UserName and g.gameStatus in (:statusList)");
		query.setParameter("player1UserName", player1UserName);
		query.setParameter("statusList", states);
		List<GameVO> entries = query.getResultList();
		
		if (entries == null) 
			entries = new ArrayList<GameVO>();
		
		return entries;
	}

	public void deleteGameObject(long id){
		GameVO gameOb = em.find(GameVO.class,id);
		em.remove(gameOb);
	}

	public String updateCurrentGameState(GameVO game) {
		
		try{
			game=em.merge(game);
			em.persist(game);
			return "success";
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			return "failure";
		}
	}
	
	public List<String> returnAvailablePlayers() {
		// TODO Auto-generated method stub
		
		final Query query = em.createQuery("select g.player1 from GameVO g where g.player1 !=:self and g.player2 IS NULL and g.gameStatus=:status");
		query.setParameter("status", "new");
		query.setParameter("self",(String)MyUtil.getSessionAttribute("userName"));
		
		List<String> entries = query.getResultList();
		//System.out.println("KKKKKKKKKKK:"+entries.size());
		if (entries == null) {
			entries = new ArrayList<String>();
		}
		return entries;
	}
	
	public List<GameVO> getGameObject(String playerUserName, List<String> states) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		final Query query = em.createQuery("from GameVO g where (g.player1=:playerUserName or g.player2=:playerUserName) and g.gameStatus in (:statusList)");
		query.setParameter("playerUserName", playerUserName);
		query.setParameter("statusList", states);
		List<GameVO> entries = query.getResultList();
		if (entries == null) {
			entries = new ArrayList<GameVO>();
		}
		return entries;
		
	}
	
	//Retrieves game object by the GameID
	public GameVO getGameObjectById(long id){
		
		final Query query = em.createQuery("from GameVO g where g.id=:ID");
		query.setParameter("ID", id);
		List<GameVO> games = query.getResultList();
		if(games!=null){
			return games.get(0);
		}
		else
			return null;
	}
	
	public boolean updateStatistics(String userName,String result){
		StatisticsVO stats;
		int count;
		try{



			final Query query = em.createQuery("from StatisticsVO s where s.userName=:userName");
			query.setParameter("userName", userName);
			//		query.setParameter("statusList", states);
			List<StatisticsVO> entries = query.getResultList();
			if (entries == null) {
				entries = new ArrayList<StatisticsVO>();
			}
			if(entries.size()==0){
				stats=new StatisticsVO();
				stats.setUserName(userName);
				stats.setDraws(0);
				stats.setLoses(0);
				stats.setWins(0);
			}
			else{
				stats=entries.get(0);
				//	stats=em.merge(stats);
			}
			switch(result)
			{
			case Configuration.STATISTICS_RESULT_WIN:
				count=stats.getWins();
				count++;
				stats.setWins(count);
				break;
			case Configuration.STATISTICS_RESULT_LOSS:
				count=stats.getLoses();
				count++;
				stats.setLoses(count);
				break;
			case Configuration.STATISTICS_RESULT_DRAW:
				count=stats.getLoses();
				count++;
				stats.setLoses(count);
				break;

			}
			em.persist(stats);
			return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("======= Failed to persist statistics=======");
			return false;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<StatisticsVO> findStatistics(){
		Query query = em.createQuery("from StatisticsVO");
		statistics = query.getResultList();
		return statistics;
	}

	public List<GameVO> getGameLogs() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		final Query query = em.createQuery("from GameVO g where g.gameStatus=:finishedStatus");
		query.setParameter("finishedStatus", Configuration.GAME_STATUS_FINISHED);
		List<GameVO> entries = query.getResultList();
		if (entries == null) {
			entries = new ArrayList<GameVO>();
		}
		return entries;
		
	}
	
	
}
