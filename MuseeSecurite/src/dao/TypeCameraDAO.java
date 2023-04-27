package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import securite.TypeCamera;

public class TypeCameraDAO extends DAO<TypeCamera>{

	private static final String FLUX = "Flux";
	private static final String ANGLEDEVUE = "AngleDeVue";
	private static final String TYPEDECAMERA = "TypeDeCamera";
	private static final String DESCRIPTION = "Description";
	
	private static final String TABLE = "TypeCamera";

	private static final String CLE_PRIMAIRE = "ID";
	
	private static TypeCameraDAO instance=null; 
	
	public static TypeCameraDAO getInstance(){
		if (instance==null){
			instance = new TypeCameraDAO();
		}
		return instance;
	}

	private TypeCameraDAO() {
		super();
	}

	
	@Override
	public boolean create(TypeCamera elem) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+FLUX+", "+ANGLEDEVUE+", "+TYPEDECAMERA+","+DESCRIPTION+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			
			pst.setString(1, elem.getFlux());
			pst.setString(2, elem.getAngledevue());
			pst.setString(3, elem.getTypedecamera());
			pst.setString(4, elem.getDescription());
			

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
	public boolean delete(TypeCamera elem) {
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
	public boolean update(TypeCamera elem) {
		boolean success=true;

		String flux =elem.getFlux();
		String angledevue =elem.getAngledevue();
		String typedecamera = elem.getTypedecamera();
		String description = elem.getDescription();
		int id = elem.getId();

		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, mod = ?, emp = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,flux) ; 
			pst.setString(2,angledevue) ; 
			pst.setString(3, typedecamera) ;
			pst.setString(4, description) ;
			pst.setInt(5, id) ;
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
	
	public List<TypeCamera> readAll() {
		List<TypeCamera> elemList = new ArrayList<TypeCamera>();
		TypeCamera elem = null;
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
	public TypeCamera read(int id) {
		TypeCamera elem = null;
		
			if(data.containsKey(id)) {
				elem = data.get(id);
			}
			else {
				try {
				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String flux = rs.getString(FLUX);
				String angledevue = rs.getString(ANGLEDEVUE);
				String typedecamera = rs.getString(TYPEDECAMERA);
				String description = rs.getString(DESCRIPTION);
				elem = new TypeCamera (id, flux, angledevue, typedecamera,description);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


		return elem;
	}
	

}
