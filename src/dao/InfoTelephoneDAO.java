package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import securite.InfoTelephone;

public class InfoTelephoneDAO extends DAO<InfoTelephone>{

	private static final String NUMEROTELEPHONE = "NumeroTelephone";
	private static final String TABLE = "InfoTelephone";
	private static final String NOM = "Nom";
	private static final String CLE_PRIMAIRE = "ID";
	
	private static InfoTelephoneDAO instance=null; 
	
	public static InfoTelephoneDAO getInstance(){
		if (instance==null){
			instance = new InfoTelephoneDAO();
		}
		return instance;
	}

	private InfoTelephoneDAO() {
		super();
	}

	
	@Override
	public boolean create(InfoTelephone elem) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+NUMEROTELEPHONE+") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			
			pst.setString(1, elem.getNom());
			pst.setString(2, elem.getNumerotelephone());

			

			pst.executeUpdate();


			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				elem.setId(rs.getInt(1));
			}
			data.put(elem.getId(), elem);

		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(InfoTelephone elem) {
		boolean success = true;
		try {
			int id = elem.getId();
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
	public boolean update(InfoTelephone elem) {
		boolean success=true;

		String nom =elem.getNom();;
		String numerotelephone = elem.getNumerotelephone();
		int id = elem.getId();

		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, num = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setString(2,numerotelephone);
			pst.setInt(4, id) ;
			pst.executeUpdate() ;
			data.put(id, elem);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}
	
	public Set<Integer> getKeys() {
	    return data.keySet();
	}
	
	public List<InfoTelephone> readAll() {
		List<InfoTelephone> elemList = new ArrayList<InfoTelephone>();
		InfoTelephone elem = null;
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
		System.out.println("tel = " + elemList.size());
		return elemList;
	}
	
	@Override
	public InfoTelephone read(int id) {
		InfoTelephone elem = null;
		
			if(data.containsKey(id)) {
				elem = data.get(id);
			}
			else {
				try {
				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String nom = rs.getString(NOM);
				String numerotelephone = rs.getString(NUMEROTELEPHONE);
				elem = new InfoTelephone (id, nom, numerotelephone);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


		return elem;
	}
	

}
