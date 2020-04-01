package fr.diginamic;

import javax.swing.SwingUtilities;

import fr.diginamic.recensement.FenetrePrincipale;

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
				// Création de la fenêtre principale
				final FenetrePrincipale wnd = new FenetrePrincipale("Application recensement");
				wnd.buildInterfaceGraphique();
				// On passe les données à ce composant pour traitement ultérieur

				// On affiche la fenêtre au démarrage
				wnd.setVisible(true);
			}
		});
	}
}