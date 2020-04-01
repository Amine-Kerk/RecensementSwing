package fr.diginamic;

import javax.swing.SwingUtilities;

import fr.diginamic.recensement.FenetrePrincipale;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.utils.RecensementUtils;

/**
 * Point d'entrée de l'application: launcher
 * 
 * @author RichardBONNAMY
 *
 */
public class RecensementApplication {

	/**
	 * Point d'entrée
	 * 
	 * @param args non utilisés ici
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				// Lecture des données de recensement
				Recensement donneesRecensement = RecensementUtils.lire("recensement_population_2016.csv");

				// Création de la fenêtre principale
				final FenetrePrincipale wnd = new FenetrePrincipale("Application recensement");
				wnd.setRecensement(donneesRecensement);
				wnd.buildInterfaceGraphique();
				// On passe les données à ce composant pour traitement ultérieur

				// On affiche la fenêtre au démarrage
				wnd.setVisible(true);
			}
		});
	}
}