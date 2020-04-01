package fr.diginamic.recensement.menu;

import javax.swing.JTextPane;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.services.AfficherDepartementsPlusPeuplees;
import fr.diginamic.recensement.services.AfficherRegionsPlusPeuplees;
import fr.diginamic.recensement.services.AfficherVillesPlusPeupleesDepartement;
import fr.diginamic.recensement.services.AfficherVillesPlusPeupleesFrance;
import fr.diginamic.recensement.services.AfficherVillesPlusPeupleesRegion;
import fr.diginamic.recensement.services.MenuService;
import fr.diginamic.recensement.services.RecherchePopulationDepartement;
import fr.diginamic.recensement.services.RecherchePopulationRegion;
import fr.diginamic.recensement.services.RecherchePopulationVille;
import fr.diginamic.recensement.services.RechercheVilles;

/**
 * Classe contrôleur
 * 
 * @author RichardBONNAMY
 *
 */
public class MenuController {

	/** données de recensement */
	protected Recensement recensement;
	/** afficheur */
	protected JTextPane afficheur;

	/**
	 * Constructeur
	 * 
	 * @param recensement données de recensement
	 * @param afficheur   afficheur pour les résultats
	 */
	public MenuController(Recensement recensement, JTextPane afficheur) {
		this.recensement = recensement;
		this.afficheur = afficheur;
	}

	/**
	 * Méthode qui exécute un cas d'utilisation en fonction de son type. Les
	 * paramètres sont les saisies effectuées par l'utilisateur.
	 * 
	 * @param typeTraitement identifie un traitement à effectuer
	 * @param parameters     paramètres fournis au traitement
	 */
	public void executeUseCase(TypeTraitement typeTraitement, String... parameters) {

		MenuService rech = null;
		switch (typeTraitement) {
		case RECHERCHE_POP_VILLE:
			rech = new RecherchePopulationVille(afficheur, parameters[0]);
			break;
		case RECHERCHE_POP_DEPT:
			rech = new RecherchePopulationDepartement(afficheur, parameters[0]);
			break;
		case RECHERCHE_POP_REGION:
			rech = new RecherchePopulationRegion(afficheur, parameters[0]);
			break;
		case RECHERCHE_N_VILLES_DEPT:
			rech = new AfficherVillesPlusPeupleesDepartement(afficheur, parameters[0], parameters[1]);
			break;
		case RECHERCHE_N_VILLES_REGION:
			rech = new AfficherVillesPlusPeupleesRegion(afficheur, parameters[0], parameters[1]);
			break;
		case RECHERCHE_N_DEPTS:
			rech = new AfficherDepartementsPlusPeuplees(afficheur, parameters[0]);
			break;
		case RECHERCHE_N_REGIONS:
			rech = new AfficherRegionsPlusPeuplees(afficheur, parameters[0]);
			break;
		case RECHERCHE_N_VILLES_FRANCE:
			rech = new AfficherVillesPlusPeupleesFrance(afficheur, parameters[0]);
			break;
		case RECHERCHE_VILLES:
			rech = new RechercheVilles(afficheur, parameters[0], parameters[1], parameters[2], parameters[3]);
			break;
		}
		if (rech != null) {
			rech.traiter(recensement);
		} else {
			afficheur.setText("Traitement non trouvé.");
		}

	}

}
