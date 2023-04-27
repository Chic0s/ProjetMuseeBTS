package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import securite.TypeCapteur;

public class TypeCapteurDAO extends DAO<TypeCapteur>{

	private static final String FORMATDESORTIE = "FormatDeSortie";
	private static final String TYPECAPTEUR = "Type";
	private static final String DESCRIPTION = "Description";
	
	private static final String TABLE = "TypeCapteur";

	private static final String CLE_PRIMAIRE = "ID";
	
	private static TypeCapteurDAO instance=null; 
	
	public static TypeCapteurDAO getInstance(){
		if (instance==null){
			instance = new TypeCapteurDAO();
		}
		return instance;
	}

	private TypeCapteurDAO() {
		super();
	}

	
	@Override
	public boolean create(TypeCapteur elem) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+TYPECAPTEUR+", "+FORMATDESORTIE+","+DESCRIPTION+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			
			pst.setString(1, elem.getType());
			pst.setString(2, elem.getFormatdesortie());
			pst.setString(3, elem.getDescription());
			

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
	public boolean delete(TypeCapteur elem) {
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
	public boolean update(TypeCapteur elem) {
		boolean success=true;

		String type =elem.getType();
		String formatdesortie =elem.getFormatdesortie();
		String description = elem.getDescription();
		int id = elem.getId();

		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, mod = ?, emp = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,type) ; 
			pst.setString(2,formatdesortie) ; 
			pst.setString(3, description) ;
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
	
	public List<TypeCapteur> readAll() {
		List<TypeCapteur> elemList = new ArrayList<TypeCapteur>();
		TypeCapteur elem = null;
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
	public TypeCapteur read(int id) {
		TypeCapteur elem = null;
		
			if(data.containsKey(id)) {
				elem = data.get(id);
			}
			else {
				try {
				String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
				ResultSet rs = Connexion.executeQuery(requete);
				rs.next();
				String typedecapteur = rs.getString(TYPECAPTEUR);
				String formatdesortie = rs.getString(FORMATDESORTIE);
				String description = rs.getString(DESCRIPTION);
				elem = new TypeCapteur (id, typedecapteur, formatdesortie, description);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


		return elem;
	}
	

}
