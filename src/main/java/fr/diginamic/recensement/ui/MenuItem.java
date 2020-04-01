package fr.diginamic.recensement.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Composant custom composé d'un bouton et d'un libellé. De plus, ce composant
 * reçoit une fenêtre à afficher (JFrame) lorsque le bouton du menu est cliqué.
 * 
 * @author RichardBONNAMY
 *
 */
public class MenuItem extends JComponent {

	/** serialVersionUID */
	private static final long serialVersionUID = -1213747996069300394L;

	/**
	 * Constructeur
	 * 
	 * @param buttonText texte du bouton
	 * @param labelText  libellé à afficher à droite du bouton
	 * @param fenetreSecondaireCible     fenêtre secondaire à afficher lorsque le bouton est cliqué
	 */
	public MenuItem(String buttonText, String labelText, JFrame fenetreSecondaireCible) {
		this.setLayout(null);

		JButton button = new JButton(buttonText);// creating instance of JButton
		button.setMinimumSize(new Dimension(50, 25));// x axis, y axis, width, height
		button.setBounds(10, 10, 50, 25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// display/center the jdialog when the button is pressed
				fenetreSecondaireCible.setVisible(true);
			}
		});

		this.add(button);

		JLabel label = new JLabel(labelText);
		label.setBounds(100, 10, 500, 25);
		label.setMinimumSize(new Dimension(150, 25));
		label.setFont(SwingUIBuilder.FONT_18);
		this.add(label);
	}
}
