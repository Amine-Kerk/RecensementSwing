package fr.diginamic.recensement.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;

import fr.diginamic.recensement.entites.Recensement;

/**
 * Classe permettant de lire le contenu d'un fichier
 * 
 * @author DIGINAMIC
 *
 */
public class RecensementUtils {

	/**
	 * Lit le contenu du fichier en paramétre, contenant des données de recensement
	 * avec le format attendu, et retourne une instance de la classe Recensement
	 * avec toutes ses villes
	 * 
	 * @param cheminFichier chemin d'accés au fichier sur le disque dur
	 * @return {@link Recensement}
	 */
	public static Recensement lire(String cheminFichier) {
		Recensement recensement = new Recensement();

		List<String> lignes = null;
		try {
			File file = new File(cheminFichier);
			lignes = FileUtils.readLines(file, "UTF-8");

			// On supprime la ligne d'entéte avec les noms des colonnes
			lignes.remove(0);

			for (String ligne : lignes) {
				ParseurVille.ajoutLigne(recensement, ligne);
			}
			return recensement;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 * Extraction des données de recensement
	 * 
	 * @return Recensement
	 */
	public static Recensement extraireDonnees() {
		// On charge le fichier recensement population 2016.csv
		File file = new File("C:/Temp/recensement population 2016.csv");

		Recensement donneesRecensement = null;
		// Si ce fichier n'existe pas on ouvre un sélecteur de fichier pour aller le
		// chercher sur le disque dur
		if (!file.exists()) {

			// On initialise un sélecteur de fichier qui pointe par défaut sur C:/Temp
			final JFileChooser fc = new JFileChooser("C:/Temp");

			// On récupère le résultat
			int returnVal = fc.showOpenDialog(null);

			// Si l'utilisateur a sélectionné OUVRIR
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				// On récupère le fichier sélectionné
				file = fc.getSelectedFile();

				// On lit les données de recensement
				donneesRecensement = RecensementUtils.lire(file.getAbsolutePath());
			} else {
				System.out.println("Ouverture annulée par l'utilisateur.");
				System.exit(0);
			}
		} else {
			donneesRecensement = RecensementUtils.lire(file.getAbsolutePath());
		}
		return donneesRecensement;
	}
}
