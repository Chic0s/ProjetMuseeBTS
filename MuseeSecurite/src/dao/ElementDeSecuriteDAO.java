package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import securite.ElementDeSecurite;

public class ElementDeSecuriteDAO extends DAO<ElementDeSecurite>{

	private static final String EMPLACEMENT = "Emplacement";
	private static final String MODELE = "Modele";
	private static final String TABLE = "Element";
	private static final String NOM = "Nom";
	private static final String CLE_PRIMAIRE = "ID";
	
	private static ElementDeSecuriteDAO instance=null; 
	
	public static ElementDeSecuriteDAO getInstance(){
		if (instance==null){
			instance = new ElementDeSecuriteDAO();
		}
		return instance;
	}

	private ElementDeSecuriteDAO() {
		super();
	}

	
	@Override
	public boolean create(ElementDeSecurite elem) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+MODELE+", "+EMPLACEMENT+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			
			pst.setString(1, elem.getNom());
			pst.setString(2, elem.getModele());
			pst.setString(3, elem.getEmplacement());

			

			pst.executeUpdate();


			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				elem.setId(rs.getInt(1));
			}
			donnees.put(elem.getId(), elem);

		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(ElementDeSecurite elem) {
		boolean success = true;
		try {
			int id = elem.getId();
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
	public boolean update(ElementDeSecurite elem) {
		boolean success=true;

		String nom =elem.getNom();
		String modele =elem.getModele();
		String emplacement = elem.getEmplacement();
		int id = elem.getId();

		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, mod = ?, emp = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,modele) ; 
			pst.setString(3, emplacement) ;
			pst.setInt(4, id) ;
			pst.executeUpdate() ;
			donnees.put(id, elem);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}
	
	public Set<Integer> getKeys() {
	    return donnees.keySet();
	}

	@Override
	public ElementDeSecurite read(int id) {
		ElementDeSecurite elem = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			String modele = rs.getString(MODELE);
			String emplacement = rs.getString(EMPLACEMENT);
			elem = new ElementDeSecurite (id, nom, modele, emplacement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return elem;
	}
	

}
