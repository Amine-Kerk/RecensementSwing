package fr.diginamic.recensement.services;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import fr.diginamic.recensement.entites.Recensement;

/**
 * Classe représentant un service
 * 
 * @author DIGINAMIC
 *
 */
public abstract class MenuService {

	protected JTextPane affichage;

	public MenuService(JTextPane affichage) {
		super();
		this.affichage = affichage;
	}

	/**
	 * Méthode abstraite de traitement que doivent posséder toutes les méthodes de
	 * services
	 * 
	 * @param lignes  lignes du fichier
	 * @param scanner scanner
	 */
	public abstract void traiter(Recensement recensement);

	public void printError(String text) {
		println(text, Color.RED);
	}

	public void println(String text) {
		appendToPane(text + "\n", Color.BLACK);
	}

	public void println(String text, Color c) {
		appendToPane(text + "\n", c);
	}

	protected void appendToPane(String msg, Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		int len = affichage.getDocument().getLength();
		affichage.setCaretPosition(len);
		affichage.setCharacterAttributes(aset, false);
		affichage.replaceSelection(msg);
	}

}
