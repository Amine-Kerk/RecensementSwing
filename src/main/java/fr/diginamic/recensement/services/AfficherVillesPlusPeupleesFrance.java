package fr.diginamic.recensement.services;

import java.util.Collections;
import java.util.List;

import javax.swing.JTextPane;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des 10 villes les plus peuplées de France
 * 
 * @author DIGINAMIC
 *
 */
public class AfficherVillesPlusPeupleesFrance extends MenuService {

	private String nbVillesStr;

	public AfficherVillesPlusPeupleesFrance(JTextPane affichage, String nbVillesStr) {
		super(affichage);
		this.nbVillesStr = nbVillesStr;
	}

	@Override
	public void traiter(Recensement recensement) {

		if (!NumberUtils.isDigits(nbVillesStr)) {
			printError("Veuillez saisir un nombre entier de villes.");
			return;
		}
		int nbVilles = Integer.parseInt(nbVillesStr);

		List<Ville> villes = recensement.getVilles();
		println("Les " + nbVilles + " villes les plus peuplées de France sont :");
		Collections.sort(villes, new EnsemblePopComparateur(false));
		for (int i = 0; i < nbVilles; i++) {
			Ville ville = villes.get(i);
			println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
		}
	}

}
