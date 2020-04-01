package fr.diginamic.recensement.services;

import java.util.List;

import javax.swing.JTextPane;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'une ville
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationVille extends MenuService {

	private String nomVille;

	public RecherchePopulationVille(JTextPane affichage, String nomVille) {
		super(affichage);
		this.nomVille = nomVille;
	}

	@Override
	public void traiter(Recensement rec) {

		if (nomVille == null || nomVille.equals("")) {
			printError("Veuillez saisir un nom de ville");
			return;
		}

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getNom().equalsIgnoreCase(nomVille)
					|| ville.getNom().toLowerCase().startsWith(nomVille.toLowerCase())) {

				String text = ville.getNom() + " a une population de " + ville.getPopulation() + " habitants.";
				println(text);
			}
		}
	}

}
