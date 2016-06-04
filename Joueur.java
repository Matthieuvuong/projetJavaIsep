package projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Joueur {

	// public ArrayList<Color> couleurInterdite;
	public String name;
	public Text tj;
	public int difficulteeIa;
	public Case caseInitiale;
	public Color couleuInitiale;
	public Color dernierChoix;
	public Color dernierChoixTampon;
	public TreeMap<String, Color> cases;
	public ArrayList<Color> CasesJouablesIa;
	public TreeMap<String, Color> casesPrisesIa;
	private boolean Ia = false;

	// constructeur

	public Joueur() {

		this.casesPrisesIa = new TreeMap<String, Color>();
		this.CasesJouablesIa = new ArrayList<Color>();
		this.cases = new TreeMap<String, Color>();

	}

	// acceseurs

	public Color getCouleuInitiale() {
		return couleuInitiale;
	}

	public void setCouleuInitiale(Color couleuInitiale) {
		this.couleuInitiale = couleuInitiale;
	}

	public Color getDernierChoix() {
		return dernierChoix;
	}

	public void setDernierChoix(Color dernierChoix) {
		this.dernierChoix = dernierChoix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Case getCaseInitiale() {
		return caseInitiale;
	}

	public void setCaseInitiale(Case initiale) {
		this.caseInitiale = initiale;
	}

	public void ajouterCase(int x, int y, Color c) {
		String key = x + "," + y;

		if (!cases.containsKey(key)) {
			cases.put(key, c);
		}
	}

	public void ajouterCasesPrisesIa(int x, int y, Color c) {
		String key = x + "," + y;

		if (!casesPrisesIa.containsKey(key)) {
			casesPrisesIa.put(key, c);
		}
	}

	public void afficherajouterCasesJouables() {
		// changer la couleur de toutes les cases du joeueur
		cases.forEach((k, v) -> System.out.println("key="));

	}

	public boolean absenceCase(int x, int y, Joueur joueur) {
		boolean verif = true;
		int[] tab;
		for (String mapKey : joueur.cases.keySet()) {

			tab = joueur.demo(mapKey);
			if (tab[0] == x && tab[1] == y) {
				verif = false;
			}
		}

		return verif;

	}

	public boolean absenceCase2(int x, int y, Joueur joueur) {
		boolean verif = true;
		int[] tab;
		for (String mapKey : joueur.casesPrisesIa.keySet()) {

			tab = joueur.demo(mapKey);
			if (tab[0] == x && tab[1] == y) {
				verif = false;
			}
		}

		return verif;

	}

	public void changeCouleurCases(Color c) {
		// changer la couleur de toutes les cases du joeueur
		cases.forEach((k, v) -> cases.put(k, c));
	}

	// pour debug
	public void afficherCases() {
		// changer la couleur de toutes les cases du joeueur
		cases.forEach((k, v) -> System.out.println("key=" + k + ";couleur=" + v));

	}

	public int[] demo(String strKey) {
		// System.out.println("strKey"+strKey);
		String[] strCoord = strKey.split(",");
		int x = Integer.parseInt(strCoord[0]);
		// System.out.println("x"+x);
		int y = Integer.parseInt(strCoord[1]);
		// System.out.println("y"+y);
		// System.out.println(x);
		// System.out.println(y);
		int tab[] = { x, y };
		return tab;

	}

	public boolean isIa() {
		return Ia;
	}

	public void setIa(boolean ia) {
		Ia = ia;
	}

	public Color getDernierChoixTampon() {
		return dernierChoixTampon;
	}

	public void setDernierChoixTampon(Color dernierChoixTampon) {
		this.dernierChoixTampon = dernierChoixTampon;
	}

	public int getDifficulteeIa() {
		return difficulteeIa;
	}

	public void setDifficulteeIa(int difficulteeIa) {
		this.difficulteeIa = difficulteeIa;
	}

}
