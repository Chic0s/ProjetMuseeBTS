package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import securite.Serveur;

public class ServeurDAO extends DAO<Serveur>{

	private static final String USAGE = "Usage";
	private static final String CAPACITE = "Capacite";
	private static final String NOM = "Nom";
	private static final String CLE_PRIMAIRE = "ID";
	private static final String TABLE = "Serveur";
		
	
	private static ServeurDAO instance=null; 
	
	public static ServeurDAO getInstance(){
		if (instance==null){
			instance = new ServeurDAO();
		}
		return instance;
	}

	private ServeurDAO() {
		super();
	}

	@Override
	public boolean create(Serveur serv) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+CAPACITE+", "+USAGE+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			
			pst.setString(1, serv.getNom());
			pst.setInt(2, serv.getCapacite());
			pst.setString(3, serv.getUsage());

			

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				serv.setId(rs.getInt(1));
			}
			data.put(serv.getId(), serv);

		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(Serveur serv) {
		boolean success = true;
		try {
			int id = serv.getId();
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
	public boolean update(Serveur serv) {
		boolean success=true;

		String nom =serv.getNom();
		int capacite =serv.getCapacite();
		String usage = serv.getUsage();
		int id = serv.getId();

		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, cap = ?, use = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nom) ; 
			pst.setInt(2,capacite) ; 
			pst.setString(3, usage) ;
			pst.setInt(4, id) ;
			pst.executeUpdate() ;
			data.put(id, serv);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}
	
	public List<Serveur> readAll() {
		List<Serveur> elemList = new ArrayList<Serveur>();
		Serveur elem = null;
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
	public Serveur read(int id) {
		Serveur serv = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String nom = rs.getString(NOM);
			int capacite = rs.getInt(CAPACITE);
			String usage = rs.getString(USAGE);
			serv = new Serveur (id, nom, capacite, usage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serv;
	}

}
