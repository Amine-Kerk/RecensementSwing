package fr.diginamic.recensement.services;

import java.util.List;

import javax.swing.JTextPane;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'un département
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationDepartement extends MenuService {

	private String nomDept;

	public RecherchePopulationDepartement(JTextPane affichage, String nomDept) {
		super(affichage);
		this.nomDept = nomDept;
	}

	@Override
	public void traiter(Recensement rec) {

		if (nomDept == null || nomDept.equals("")) {
			printError("Veuillez saisir un nom de département");
			return;
		}

		List<Ville> villes = rec.getVilles();
		int somme = 0;
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(nomDept)) {
				somme += ville.getPopulation();
			}
		}
		if (somme > 0) {
			println("Population du département " + nomDept + " : " + somme);
		} else {
			printError("Département " + nomDept + " non trouvé.");
		}
	}

}
