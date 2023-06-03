package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import securite.Action;
import securite.Alertes;

public class AlertesDAO extends DAO<Alertes>{

	private static final String EMPLACEMENT = "Emplacement";
	private static final String POSITION = "Position";
	private static final String ETAT = "Etat";
	private static final String TABLE = "Alerte";
	private static final String DATE = "Date";
	private static final String IDACTION = "IdActionRealiser";
	private static final String NOM = "Nom";
	private static final String DESCRIPTION = "Description";
	private static final String CLE_PRIMAIRE = "ID";
	
	private static AlertesDAO instance=null; 
	
	public static AlertesDAO getInstance(){
		if (instance==null){
			instance = new AlertesDAO();
		}
		return instance;
	}

	private AlertesDAO() {
		super();
	}

	
	@Override
	public boolean create(Alertes alerte) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+DATE+", "+POSITION+", "+EMPLACEMENT+", "+ETAT+", " + IDACTION + ", " + NOM + ", " + DESCRIPTION + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setDate(1, alerte.getDate());
			pst.setString(2, alerte.getPosition());
			pst.setString(3, alerte.getEmplacement());
			pst.setBoolean(4, alerte.getEtat());
			pst.setInt(5, alerte.getAction().getId());
			pst.setString(6, alerte.getNom());
			pst.setString(7, alerte.getDescription());
			
			pst.executeUpdate();


			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				alerte.setId(rs.getInt(1));
			}
			data.put(alerte.getId(), alerte);

		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(Alertes alerte) {
		boolean success = true;
		try {
			int id = alerte.getId();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
			data.remove(id);
		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}
		return success;
	}
	

	@Override
	public boolean update(Alertes alerte) {
		boolean success=true;

		Date date = alerte.getDate();
		String position =alerte.getPosition();
		String emplacement = alerte.getEmplacement();
		boolean etat =  alerte.getEtat();
		int id = alerte.getId();
		String nom = alerte.getNom();
		String description = alerte.getDescription();
		try {
			String requete = "UPDATE "+TABLE+" SET nomav = ?, loc = ?, capacite = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setDate(1,date); 
			pst.setString(2,position); 
			pst.setString(3, emplacement);
			pst.setBoolean(4, etat);
			pst.setInt(5, id) ;
			pst.setString(6, nom);
			pst.setString(7, description);
			
			pst.executeUpdate() ;
			
			data.put(id, alerte);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}

	@Override
	public Alertes read(int id) {
		Alertes alerte = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			Date date = rs.getDate(DATE);
			String position = rs.getString(POSITION);
			String emplacement = rs.getString(EMPLACEMENT);
			boolean etat = rs.getBoolean(ETAT);
			Action action = ActionDAO.getInstance().read(rs.getInt(IDACTION));
			String nom = rs.getString(NOM);
			String description = rs.getString(DESCRIPTION);
			alerte = new Alertes (id, date, position, emplacement, etat, action, nom, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alerte;
	}
	
	public List<Alertes> readAll() {
		List<Alertes> elemList = new ArrayList<Alertes>();
		Alertes elem = null;
		try {
			String requete = "SELECT * FROM " + TABLE;
			ResultSet rep = Connexion.executeQuery(requete);
			System.out.println(rep);
			
			while(rep.next()) {
				int idZone = rep.getInt(1);
				elem = this.read(idZone);
				elemList.add(elem);
			}
	    } catch (SQLException e) {
	        // e.printStackTrace();
	        System.out.println("�chec de la tentative d'interrogation Select * : " + e.getMessage()) ;
	    }
		System.out.println(elemList.size());
		return elemList;
	}

}
