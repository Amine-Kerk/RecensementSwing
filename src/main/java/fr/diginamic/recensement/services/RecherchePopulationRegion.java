package fr.diginamic.recensement.services;

import java.util.List;

import javax.swing.JTextPane;

import org.apache.commons.lang3.StringUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'une région
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationRegion extends MenuService {

	private String nomRegion;

	public RecherchePopulationRegion(JTextPane affichage, String nomRegion) {
		super(affichage);
		this.nomRegion = nomRegion;
	}

	@Override
	public void traiter(Recensement rec) {

		if (StringUtils.isEmpty(nomRegion)) {
			printError("Veuillez saisir un nom de région.");
			return;
		}

		List<Ville> villes = rec.getVilles();
		int somme = 0;
		String nom = null;
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase())) {
				somme += ville.getPopulation();
				nom = ville.getNomRegion();
			}
		}
		if (somme > 0) {
			println("Population de la région " + nom + " : " + somme);
		} else {
			printError("Région " + nomRegion + " non trouvée.");
		}
	}

}
