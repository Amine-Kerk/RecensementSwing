package fr.diginamic.recensement.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
			String filePath = RecensementUtils.class.getResource("/" + cheminFichier).getFile();
			File file = new File(filePath);
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
}
