package test;

import dao.SalleDAO;
import securite.Salle;

public class TestBd {
	
	public static Boolean LAUNCH = false;

	public TestBd() {
		System.out.print("Test de la connexion avec la BD et affichage des éléments correspondants ");
	}

	
	public Boolean CheckBd() {
		if(LAUNCH==false){
			System.out.print("L'etat du test est sur OFF");
		}
		else {
			System.out.print("L'etat du test est sur ON");
		}
		return LAUNCH;
	}
	
	    public static void main(String[] args) {
		if(LAUNCH==false){
			SalleDAO salleDAO = SalleDAO.getInstance();
			Salle serv = new Salle(7, "coucou",14,"sdd");
			salleDAO.create(serv);
			System.out.print("Ajout d'un objet Serveur via la DAO");
		}
    }
}
