package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Cas d'utilisation: affichage des 10 villes les plus peuplées d'une
 * département donné
 * 
 * @author DIGINAMIC
 *
 */
public class AfficherVillesPlusPeupleesDepartement extends MenuService {

	private String nomDept;
	private String nbVillesStr;

	public AfficherVillesPlusPeupleesDepartement(JTextPane affichage, String nomDept, String nbVillesStr) {
		super(affichage);
		this.nomDept = nomDept;
		this.nbVillesStr = nbVillesStr;
	}

	@Override
	public void traiter(Recensement recensement) {

		if (StringUtils.isEmpty(nomDept)) {
			printError("Veuillez saisir le numéro d'un département.");
			return;
		}
		if (!NumberUtils.isDigits(nbVillesStr)) {
			printError("Veuillez saisir un nombre entier de villes.");
			return;
		}
		int nbVilles = Integer.parseInt(nbVillesStr);

		List<Ville> villesDept = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(nomDept)) {
				villesDept.add(ville);
			}
		}

		Collections.sort(villesDept, new EnsemblePopComparateur(false));

		if (villesDept.size() > 0) {
			println("Les " + nbVilles + " villes les plus peuplées du département " + nomDept + " :");
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesDept.get(i);
				println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		} else {
			printError("Département " + nomDept + " non trouvé.");
		}
	}

}
