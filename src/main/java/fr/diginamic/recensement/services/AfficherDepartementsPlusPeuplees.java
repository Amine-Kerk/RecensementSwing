package fr.diginamic.recensement.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextPane;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;

/**
 * Affichage des 10 départements les plus peuplés
 * 
 * @author DIGINAMIC
 *
 */
public class AfficherDepartementsPlusPeuplees extends MenuService {

	private String nbDeptsStr;

	public AfficherDepartementsPlusPeuplees(JTextPane affichage, String nbDeptsStr) {
		super(affichage);
		this.nbDeptsStr = nbDeptsStr;
	}

	@Override
	public void traiter(Recensement rec) {

		if (!NumberUtils.isDigits(nbDeptsStr)) {
			printError("Veuillez saisir un nombre entier de départements.");
			return;
		}
		int nbDepts = Integer.parseInt(nbDeptsStr);

		List<Ville> villes = rec.getVilles();
		Map<String, Departement> mapDepts = new HashMap<>();

		for (Ville ville : villes) {

			Departement departement = mapDepts.get(ville.getCodeDepartement());
			if (departement == null) {
				departement = new Departement(ville.getCodeDepartement());
				mapDepts.put(ville.getCodeDepartement(), departement);
			}
			departement.addVille(ville);
		}

		List<Departement> departements = new ArrayList<Departement>();
		departements.addAll(mapDepts.values());

		Collections.sort(departements, new EnsemblePopComparateur(false));

		for (int i = 0; i < nbDepts; i++) {
			Departement departement = departements.get(i);
			println("Département " + departement.getCode() + " : " + departement.getPopulation() + " habitants.");
		}

	}

}
