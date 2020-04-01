package fr.diginamic.recensement;

import static fr.diginamic.recensement.ui.SwingUIBuilder.FONT_18;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.menu.MenuController;
import fr.diginamic.recensement.menu.TypeTraitement;
import fr.diginamic.recensement.ui.MenuItem;
import fr.diginamic.recensement.ui.SwingUIBuilder;

/**
 * Fenêtre principale qui porte les principaux composants graphiques de
 * l'application:<br>
 * - les boutons du menu,<br>
 * - le panneau d'affichage des résultats<br>
 * 
 * @author RichardBONNAMY
 *
 */
public class FenetrePrincipale extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = -5774337273803383053L;
	/** recensement */
	private Recensement recensement;

	/**
	 * Constructeur
	 * 
	 * @param title nom de l'application qui s'affiche dans le bandeau
	 */
	public FenetrePrincipale(String title) {
		setTitle(title);

		// Taille de la fenêtre
		setSize(800, 718);

		// Opération à effectuer lorsque l'utilisateur clique sur la croix de fermeture:
		// EXIT_ON_CLOSE signifie arrêt de l'application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// calcule de la position X de la fenêtre dans l'environnement windows
		int x = SwingUIBuilder.getX(this);

		// calcule de la position Y de la fenêtre dans l'environnement windows
		int y = SwingUIBuilder.getY(this);

		// Mise à jour des coordonnées de l'application dans l'environnement windows.
		// Le but recherché est que l'application soit centrée sur le PC au démarrage
		// Sinon par défaut l'application apparait en haut à gauche.
		setLocation(x, y);
	}

	/**
	 * Construit l'interface graphique principale avec:<br>
	 * - des boutons pour chaque item du menu<br>
	 * - un bouton/gomme permettant d'effacer la zone d'affichage<br>
	 * - une JTextPane qui va permettre d'afficher tout ce qu'on a à afficher.
	 * 
	 */
	public void buildInterfaceGraphique() {

		// La mise à NULL du Layout permet d'afficher tous les éléments de l'interface
		// graphique en coordonnées X, Y
		this.setLayout(null);

		// Création de l'afficheur qui sera ici un JTextPane.
		JTextPane afficheur = new JTextPane();
		afficheur.setBounds(10, 200, 760, 390);
		afficheur.setFont(FONT_18);

		// Création d'un JScrollPane qui va permettre d'ajouter des barres de défilement
		// horizontale et verticale avec curseurs à notre afficheur.
		JScrollPane sp = new JScrollPane(afficheur);
		sp.setBounds(10, 270, 760, 390);

		// On ajoute le JScrollPane à l'interface graphique.
		this.add(sp);

		// Le MenuController va permettre de créer du lien entre les actions de
		// l'utilisateur et les traitements à effectuer.
		MenuController menuRouter = new MenuController(recensement, afficheur);

		// Ajout du bouton en forme de gomme
		ImageIcon image = new ImageIcon(FenetrePrincipale.class.getResource("/gomme.png"));
		JButton boutonGomme = new JButton(image);
		boutonGomme.setBounds(738, 236, 30, 30);

		// Ajout d'un listener sur le bouton. Ce listener permet de déclencher une
		// action lorsque l'utilisateur clique sur le bouton.
		boutonGomme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// L'action ici est d'effacer le contenu de l'afficheur
				afficheur.setText("");
			}
		});

		// Ajout du bouton en forme de gomme à l'interface graphique
		this.add(boutonGomme);

		// j'instancie un builder qui propose notamment une méthode buildJFrame que j'ai
		// écrite pour construire des fenêtres secondaires
		SwingUIBuilder builder = new SwingUIBuilder();

		// Toutes les JFrame ci-dessous correspondent à des fenêtres secondaires qu'on
		// fera apparaitre le moment voulu pour poser des questions à l'utilisateur
		JFrame fenetreRecherchePopVille = builder.buildJFrame(menuRouter, TypeTraitement.RECHERCHE_POP_VILLE,
				"Saisissez le nom de la ville");
		JFrame fenetreRecherchePopDept = builder.buildJFrame(menuRouter, TypeTraitement.RECHERCHE_POP_DEPT,
				"Saisissez le nom du département");
		JFrame fenetreRecherchePopRegion = builder.buildJFrame(menuRouter, TypeTraitement.RECHERCHE_POP_REGION,
				"Saisissez le nom de la région");
		JFrame fenetreRechercheNVillesDept = builder.buildJFrame(menuRouter, TypeTraitement.RECHERCHE_N_VILLES_DEPT,
				"Saisissez le numéro du département", "Saisissez le nombre de villes");
		JFrame fenetreRechercheNVillesRegions = builder.buildJFrame(menuRouter,
				TypeTraitement.RECHERCHE_N_VILLES_REGION, "Saisissez le nom d'une région",
				"Saisissez le nombre de villes");
		JFrame fenetreRechercheNDepartements = builder.buildJFrame(menuRouter, TypeTraitement.RECHERCHE_N_DEPTS,
				"Saisissez le nombre de départements");
		JFrame fenetreRechercheNRegions = builder.buildJFrame(menuRouter, TypeTraitement.RECHERCHE_N_REGIONS,
				"Saisissez le nombre de régions");
		JFrame fenetreRechercheNVillesFrance = builder.buildJFrame(menuRouter, TypeTraitement.RECHERCHE_N_VILLES_FRANCE,
				"Saisissez le nombre de villes");

		// Les MenuItem sont des composants custom composés principalement d'un libellé
		// et d'un bouton
		// la JFrame passée en paramètre du constructeur correspond à la fenêtre
		// secondaire qui doit être affichée lorsque l'utilisateur clique sur le bouton.
		MenuItem menuItem = new MenuItem("1.", "Afficher la population d'une ville.", fenetreRecherchePopVille);
		menuItem.setBounds(10, 10, 500, 40);
		this.add(menuItem);

		MenuItem menuItem2 = new MenuItem("2.", "Afficher la population d'un département.", fenetreRecherchePopDept);
		menuItem2.setBounds(10, 40, 500, 40);
		this.add(menuItem2);

		MenuItem menuItem3 = new MenuItem("3.", "Afficher la population d'une région.", fenetreRecherchePopRegion);
		menuItem3.setBounds(10, 70, 500, 40);
		this.add(menuItem3);

		MenuItem menuItem4 = new MenuItem("4.", "Afficher les plus grandes villes d'un département.",
				fenetreRechercheNVillesDept);
		menuItem4.setBounds(10, 100, 500, 40);
		this.add(menuItem4);

		MenuItem menuItem5 = new MenuItem("5.", "Afficher les plus grandes villes d'une région.",
				fenetreRechercheNVillesRegions);
		menuItem5.setBounds(10, 130, 500, 40);
		this.add(menuItem5);

		MenuItem menuItem6 = new MenuItem("6.", "Afficher les départements les plus peuplés de France.",
				fenetreRechercheNDepartements);
		menuItem6.setBounds(10, 160, 650, 40);
		this.add(menuItem6);

		MenuItem menuItem7 = new MenuItem("7.", "Afficher les régions les plus peuplées de France.",
				fenetreRechercheNRegions);
		menuItem7.setBounds(10, 190, 500, 40);
		this.add(menuItem7);

		MenuItem menuItem8 = new MenuItem("8.", "Afficher les villes les plus peuplées de France.",
				fenetreRechercheNVillesFrance);
		menuItem8.setBounds(10, 220, 500, 40);
		this.add(menuItem8);

		// On rend l'interface graphique visible par défaut, ce qui est préférable.
		this.setVisible(true);
	}

	/**
	 * Getter
	 * 
	 * @return the recensement
	 */
	public Recensement getRecensement() {
		return recensement;
	}

	/**
	 * Setter
	 * 
	 * @param recensement the recensement to set
	 */
	public void setRecensement(Recensement recensement) {
		this.recensement = recensement;
	}
}