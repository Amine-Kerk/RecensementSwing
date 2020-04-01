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
 * Cas d'utilisation: affichage des 10 villes les plus peuplées d'une région
 * donnée
 * 
 * @author DIGINAMIC
 *
 */
public class AfficherVillesPlusPeupleesRegion extends MenuService {

	private String nomRegion;
	private String nbVillesStr;

	public AfficherVillesPlusPeupleesRegion(JTextPane affichage, String nomRegion, String nbVillesStr) {
		super(affichage);
		this.nomRegion = nomRegion;
		this.nbVillesStr = nbVillesStr;
	}

	@Override
	public void traiter(Recensement recensement) {
		if (StringUtils.isEmpty(nomRegion)) {
			printError("Veuillez saisir le nom d'une région.");
			return;
		}
		if (!NumberUtils.isDigits(nbVillesStr)) {
			printError("Veuillez saisir un nombre entier de villes.");
			return;
		}
		int nbVilles = Integer.parseInt(nbVillesStr);

		List<Ville> villesRegions = new ArrayList<Ville>();

		List<Ville> villes = recensement.getVilles();
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase())) {
				villesRegions.add(ville);
			}
		}

		Collections.sort(villesRegions, new EnsemblePopComparateur(false));
		println("Les " + nbVilles + " villes les plus peuplées de la région " + nomRegion + " sont :");
		if (villesRegions.size() > 0) {
			for (int i = 0; i < nbVilles; i++) {
				Ville ville = villesRegions.get(i);
				println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
			}
		} else {
			printError("Région " + nomRegion + " non trouvée.");
		}

	}

}
