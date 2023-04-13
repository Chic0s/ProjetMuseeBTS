package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import securite.Alertes;

public class AlertesDAO extends DAO<Alertes>{

	private static final String EMPLACEMENT = "Emplacement";
	private static final String POSITION = "Position";
	private static final String ETAT = "Etat";
	private static final String TABLE = "Capteur";
	private static final String DATE = "Date";
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
			String requete = "INSERT INTO "+TABLE+" ("+DATE+", "+POSITION+", "+EMPLACEMENT+", "+ETAT+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, alerte.getDate());
			pst.setString(2, alerte.getPosition());
			pst.setString(3, alerte.getEmplacement());
			pst.setString(4, alerte.getEtat());
			

			pst.executeUpdate();


			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				alerte.setId(rs.getInt(1));
			}
			donnees.put(alerte.getId(), alerte);

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
			donnees.remove(id);
		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}
		return success;
	}
	

	@Override
	public boolean update(Alertes alerte) {
		boolean success=true;

		String date = alerte.getDate();
		String position =alerte.getPosition();
		String emplacement = alerte.getEmplacement();
		String etat =  alerte.getEtat();
		int id = alerte.getId();
		try {
			String requete = "UPDATE "+TABLE+" SET nomav = ?, loc = ?, capacite = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,date); 
			pst.setString(2,position); 
			pst.setString(3, emplacement);
			pst.setString(4, etat);
			pst.setInt(5, id) ;
			pst.executeUpdate() ;
			
			donnees.put(id, alerte);
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
			String date = rs.getString(DATE);
			String position = rs.getString(POSITION);
			String emplacement = rs.getString(EMPLACEMENT);
			String etat = rs.getString(ETAT);
			alerte = new Alertes (id, date, position, emplacement, etat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alerte;
	}

}
