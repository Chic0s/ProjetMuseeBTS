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
import securite.ElementDeSecurite;

public class ActionDAO extends DAO<Action>{

	private static final String CONDITION = "Condition";
	private static final String VALEUR = "Valeur";
	private static final String IDCAPTEUR = "IdCapteur";
	private static final String TABLE = "ActionRealiser";
	private static final String CLE_PRIMAIRE = "ID";
	
	private static ActionDAO instance=null; 
	
	public static ActionDAO getInstance(){
		if (instance==null){
			instance = new ActionDAO();
		}
		return instance;
	}

	private ActionDAO() {
		super();
	}

	
	@Override
	public boolean create(Action action) {
		boolean success=true;
		try {
			String requete = "INSERT INTO "+TABLE+" ("+CONDITION+", "+VALEUR+", "+IDCAPTEUR+") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, action.getCondition());
			pst.setInt(2, action.getValeur());
			pst.setInt(3, action.getCapteur().getId());			

			pst.executeUpdate();


			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				action.setId(rs.getInt(1));
			}
			data.put(action.getId(), action);

		} catch (SQLException e) {
			success=false;
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(Action action) {
		boolean success = true;
		try {
			int id = action.getId();
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
	public boolean update(Action action) {
		boolean success=true;

		String condition = action.getCondition();
		int valeur = action.getValeur();
		int idCapteur = action.getCapteur().getId();
		int id = action.getId();

		try {
			String requete = "UPDATE "+TABLE+" SET nomav = ?, loc = ?, capacite = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1, condition); 
			pst.setFloat(2, (float)valeur); 
			pst.setInt(3, idCapteur);
			pst.executeUpdate() ;
			
			data.put(id, action);
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		} 
		return success;	
	}

	@Override
	public Action read(int id) {
		Action action = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = "+id;
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String condition = rs.getString(CONDITION);
			int valeur = rs.getInt(VALEUR);
			ElementDeSecurite capteur = ElementDeSecuriteDAO.getInstance().read(rs.getInt(IDCAPTEUR));
			action = new Action (id, condition, valeur, capteur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return action;
	}
	
	public List<Action> readAll() {
		List<Action> elemList = new ArrayList<Action>();
		Action elem = null;
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
