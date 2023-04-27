package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import securite.Salle;

public class SalleDAO extends DAO<Salle>{

	private static final String ETAGE = "Etage";
	private static final String HUMIDITE = "Humidite";
	private static final String TABLE = "Salle";
	private static final String NOM = "Nom";
	private static final String CLE_PRIMAIRE = "ID";
	
private static SalleDAO instance=null; 
	
	public static SalleDAO getInstance(){
		if (instance==null){
			instance = new SalleDAO();
		}
		return instance;
	}

	private SalleDAO() {
		super();
	}

	
	@Override
	public boolean create(Salle salle) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+HUMIDITE+", "+ETAGE+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			
			pst.setString(1, salle.getNom());
			pst.setInt(2, salle.getHumidite());
			pst.setString(3, salle.getEtage());

			

			pst.executeUpdate();


			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				salle.setId(rs.getInt(1));
			}
			data.put(salle.getId(), salle);

		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(Salle salle) {
		boolean success = true;
		try {
			int id = salle.getId();
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
	public boolean update(Salle salle) {
		boolean success=true;

		String nom =salle.getNom();
		int humidite =salle.getHumidite();
		String etage = salle.getEtage();
		int id = salle.getId();

		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, mod = ?, emp = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setInt(2,humidite) ; 
			pst.setString(3, etage) ;
			pst.setInt(4, id) ;
			pst.executeUpdate() ;
			data.put(id, salle);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}
	
	public List<Salle> readAll() {
		List<Salle> elemList = new ArrayList<Salle>();
		Salle elem = null;
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
		System.out.println(elemList.size());
		return elemList;
	}

	@Override
	public Salle read(int id) {
		Salle elem = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			int humidite = rs.getInt(HUMIDITE);
			String etage = rs.getString(ETAGE);
			elem = new Salle (id, nom, humidite, etage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return elem;
	}

}
