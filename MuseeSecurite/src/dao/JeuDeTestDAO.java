package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import securite.JeuDeTest;

public class JeuDeTestDAO extends DAO<JeuDeTest>{
	private static final String TABLE = "JeuDeTest";
	private static final String NOM = "Nom";
	private static final String VALEUR = "Valeur";
	private static final String CLE_PRIMAIRE = "ID";
	
	private static JeuDeTestDAO instance=null; 
	
	public static JeuDeTestDAO getInstance(){
		if (instance==null){
			instance = new JeuDeTestDAO();
		}
		return instance;
	}

	private JeuDeTestDAO() {
		super();
	}

	
	@Override
	public boolean create(JeuDeTest alerte) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+VALEUR+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, alerte.getNom());
			pst.setString(3, alerte.getValeur());
			

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
	public boolean delete(JeuDeTest alerte) {
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
	public boolean update(JeuDeTest alerte) {
		boolean success=true;

		String nom = alerte.getNom();
		String valeur = alerte.getValeur();
		int id = alerte.getId();
		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, valeur = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom); 
			pst.setString(2, valeur);
			pst.setInt(3, id) ;
			pst.executeUpdate() ;
			
			data.put(id, alerte);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}

	
	public List<JeuDeTest> readAll() {
		List<JeuDeTest> elemList = new ArrayList<JeuDeTest>();
		JeuDeTest elem = null;
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
		System.out.println("JeuDeTest.size = " + elemList.size());
		return elemList;
	}
	
	
	@Override
	public JeuDeTest read(int id) {
		JeuDeTest alerte = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			String valeur = rs.getString(VALEUR);
			alerte = new JeuDeTest (id, nom,valeur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alerte;
	}

}
