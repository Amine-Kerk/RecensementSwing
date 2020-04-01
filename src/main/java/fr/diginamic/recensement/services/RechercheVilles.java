package fr.diginamic.recensement.services;

import java.awt.Color;
import java.util.List;

import javax.swing.JTextPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes dont la population est comprise
 * entre un min et un max
 * 
 * @author DIGINAMIC
 *
 */
public class RechercheVilles extends MenuService {

	/** nomRegion */
	private String nomRegion;
	/** dept */
	private String dept;
	/** popMinStr */
	private String popMinStr;
	/** popMaxStr */
	private String popMaxStr;

	/**
	 * Constructeur
	 * 
	 * @param affichage
	 * @param popMinStr
	 * @param popMaxStr
	 */
	public RechercheVilles(JTextPane affichage, String nomRegion, String dept, String popMinStr, String popMaxStr) {
		super(affichage);
		this.nomRegion = nomRegion;
		this.dept = dept;
		this.popMinStr = popMinStr;
		this.popMaxStr = popMaxStr;
	}

	@Override
	public void traiter(Recensement rec) {

		if (!NumberUtils.isDigits(popMinStr)) {
			printError("Veuillez saisir une population min entière.");
			return;
		}

		if (!NumberUtils.isDigits(popMaxStr)) {
			printError("Veuillez saisir une population min entière.");
			return;
		}
		int popMin = Integer.parseInt(popMinStr);
		int popMax = Integer.parseInt(popMaxStr);

		int compteur = 0;
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if ((StringUtils.isEmpty(nomRegion)
					|| ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase()))
					&& (StringUtils.isEmpty(dept) || ville.getCodeDepartement().toLowerCase().startsWith(dept))
					&& ville.getPopulation() >= popMin && ville.getPopulation() < popMax) {

				String text = "Région : " + ville.getNomRegion() + " - Département : " + ville.getCodeDepartement()
						+ " - Ville :" + ville.getNom() + " a une population de " + ville.getPopulation()
						+ " habitants.";
				println(text);
				compteur++;
			}
		}
		println(compteur + " villes correspondent aux critères sélectionnés.", Color.GREEN);
	}

}
