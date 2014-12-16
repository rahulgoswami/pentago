package com.pentago.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pentago.persistence.PlayerVO;

@Stateless
public class PlayerInfoManagerEJB {
	@PersistenceContext(unitName = "pentagoDS")
	private EntityManager	em;

	public String savePlayerInfo(PlayerVO player) {
		try{
			
			System.out.println("SSSSSSSSSSSSSSSS");
			em.persist(player);
			return "success";
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "failure";
		}
		
	}
	
	public ArrayList<PlayerVO> registerCheck(PlayerVO player)
	{
		Query query = em.createQuery("from Player p where p.userName = ?1");
		query.setParameter(1, player.getUserName());
		ArrayList<PlayerVO> entries = (ArrayList<PlayerVO>) query.getResultList();
		if(entries == null)
			entries = new ArrayList<PlayerVO>();
		return entries;
		
	}
	
	public ArrayList<PlayerVO> loginCheck(String username, String password){
		
		Query query = em.createQuery("from PlayerVO p where p.userName= ?1");
		query.setParameter(1, username);
		ArrayList<PlayerVO> entries = (ArrayList<PlayerVO>) query.getResultList();
		/*if(entries == null)
			return null;*/
		return entries;
	 }
	
	public List<PlayerVO> findPlayerInfo() {
		final Query query = em.createQuery("from PlayerVO p ORDER BY p.firstName");
		List<PlayerVO> entries = query.getResultList();
		if (entries == null) {
			entries = new ArrayList<PlayerVO>();
		}
		return entries;
	}

	public void deletePlayer(PlayerVO player) {
		player = em.merge(player);
		em.remove(player);
	}
}