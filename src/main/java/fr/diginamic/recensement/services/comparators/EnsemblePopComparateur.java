package fr.diginamic.recensement.services.comparators;

import java.util.Comparator;

import fr.diginamic.recensement.entites.EnsemblePop;

/** Permet de comparer 2 instances de classe qui implémentent l'interface {@link EnsemblePop}
 * @author DIGINAMIC
 *
 */
public class EnsemblePopComparateur implements Comparator<EnsemblePop>{

	/**  Sens du tri: ascendant (true) ou descendant (false) */
	private boolean asc;
	
	/** Constructeur
	 * @param asc sens du tri
	 */
	public EnsemblePopComparateur(boolean asc){
		this.asc=asc;
	}
	
	@Override
	public int compare(EnsemblePop o1, EnsemblePop o2) {
		if (asc){
			if (o1.getPopulation()>o2.getPopulation()){
				return 1;
			}
			else if (o1.getPopulation()<o2.getPopulation()){
				return -1;
			}
			return 0;
		}
		else {
			if (o1.getPopulation()<o2.getPopulation()){
				return 1;
			}
			else if (o1.getPopulation()>o2.getPopulation()){
				return -1;
			}
			return 0;
		}
	}

}
