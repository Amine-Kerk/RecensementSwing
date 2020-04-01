package fr.diginamic.recensement.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.diginamic.recensement.menu.MenuController;
import fr.diginamic.recensement.menu.TypeTraitement;

/**
 * Classe qui propose quelques méthodes pour construire des composants
 * graphiques
 * 
 * @author RichardBONNAMY
 *
 */
public class SwingUIBuilder {

	/**
	 * FONT_18 : police par défaut utilisée pour la construction de tous les
	 * composants graphiques
	 */
	public static final Font FONT_18 = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	/**
	 * dimension : classe qui fournit des informations sur l'écran du PC utilisé et
	 * notamment ses dimensions. Cette classe intervient dans le calcul de centrage
	 * des composants graphiques
	 */
	private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Constructeur
	 * 
	 */
	public SwingUIBuilder() {
	}

	/**
	 * Méthode utilitaire qui permet de calculer l'abscisse (X) idéale d'un
	 * composant graphique pour que celui-ci soit centré par rapport à l'écran de
	 * l'ordinateur utilisé.
	 * 
	 * @param component composant
	 * @return int
	 */
	public static int getY(Component component) {
		return (int) ((dimension.getHeight() - component.getHeight()) / 2);
	}

	/**
	 * Méthode utilitaire qui permet de calculer l'ordonnée (Y) idéale d'un
	 * composant graphique pour que celui-ci soit centré par rapport à l'écran de
	 * l'ordinateur utilisé.
	 * 
	 * @param component composant
	 * @return int
	 */
	public static int getX(Component component) {
		return (int) ((dimension.getWidth() - component.getWidth()) / 2);
	}

	/**
	 * Méthode qui permet de construire une fenêtre secondaire standardisée. Cette
	 * fenêtre secondaire est le point d'entrée de l'exécution d'un cas
	 * d'utilisation de l'application. <br>
	 * <br>
	 * La fenêtre secondaire est constituée d'un ensemble de couples "libellé/champ
	 * de saisie" et d'un bouton valider. <br>
	 * <br>
	 * Lorsque l'utilisateur clique sur le bouton Valider, le contrôleur est appelé
	 * avec en paramètres l'ensemble des valeurs saisies dans les divers champs de
	 * saisie.
	 * 
	 * @param menuRouter contrôleur qui est appelé lorsque l'utilisateur clique sur
	 *                   le bouton validé.
	 * @param type       type de traitement à déclencher
	 * @param questions  les questions correspondent aux différentes questions qu'on
	 *                   va poser à l'utilisateur. Un couple libellé/champ de saisie
	 *                   est généré pour chaque question de ce tableau.
	 * @return JFrame
	 */
	public JFrame buildJFrame(MenuController menuRouter, TypeTraitement type, String... questions) {

		// Création de la fenêtre secondaire
		JFrame fenetreRecherche = new JFrame();

		// Calcul de son positionnement par défaut et de ses dimensions.
		// La hauteur dépend notamment du nombre de questions qu'on a à poser
		// à l'utilisateur
		fenetreRecherche.setBounds(100, 100, 650, 130 + 40 * (questions.length - 1));

		// Lorsqu'on ferme une fenêtre secondaire on ne souhaite pas arrêter
		// l'application
		// mais simplement la masquer.
		fenetreRecherche.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Dans chaque fenêtre secondaire les composants graphiques seront positionnés
		// en coordonnées x,y d'où la suppression du layout
		fenetreRecherche.getContentPane().setLayout(null);

		// Par défaut les fenêtres secondaires ne sont pas visibles.
		fenetreRecherche.setVisible(false);

		// Calcul des coordonnées x et y pour que la fenêtre secondaire soit centrée.
		int x = getX(fenetreRecherche);
		int y = getY(fenetreRecherche);
		fenetreRecherche.setLocation(x, y);

		// On créé des composants JLabel (libellé) et JTextField (champ de saisie)
		// pour chaque question
		JLabel[] labelSaisies = new JLabel[questions.length];
		JTextField[] saisiesField = new JTextField[questions.length];

		// On réalise une boucle sur le tableau de questions pour générer et positionner
		// les divers éléments sur la fenêtre secondaire
		int maxWidth = 0;
		for (int i = 0; i < questions.length; i++) {

			// Nombre de carcatères dans le texte de la question
			int nbCaracteres = questions[i].length();

			// Largeur approximative en pixels du texte à afficher
			int largeurLabel = nbCaracteres * 10;

			// Création du label
			labelSaisies[i] = new JLabel(questions[i]);
			labelSaisies[i].setBounds(10, 12 + i * 30, largeurLabel, 20);
			labelSaisies[i].setFont(FONT_18);
			fenetreRecherche.add(labelSaisies[i]);

			// Recherche du texte le plus large parmi tous les textes des questions
			if (largeurLabel > maxWidth) {
				maxWidth = largeurLabel;
			}
		}

		// On fait une seconde boucle pour générer les champs de saisie.
		// Ces champs de saisie seront tous aligner à gauche.
		// Le positionnement X est le même pour tous les champs de saisie: maxWidth+5
		for (int i = 0; i < questions.length; i++) {
			saisiesField[i] = new JTextField();
			saisiesField[i].setBounds(maxWidth + 5, 6 + i * 30, fenetreRecherche.getWidth() - maxWidth - 40, 30);
			saisiesField[i].setFont(FONT_18);
			fenetreRecherche.add(saisiesField[i]);
		}

		// Création du bouton Valider
		JButton valider = new JButton("Valider");
		valider.setBounds(260, 45 + 40 * (questions.length - 1), 75, 25);

		// Lorsqu'on clique sur le bouton Valider on appelle
		// la méthode executeUseCase du contrôleur avec toutes les saisies effectuées
		// par l'utilisateur en paramètres de la méthode.
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// display/center the jdialog when the button is pressed

				String[] saisies = new String[saisiesField.length];
				for (int i = 0; i < saisies.length; i++) {
					saisies[i] = saisiesField[i].getText();
				}

				fenetreRecherche.setVisible(false);
				menuRouter.executeUseCase(type, saisies);
			}
		});
		fenetreRecherche.add(valider);
		fenetreRecherche.getRootPane().setDefaultButton(valider);

		return fenetreRecherche;
	}
}
