package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import securite.Alertes;

public class AlertesDAO extends DAO<Alertes>{
	private static final String TABLE = "Alerte";
	private static final String NOM = "Nom";
	private static final String CONDITION = "Condition";
	private static final String VALEUR = "Valeur";
	private static final String ETAT = "Etat";
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
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+CONDITION+", "+VALEUR+", "+ETAT+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, alerte.getNom());
			pst.setString(2, alerte.getCondition());
			pst.setString(3, alerte.getValeur());
			pst.setBoolean(4, alerte.getEtat());
			

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

		String nom = alerte.getNom();
		String position =alerte.getCondition();
		String emplacement = alerte.getValeur();
		Boolean etat =  alerte.getEtat();
		int id = alerte.getId();
		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, condition = ?, valeur = ?, etat = ?  WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom); 
			pst.setString(2,position); 
			pst.setString(3, emplacement);
			pst.setBoolean(4, etat);
			pst.setInt(5, id) ;
			pst.executeUpdate() ;
			
			data.put(id, alerte);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}

	
	public List<Alertes> readAll() {
		List<Alertes> elemList = new ArrayList<Alertes>();
		Alertes elem = null;
		try {
			String requete = "SELECT * FROM " + TABLE;
			ResultSet rep = Connexion.executeQuery(requete);
			while(rep.next()) {
				int idZone = rep.getInt(1);
				elem = this.read(idZone);
				elemList.add(elem);
			}
	    } catch (SQLException e) {
	        // e.printStackTrace();
	        System.out.println("Échec de la tentative d'interrogation Select * : " + e.getMessage()) ;
	    }
		System.out.println("Alertes.size = " + elemList.size());
		return elemList;
	}
	
	
	@Override
	public Alertes read(int id) {
		Alertes alerte = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			String condition = rs.getString(CONDITION);
			String valeur = rs.getString(VALEUR);
			boolean etat = rs.getBoolean(ETAT);
			alerte = new Alertes (id, nom, condition, valeur, etat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alerte;
	}

}
