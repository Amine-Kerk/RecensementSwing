package fr.diginamic.recensement.entites;

/** Représente tout ce qui posséde une population: pays, région, département, canton, ville, etc...
 * @author DIGINAMIC
 *
 */
public interface EnsemblePop {

	/** Retourne la population du regroupement
	 * @return int
	 */
	int getPopulation();
}
