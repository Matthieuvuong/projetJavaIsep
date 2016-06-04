package projet;

import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.sound.midi.Synthesizer;

import org.omg.Messaging.SyncScopeHelper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Jeu extends Application {

	public Plateau plateau;
	public static HashMap<Color, Rectangle> choix = new HashMap();
	Joueur[] tabJoueur;
	Color selection;
	Button[] tabButton = new Button[3];
	int tour = 0;
	int max = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Application.launch(Jeu.class, args);

	}

	// Création de la grille
	@Override
	public void start(Stage stage) throws Exception {

		Group root = new Group(); // on définit un groupe pour le nombre de
									// joueur
		// on définit un groupe pour afficher la grille
		stage.setWidth(500);
		stage.setHeight(400);
		; // Couleur de la scène du Canvas
		stage.setTitle("Jeu des 6 couleurs!");

		// on créer la fenêtre qui demande de choisir un joueur
		Scene sceneNbJoueur = new Scene(root);
		sceneNbJoueur.setFill(Color.GREY);
		stage.setScene(sceneNbJoueur);
		stage.show();

		Text t = new Text(100, 100, "Veuillez choisir un joueur");
		t.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		t.setFill(Color.BLACK);
		root.getChildren().add(t);

		// on récupèe un tableau de bouton grâce à la fonction buttonAccueil
		plateau = new Plateau(13, stage);

		Button[] tabButton = bouttonAccueil(stage);

		root.getChildren().add(tabButton[0]);
		tabButton[0].addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

			stage.setWidth(500);
			stage.setHeight(500);

			Group Ia = new Group();
			Scene scene = new Scene(Ia);
			scene.setFill(Color.GREY);
			stage.setScene(scene);
			stage.show();

			int nbJoueur = 2;
			Text t1 = new Text(80, 50, "Combien d'IA pour : " + nbJoueur + " Joueurs");
			t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			t1.setFill(Color.BLACK);
			Ia.getChildren().add(t1);
			Button[] tabButtonIa = bouttonNombreIa(stage, nbJoueur);
			for (int i = 0; i < nbJoueur; i++) {
				Ia.getChildren().add(tabButtonIa[i]);

			}

		});

		root.getChildren().add(tabButton[1]);

		tabButton[1].addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

			stage.setWidth(500);
			stage.setHeight(500);

			Group Ia = new Group();
			Scene scene = new Scene(Ia);
			scene.setFill(Color.GREY);
			stage.setScene(scene);
			stage.show();

			int nbJoueur = 3;
			Button[] tabButtonIa = bouttonNombreIa(stage, nbJoueur);
			Text t1 = new Text(80, 50, "Combien d'IA pour : " + nbJoueur + " Joueurs");
			t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			t1.setFill(Color.BLACK);
			Ia.getChildren().add(t1);
			for (int i = 0; i < nbJoueur; i++) {
				Ia.getChildren().add(tabButtonIa[i]);
			}

		});

		root.getChildren().add(tabButton[2]);
		tabButton[2].addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			stage.setWidth(500);
			stage.setHeight(500);

			Group Ia = new Group();
			Scene scene = new Scene(Ia);
			scene.setFill(Color.GREY);
			stage.setScene(scene);
			stage.show();

			int nbJoueur = 4;
			Text t1 = new Text(80, 50, "Combien d'IA pour : " + nbJoueur + " Joueurs");
			t1.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			t1.setFill(Color.BLACK);
			Ia.getChildren().add(t1);
			Button[] tabButtonIa = bouttonNombreIa(stage, nbJoueur);
			for (int i = 0; i < nbJoueur; i++) {
				Ia.getChildren().add(tabButtonIa[i]);
			}

		});

	}

	public void secondAfficage(Stage stage, Joueur[] joueurs) {

		stage.setWidth(1000);
		stage.setHeight(800);

		Case[][] grille = plateau.getGrille();// on récupérer le contenue de la
												// grille de cases que l'on a
												// instancier avec le
												// constructeur.
		// On appel la fonction nombre Joueur qui permet d'instancier le nombre
		// de joueur demander

		Group root2 = new Group();
		Scene scene = new Scene(root2); // on définit une scène qui contiendra
										// le décore
		scene.setFill(Color.GREY); // Couleur de la scène du Canvas
		int y = 40;

		// System.out.println("******** dessiner la grille *******");
		for (int i = 0; i < 13; ++i) {
			int x = 10;
			for (int j = 0; j < 13; ++j) {
				Color c = grille[i][j].getCouleur(); // on récupére la couleur
														// pour chaque cases de
														// ma grille.
				// on appel la fonction afficherRectangle de l'instance plateau
				// pour afficher graphiquement un rectangle
				Rectangle r = plateau.afficherRectangle(y, x, 50, 50, c, Color.BLACK);
				root2.getChildren().add(r);
				grille[i][j].setRectangle(r);

				grille[i][j].setxRectangle(y);
				grille[i][j].setYRectangle(x);

				x = x + 50;
			}
			y = y + 50;
		}

		// on affiche le score de chaque joueur
		joueurs[0].tj = new Text(770, 140, "Joueur1 :  " + joueurs[0].cases.size() + "case");
		joueurs[0].tj.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		joueurs[0].tj.setFill(Color.BLACK);
		root2.getChildren().add(joueurs[0].tj);

		joueurs[1].tj = new Text(770, 170, "Joueur2 :  " + joueurs[1].cases.size() + "case");
		joueurs[1].tj.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		joueurs[1].tj.setFill(Color.BLACK);
		root2.getChildren().add(joueurs[1].tj);

		// on met en blanc le contour de la case du joueur qui commence
		majNumCases(joueurs[0], grille, root2, 0);
		majContourCases(joueurs[0], grille, root2, 0, Color.WHITE);
		majNumCases(joueurs[1], grille, root2, 1);

		// position graphique des joueurs.

		// affecter la première couleur de chaque joueur à sa première case
		grille[0][12].setCouleur(joueurs[0].couleuInitiale);
		grille[12][0].setCouleur(joueurs[1].couleuInitiale);

		if (joueurs.length >= 3) {
			grille[12][12].setCouleur(joueurs[2].couleuInitiale);
			majNumCases(joueurs[2], grille, root2, 2);
			joueurs[2].tj = new Text(770, 200, "Joueur3 :  " + joueurs[0].cases.size() + "case");
			joueurs[2].tj.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			joueurs[2].tj.setFill(Color.BLACK);
			root2.getChildren().add(joueurs[2].tj);

		}

		if (joueurs.length == 4) {
			grille[0][0].setCouleur(joueurs[3].couleuInitiale);
			majNumCases(joueurs[3], grille, root2, 3);
			joueurs[3].tj = new Text(770, 230, "Joueur4 :  " + joueurs[0].cases.size() + "case");
			joueurs[3].tj.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			joueurs[3].tj.setFill(Color.BLACK);
			root2.getChildren().add(joueurs[3].tj);
		}

		// on affiche les rectangles de couleur qu'un joueur pourra
		// sélectionner:
		int x2 = 700;
		int y2 = 100;

		for (int i = 0; i < Case.tableauCouleur.length; i++) {
			selection = Case.tableauCouleur[i];
			System.out.println("****Couleur=" + selection);
			Rectangle r = plateau.afficherRectangle(x2, y2, 50, 50, selection, Color.BLACK);

			r.setAccessibleText(selection + "");

			r.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {

					if (tour == tabJoueur.length) {
						tour = 0;
					}

					// on appel la fonction qui permet de capturer des cases.
					captureCases(joueurs, grille, Color.valueOf(r.getAccessibleText()), tour, root2, stage);
					// Jeu.secondAfficage(stage, nbJoueur, tabJoueur);
					if (tour + 1 > tabJoueur.length - 1) {

						tour = 0;

					} else {
						tour = tour + 1;
					}

					while (tabJoueur[tour].isIa() == true && finparie2(tabJoueur, grille) != true) {

						if (tabJoueur[tour].getDifficulteeIa() == 1) {
							coupAleatoire(tabJoueur[tour], grille, stage, root2, tour);
						}
						if (tabJoueur[tour].getDifficulteeIa() == 2) {
							coupBloquant(tabJoueur[tour], grille, stage, root2, tour);
						}
						if (tabJoueur[tour].getDifficulteeIa() == 3) {
							meilleurCoup(tabJoueur[tour], grille, stage, root2, tour);
						}

						initCasesChoix();

						if (tour + 1 > tabJoueur.length - 1) {

							tour = 0;

						} else {
							tour = tour + 1;
						}

					}

				}
			});

			choix.put(selection, r);

			root2.getChildren().add(r);
			y2 = y2 + 55;

		}

		// désactiver les couleurs des autres pour le premier joueur
		for (int i = 0; i < tabJoueur.length; i++) {

			Rectangle r = choix.get(tabJoueur[i].getCouleuInitiale());
			r.setFill(Color.GREY);
			r.setMouseTransparent(true);
		}

		stage.setScene(scene);
		stage.show();
	}

	// affichage de fin de partie
	public void affichageFin(Stage stage, int i) {

		stage.setWidth(500);
		stage.setHeight(500);

		Group root = new Group();
		Scene scene = new Scene(root); // on définit une scène qui contiendra le
										// décore
		scene.setFill(Color.GREY); // Couleur de la scène du Canvas
		stage.setScene(scene);
		Text t = new Text(70, 200, "Joueur " + i + " gagne !");
		t.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
		t.setFill(Color.BLACK);
		root.getChildren().add(t);
		stage.show();

	}

	// fonction qui dessine un haxagone
	public Polygon affichageHexagone(Stage stage, int x, int y) {
		double cote = 100;
		double largeur = cote / 2;
		Polygon polygon = new Polygon();
		polygon.getPoints()
				.addAll(new Double[] {

				cote, 0.0, cote * 2, 0.0, cote * 2 + largeur, cote, cote * 2, cote * 2, cote, cote * 2, largeur,
						cote });

		polygon.setLayoutX(100);
		polygon.setLayoutY(100);

		return polygon;

	}

	public Button[] bouttonAccueil(Stage stage) {

		// bouton pour 2 joueurs
		Button b1 = new Button("Deux Joueurs");
		b1.setLayoutX(30);
		b1.setLayoutY(150);

		// Taille du bouton
		b1.setPrefSize(120, 100);
		tabButton[0] = b1;

		// bouton pour 3 joueurs
		Button b2 = new Button("Trois Joueurs");
		b2.setLayoutX(180);
		b2.setLayoutY(150);

		// Taille du bouton
		b2.setPrefSize(120, 100);
		tabButton[1] = b2;

		// bouton pour 4 joueurs
		Button b3 = new Button("Quatres Joueurs");
		b3.setLayoutX(330);
		b3.setLayoutY(150);

		// Taille du bouton
		b3.setPrefSize(120, 100);
		tabButton[2] = b3;

		return tabButton;

	}

	// Les bouttons pour choisir le nombre d'IA
	public Button[] bouttonNombreIa(Stage stage, int nombreJoueur) {

		// bouton pour 0 IA
		Button b1 = new Button("Aucune IA");
		b1.setLayoutX(100);
		b1.setLayoutY(100);
		b1.setPrefSize(120, 100);
		b1.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			int Ia = 0;
			int difficulte = 0;
			// System.out.println("******** CLICK 2*******");
			Joueur[] tabJoueur = nombreJoueur(nombreJoueur, plateau.getGrille(), Ia, difficulte);
			secondAfficage(stage, tabJoueur);
		});

		// bouton pour 1 IA
		Button b2 = new Button("Une IA");
		b2.setLayoutX(250);
		b2.setLayoutY(100);
		b2.setPrefSize(120, 100);
		b2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			// System.out.println("******** CLICK 3*******");
			int Ia = 1;
			// Joueur[] tabJoueur = nombreJoueur(nombreJoueur,
			// plateau.getGrille(), Ia);
			// secondAfficage(stage, tabJoueur);
			bouttonNiveauIA(stage, Ia, nombreJoueur);

		});
		// boutton pour 2Ia
		if (nombreJoueur >= 3) {
			Button b3 = new Button("Deux IA");
			b3.setLayoutX(100);
			b3.setLayoutY(220);
			b3.setPrefSize(120, 100);
			b3.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
				int Ia = 2;
				bouttonNiveauIA(stage, Ia, nombreJoueur);

			});

			if (nombreJoueur == 3) {
				Button[] tabButton = { b1, b2, b3 };
				return tabButton;
			}

			if (nombreJoueur == 4) {
				// bouton pour 3 IA
				int Ia = 3;
				Button b4 = new Button("Trois IA");
				b4.setLayoutX(250);
				b4.setLayoutY(220);
				b4.setPrefSize(120, 100);
				b4.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
					bouttonNiveauIA(stage, Ia, nombreJoueur);

				});
				Button[] tabButton = { b1, b2, b3, b4 };
				return tabButton;

			}

		}

		Button[] tabButton = { b1, b2 };
		return tabButton;

	}

	public void bouttonNiveauIA(Stage stage, int Ia, int nombreJoueur) {

		stage.setWidth(500);
		stage.setHeight(400);

		Group niveauIA = new Group();
		Scene scene = new Scene(niveauIA);
		scene.setFill(Color.GREY);
		stage.setScene(scene);
		Text t = new Text(140, 100, "Style de Jeu de l'IA");
		t.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		t.setFill(Color.BLACK);
		niveauIA.getChildren().add(t);
		stage.show();

		// bouton pour coup Aleatoire
		Button b1 = new Button("Aléatoire");
		b1.setLayoutX(30);
		b1.setLayoutY(150);

		// Taille du bouton
		b1.setPrefSize(120, 100);
		niveauIA.getChildren().add(b1);

		// Si on appuie sur le boutton alors on appel la fonction second
		// affichage

		b1.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			int difficulte = 1;
			Joueur[] tabJoueur = nombreJoueur(nombreJoueur, plateau.getGrille(), Ia, difficulte);
			secondAfficage(stage, tabJoueur);

		});

		// bouton pour coup bloquant
		Button b2 = new Button("Bloquant");
		b2.setLayoutX(180);
		b2.setLayoutY(150);

		// Taille du bouton
		b2.setPrefSize(120, 100);
		niveauIA.getChildren().add(b2);

		// Si on appuie sur le boutton alors on appel la fonction second
		// affichage

		b2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			int difficulte = 2;
			Joueur[] tabJoueur = nombreJoueur(nombreJoueur, plateau.getGrille(), Ia, difficulte);
			secondAfficage(stage, tabJoueur);

		});

		// bouton pour coup rapportant le plus de cases
		Button b3 = new Button("Meilleur coup");
		b3.setLayoutX(330);
		b3.setLayoutY(150);

		// Taille du bouton
		b3.setPrefSize(120, 100);
		niveauIA.getChildren().add(b3);

		// Si on appuie sur le boutton alors on appel la fonction second
		// affichage

		b3.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			int difficulte = 3;
			Joueur[] tabJoueur = nombreJoueur(nombreJoueur, plateau.getGrille(), Ia, difficulte);
			secondAfficage(stage, tabJoueur);

		});

	}

	public boolean finparie2(Joueur[] tabJoueur, Case[][] grille) {
		boolean finPartie2 = true;
		int casesLibre;
		int casesPrises = 0;
		int max = 0;
		int position = 0;
		int[] tab = new int[tabJoueur.length];

		// fonction qui retourne le nombre de points du joueur qui en a le plus
		for (int i = 0; i < tabJoueur.length; i++) {

			tab[i] = tabJoueur[i].cases.size();
			System.out.println(tab[i]);
			casesPrises = casesPrises + tabJoueur[i].cases.size();
		}

		casesLibre = (grille.length * grille[0].length) - casesPrises;
		System.out.println("case libres : " + casesLibre);
		System.out.println("cases prises : " + casesPrises);

		for (int i = 0; i < tabJoueur.length; i++) {

			if (tab[i] > max) {
				max = tab[i];
				position = i;
			}
		}

		for (int i = 0; i < tabJoueur.length; i++) {
			if (i != position && max < tab[i] + casesLibre) {
				finPartie2 = false;
				return finPartie2;
			}
		}

		return finPartie2;

	}

	public void captureCases(Joueur[] tabJoueur, Case[][] grille, Color couleurChoisie, int tour, Group root2,
			Stage stage) {

		Joueur Joueur1 = tabJoueur[0];
		Joueur Joueur2 = tabJoueur[1];

		for (int i = 0; i < Case.tableauCouleur.length; i++) {
			Rectangle r = choix.get(Case.tableauCouleur[i]);
			r.setFill(Case.tableauCouleur[i]);
			r.setMouseTransparent(false);

		}

		// joueur 1 joue
		if (tour == 0) {
			// System.out.println("joueur 1 joue");

			if (Joueur1.isIa() == false) {
				Joueur1.setDernierChoix(couleurChoisie);
				Joueur1.setDernierChoixTampon(couleurChoisie);

				// fonction qui désactive les rectangles de choix qu'un joueur
				// ne peux sélectionner
				initCasesChoix();

				int tab[];

				for (String mapKey = Joueur1.cases.firstKey(); mapKey != null; mapKey = Joueur1.cases
						.higherKey(mapKey)) {

					tab = Joueur1.demo(mapKey);
					deplacementJoueur(tab[0], tab[1], Joueur1, grille);
					grille[tab[0]][tab[1]].setCouleur(couleurChoisie);

				}

				majNumCases(Joueur1, grille, root2, tour);
				majContourCases(tabJoueur[tour], grille, root2, tour, Color.BLACK);
				majContourCases(tabJoueur[tour + 1], grille, root2, tour, Color.WHITE);
				majPoint(tabJoueur[tour], tour);

				if (Joueur1.cases.size() >= max) {

					max = Joueur1.cases.size();
				}
				if (finparie2(tabJoueur, grille) == true) {
					affichageFin(stage, tour + 1);
				}

			}

		}

		if (tour == 1) {

			if (Joueur2.isIa() == false) {
				Joueur2.setDernierChoix(couleurChoisie);
				Joueur2.setDernierChoixTampon(couleurChoisie);
				// fonction qui désactive les couleurs qu'un joueur ne peux
				// jouer
				initCasesChoix();

				int tab[];

				for (String mapKey = Joueur2.cases.firstKey(); mapKey != null; mapKey = Joueur2.cases
						.higherKey(mapKey)) {

					tab = Joueur2.demo(mapKey);
					deplacementJoueur(tab[0], tab[1], Joueur2, grille);
					grille[tab[0]][tab[1]].setCouleur(Joueur2.getDernierChoix());

				}
				majPoint(tabJoueur[tour], tour);
				majContourCases(tabJoueur[tour], grille, root2, tour, Color.BLACK);
				majNumCases(Joueur2, grille, root2, tour);
				if (tour + 1 >= tabJoueur.length) {
					tour = 0;
					if (finparie2(tabJoueur, grille) == true) {
						affichageFin(stage, tour + 1);
					}
					majContourCases(tabJoueur[tour], grille, root2, tour, Color.WHITE);
				} 
				
				else {
					if (finparie2(tabJoueur, grille) == true) {
						affichageFin(stage, tour + 1);
					}
					majContourCases(tabJoueur[tour + 1], grille, root2, tour, Color.WHITE);
				}

				if (Joueur2.cases.size() >= max) {

					max = Joueur2.cases.size();
				}

			}

		}

		if (tabJoueur.length >= 3) {
			if (tour == 2) {
				// System.out.println("joueur 3 joue");
				Joueur Joueur3 = tabJoueur[2];
				Joueur3.setDernierChoix(couleurChoisie);
				Joueur3.setDernierChoixTampon(couleurChoisie);

				// fonction qui désactive les couleurs qu'un joueur ne peux
				// jouer
				initCasesChoix();
				int tab[];

				for (String mapKey = Joueur3.cases.firstKey(); mapKey != null; mapKey = Joueur3.cases
						.higherKey(mapKey)) {
					tab = Joueur3.demo(mapKey);
					deplacementJoueur(tab[0], tab[1], Joueur3, grille);
					plateau.grille[tab[0]][tab[1]].setCouleur(Joueur3.getDernierChoix());
				}

				majPoint(tabJoueur[tour], tour);
				majContourCases(tabJoueur[tour], grille, root2, tour, Color.BLACK);
				majNumCases(Joueur3, grille, root2, tour);
				if (tour + 1 >= tabJoueur.length) {
					if (finparie2(tabJoueur, grille) == true) {
						affichageFin(stage, tour + 1);
					}
					tour = 0;
					majContourCases(tabJoueur[tour], grille, root2, tour, Color.WHITE);
				} 
				else {
					if (finparie2(tabJoueur, grille) == true) {
						affichageFin(stage, tour + 1);
					}
					majContourCases(tabJoueur[tour + 1], grille, root2, tour, Color.WHITE);
				}

				if (Joueur3.cases.size() >= max) {

					max = Joueur3.cases.size();
				}

			}

		}

		if (tabJoueur.length == 4) {
			if (tour == 3) {
				// System.out.println("joueur 4 joue");
				Joueur Joueur4 = tabJoueur[3];
				Joueur4.setDernierChoix(couleurChoisie);
				Joueur4.setDernierChoixTampon(couleurChoisie);
				// fonction qui désactive les couleurs qu'un joueur ne peux
				// jouer
				initCasesChoix();
				int tab[];

				for (String mapKey = Joueur4.cases.firstKey(); mapKey != null; mapKey = Joueur4.cases
						.higherKey(mapKey)) {

					tab = Joueur4.demo(mapKey);
					deplacementJoueur(tab[0], tab[1], Joueur4, grille);
					plateau.grille[tab[0]][tab[1]].setCouleur(Joueur4.getDernierChoix());

				}

				majPoint(tabJoueur[tour], tour);
				majContourCases(tabJoueur[tour], grille, root2, tour, Color.BLACK);
				majNumCases(Joueur4, grille, root2, tour);
				if (tour + 1 >= tabJoueur.length) {
					if (finparie2(tabJoueur, grille) == true) {
						affichageFin(stage, tour + 1);
					}
					tour = 0;
					majContourCases(tabJoueur[tour], grille, root2, tour, Color.WHITE);
				} 
				else {
					
					if (finparie2(tabJoueur, grille) == true) {
						affichageFin(stage, tour + 1);
					}
					majContourCases(tabJoueur[tour + 1], grille, root2, tour, Color.WHITE);
				}
					
                //
				if (Joueur4.cases.size() >= max) {

					max = Joueur4.cases.size();
				}

			}
		}
	}

	public static void deplacementJoueur(int x, int y, Joueur Joueur1, Case[][] grille) {

		// se déplacer à droite

		int xd = x + 1;
		System.out.println(Joueur1.absenceCase2(xd, y, Joueur1));
		if (xd <= 12 && xd >= 0 && Joueur1.absenceCase(xd, y, Joueur1) == true) {
			if (grille[xd][y].getCouleur().equals(Joueur1.getDernierChoix())) {

				;
				Joueur1.ajouterCase(xd, y, grille[xd][y].getCouleur());
				deplacementJoueur(xd, y, Joueur1, grille);
			}

		}

		// se déplacer en haut
		int yh = y - 1;
		if (yh >= 0 && yh <= 12 && Joueur1.absenceCase(x, yh, Joueur1) == true) {
			if (grille[x][yh].getCouleur().equals(Joueur1.getDernierChoix())) {

				Joueur1.ajouterCase(x, yh, grille[x][yh].getCouleur());
				deplacementJoueur(x, yh, Joueur1, grille);

			}
		}

		// se déplacer à Gauche
		int xg = x - 1;

		if (xg <= 12 && xg >= 0 && Joueur1.absenceCase(xg, y, Joueur1) == true) {

			if (grille[xg][y].getCouleur().equals(Joueur1.getDernierChoix())) {

				Joueur1.ajouterCase(xg, y, grille[xg][y].getCouleur());
				deplacementJoueur(xg, y, Joueur1, grille);
			}
		}

		// se déplacer en bas
		int yb = y + 1;
		if (yb >= 0 && yb <= 12 && Joueur1.absenceCase(x, yb, Joueur1) == true) {

			if (grille[x][yb].getCouleur().equals(Joueur1.getDernierChoix())) {

				Joueur1.ajouterCase(x, yb, grille[x][yb].getCouleur());
				deplacementJoueur(x, yb, Joueur1, grille);
			}
		}

	}

	// fonction métiers

	// fonction qui instancie un nombre joueur en fonction de la valeur passé en
	// paramétre et attribus une couleur
	public Joueur[] nombreJoueur(int nombreJoueur, Case[][] grille, int Ia, int difficulte) {
		ArrayList<Color> couleurInterdite = new ArrayList<Color>();
		int nombreAleatoire;
		Color couleur;
		tabJoueur = new Joueur[nombreJoueur];
		for (int i = 0; i < nombreJoueur; i++) {

			tabJoueur[i] = new Joueur();
			Random rand = new Random();
			nombreAleatoire = rand.nextInt(6);
			couleur = Case.tableauCouleur[nombreAleatoire];

			while (couleurInterdite.contains(couleur)) {
				nombreAleatoire = rand.nextInt(6);
				couleur = Case.tableauCouleur[nombreAleatoire];
			}
			Case caseIniJoueur = new Case(couleur);
			tabJoueur[i].setCaseInitiale(caseIniJoueur);
			tabJoueur[i].setCouleuInitiale(couleur);
			tabJoueur[i].setDernierChoix(couleur);
			tabJoueur[i].setDernierChoixTampon(couleur);
			couleurInterdite.add(couleur);
		}

		Joueur Joueur1 = tabJoueur[0];
		Joueur1.getCaseInitiale().setX(0);
		Joueur1.getCaseInitiale().setY(12);
		Joueur1.ajouterCase(0, 12, Joueur1.getCaseInitiale().getCouleur());

		Joueur Joueur2 = tabJoueur[1];
		Joueur2.getCaseInitiale().setX(12);
		Joueur2.getCaseInitiale().setY(0);

		if (Ia >= 1) {
			Joueur2.setIa(true);
			Joueur2.setDifficulteeIa(difficulte);
		}

		Joueur2.ajouterCase(12, 0, Joueur2.getCaseInitiale().getCouleur());

		if (nombreJoueur >= 3) {
			Joueur Joueur3 = tabJoueur[2];
			Joueur3.getCaseInitiale().setX(12);
			Joueur3.getCaseInitiale().setY(0);
			if (Ia >= 2) {
				Joueur3.setIa(true);
				Joueur3.setDifficulteeIa(difficulte);
			}
			Joueur3.setCaseInitiale(grille[12][12]);
			Joueur3.ajouterCase(12, 12, Joueur3.getCaseInitiale().getCouleur());
		}

		if (nombreJoueur == 4) {
			Joueur Joueur4 = tabJoueur[3];
			Joueur4.getCaseInitiale().setX(12);
			Joueur4.getCaseInitiale().setY(0);
			if (Ia == 3) {
				Joueur4.setIa(true);
				Joueur4.setDifficulteeIa(difficulte);
			}
			Joueur4.setCaseInitiale(grille[0][0]);
			Joueur4.ajouterCase(0, 0, Joueur4.getCaseInitiale().getCouleur());
		}

		return tabJoueur;
	}

	// on désactive les couleurs que l'on ne peux pas jouer
	public void desactiveCouleur() {
		for (int i = 0; i < tabJoueur.length; i++) {
			{
				System.out.println("****cinit=" + tabJoueur[i].getDernierChoix());
				Rectangle r = choix.get(tabJoueur[i].getDernierChoix());

				r.setFill(Color.GREY);
				r.setMouseTransparent(true);
			}
		}
	}

	public void initCasesChoix() {

		for (int i = 0; i < Case.tableauCouleur.length; i++) {
			Rectangle r = choix.get(Case.tableauCouleur[i]);
			r.setFill(Case.tableauCouleur[i]);
			r.setMouseTransparent(false);
		}

		desactiveCouleur();

	}

	// fonction permet de déterminer les cases qu'un joueur peux jouer

	public void casesJouables(Joueur Joueur) {
		boolean caseJouable;

		// Ici on récupère les couleurs qu'un joueur peux jouer
		for (int i = 0; i < Case.tableauCouleur.length; i++) {
			caseJouable = true;

			for (int j = 0; j < tabJoueur.length; j++) {

				if (tabJoueur[j].getDernierChoix().equals(Case.tableauCouleur[i])) {
					// System.out.println("passe if 1");
					caseJouable = false;
					break;
				}

			}

			if (caseJouable == true) {
				// System.out.println("passe if 2");
				Joueur.CasesJouablesIa.add(Case.tableauCouleur[i]);
			}

		}

	}

	// fonction qui permet de sélectionner la case qui rapporte le plus de
	// points
	// @SuppressWarnings("unchecked")
	public void meilleurCoup(Joueur Joueur, Case[][] grille, Stage stage, Group root2, int tour) {
		// on détermine les cases qu'un joueur peux jouer
		casesJouables(Joueur);
		for (int i = 0; i < Joueur.CasesJouablesIa.size(); i++) {
			System.out.println(Joueur.CasesJouablesIa.get(i));
		}

		int[] tab;
		// int[] tab2;
		int score = 0;
		Color meilleurChoix = null;

		System.out.println("cases CasesJouablesIa" + Joueur.CasesJouablesIa.size());

		for (int i = 0; i < Joueur.CasesJouablesIa.size(); i++) {

			Joueur.setDernierChoix(Joueur.CasesJouablesIa.get(i));

			// on copie les cases du joueur dans le tampon casesPrisesIa

			// tampon
			@SuppressWarnings("unchecked")
			TreeMap<String, Color> treeTmp = (TreeMap<String, Color>) Joueur.cases.clone();

			for (String mapKey = Joueur.cases.firstKey(); mapKey != null; mapKey = Joueur.cases.higherKey(mapKey)) {

				tab = Joueur.demo(mapKey);
				deplacementJoueur(tab[0], tab[1], Joueur, grille);

			}

			// nouveau score

			if (Joueur.cases.size() > score) {
				score = Joueur.cases.size();
				meilleurChoix = Joueur.getDernierChoix();

			}

			// on remet les cases de départ du joeuer
			Joueur.cases = treeTmp;
		}

		// jouer la meilleur couleur

		Joueur.setDernierChoix(meilleurChoix);
		for (String mapKey = Joueur.cases.firstKey(); mapKey != null; mapKey = Joueur.cases.higherKey(mapKey)) {

			tab = Joueur.demo(mapKey);
			deplacementJoueur(tab[0], tab[1], Joueur, grille);
			grille[tab[0]][tab[1]].setCouleur(meilleurChoix);

		}

		Joueur.CasesJouablesIa.clear();

		majPoint(tabJoueur[tour], tour);
		majContourCases(tabJoueur[tour], grille, root2, tour, Color.BLACK);
		majNumCases(Joueur, grille, root2, tour);

		if (tour + 1 >= tabJoueur.length) {

			if (finparie2(tabJoueur, grille) == true) {
				affichageFin(stage, tour + 1);
			}

			tour = 0;
			majContourCases(tabJoueur[tour], grille, root2, tour, Color.WHITE);
		} else {

			if (finparie2(tabJoueur, grille) == true) {
				affichageFin(stage, tour + 1);
			}
			majContourCases(tabJoueur[tour + 1], grille, root2, tour, Color.WHITE);
		}

	}

	// fonction qui permet à l'IA de jouer un coups aléatoirement
	public void coupAleatoire(Joueur Joueur, Case[][] grille, Stage stage, Group root2, int tour) {
		int[] tab;
		casesJouables(Joueur);
		for (int i = 0; i < Joueur.CasesJouablesIa.size(); i++) {
			System.out.println(Joueur.CasesJouablesIa.get(i));
		}

		// l'IA choisit aléatoirement sa couleur
		Random rand = new Random();

		int taille = Joueur.CasesJouablesIa.size();
		int nombreAleatoire = rand.nextInt(taille);
		Color couleurJouer = Joueur.CasesJouablesIa.get(nombreAleatoire);

		// on set la couleut choisie par l'IA comme dernier choix
		Joueur.setDernierChoix(couleurJouer);

		// on applique la capture de cases par l'Ia avec le dernier choix
		for (String mapKey = Joueur.cases.firstKey(); mapKey != null; mapKey = Joueur.cases.higherKey(mapKey)) {

			tab = Joueur.demo(mapKey);
			deplacementJoueur(tab[0], tab[1], Joueur, grille);
			grille[tab[0]][tab[1]].setCouleur(Joueur.getDernierChoix());

		}

		Joueur.CasesJouablesIa.clear();

		majPoint(tabJoueur[tour], tour);
		majContourCases(tabJoueur[tour], grille, root2, tour, Color.BLACK);
		majNumCases(Joueur, grille, root2, tour);

		if (tour + 1 >= tabJoueur.length) {
			if (finparie2(tabJoueur, grille) == true) {
				affichageFin(stage, tour + 1);
			}
			tour = 0;
			majContourCases(tabJoueur[tour], grille, root2, tour, Color.WHITE);
		} else {

			majContourCases(tabJoueur[tour + 1], grille, root2, tour, Color.WHITE);
			if (finparie2(tabJoueur, grille) == true) {
				affichageFin(stage, tour + 1);
			}

		}

	}

	// fonction qui empéche le joueur qui à le plus de cases de jouer son
	// meilleur coup
	public void coupBloquant(Joueur Joueur, Case[][] grille, Stage stage, Group root2, int tour) {
		int[] tab;
		int[] tab2;
		int score = 0;

		int position = 0;
		Color meilleurChoix = null;

		// si le joueur est celui qui possede le plus de cases alors il joue son
		// meilleur coup
		System.out.println("MAX : " + max);
		System.out.println("NOMBRES CASES DU JOUEUR " + Joueur.cases.size());
		if (Joueur.cases.size() >= max) {
			System.out.println("IA JOUE UN COUPE ALEATOIRE");
			coupAleatoire(Joueur, grille, stage, root2, tour);
			max = Joueur.cases.size();
		}

		// sinon il empeche celui qui a le plus de cases de selectionner la case
		// qui lui rapporte le plus
		else {
			System.out.println("IA EMPECHE DE JOUER");
			for (int i = 0; i < tabJoueur.length; i++) {
				if (tabJoueur[i].cases.size() > max) {
					max = tabJoueur[i].cases.size();
					position = i;
				}
			}

			casesJouables(tabJoueur[position]);
			for (int i = position; i < tabJoueur[position].CasesJouablesIa.size(); i++) {
				System.out.println(tabJoueur[position].CasesJouablesIa.get(i));
			}
			for (int i = 0; i < tabJoueur[position].CasesJouablesIa.size(); i++) {

				tabJoueur[position].setDernierChoix(tabJoueur[position].CasesJouablesIa.get(i));

				// on copie les cases du joueur dans le tampon casesPrisesIa

				// tampon
				@SuppressWarnings("unchecked")
				TreeMap<String, Color> treeTmp = (TreeMap<String, Color>) tabJoueur[position].cases.clone();

				for (String mapKey = tabJoueur[position].cases
						.firstKey(); mapKey != null; mapKey = tabJoueur[position].cases.higherKey(mapKey)) {

					tab = tabJoueur[position].demo(mapKey);
					deplacementJoueur(tab[0], tab[1], tabJoueur[position], grille);

				}

				// nouveau score

				if (tabJoueur[position].cases.size() > score) {
					score = tabJoueur[position].cases.size();
					meilleurChoix = tabJoueur[position].getDernierChoix();

				}

				// on remet les cases de départ du joueur
				tabJoueur[position].cases = treeTmp;
			}

			// Joueur le coup bloquant

			Joueur.setDernierChoix(meilleurChoix);
			for (String mapKey = Joueur.cases.firstKey(); mapKey != null; mapKey = Joueur.cases.higherKey(mapKey)) {

				tab2 = Joueur.demo(mapKey);
				deplacementJoueur(tab2[0], tab2[1], Joueur, grille);
				grille[tab2[0]][tab2[1]].setCouleur(Joueur.getDernierChoix());

			}

			tabJoueur[position].CasesJouablesIa.clear();
			tabJoueur[position].setDernierChoix(tabJoueur[position].getDernierChoixTampon());

			majPoint(tabJoueur[tour], tour);
			majContourCases(tabJoueur[tour], grille, root2, tour, Color.BLACK);
			majNumCases(Joueur, grille, root2, tour);
			if (tour + 1 >= tabJoueur.length) {
				if (finparie2(tabJoueur, grille) == true) {
					affichageFin(stage, tour + 1);
				}
				tour = 0;
				majContourCases(tabJoueur[tour], grille, root2, tour, Color.WHITE);
			} 
			else {
				if (finparie2(tabJoueur, grille) == true) {
					affichageFin(stage, tour + 1);
				}
				majContourCases(tabJoueur[tour + 1], grille, root2, tour, Color.WHITE);
			}

			

		}
	}

	// fonction qui met à jour graphiquement les cases qu'un joueur possède avec
	// son numéro
	public void majNumCases(Joueur joueur, Case[][] grille, Group root2, int tour) {

		int[] tab;
		for (String mapKey = joueur.cases.firstKey(); mapKey != null; mapKey = joueur.cases.higherKey(mapKey)) {

			tab = joueur.demo(mapKey);

			float x = grille[tab[0]][tab[1]].getxRectangle() + 2;
			float y = grille[tab[0]][tab[1]].getYRectangle() + 48;
			int position = tour + 1;
			String pos = Integer.toString(position);
			Text t1 = new Text(x, y, pos);
			root2.getChildren().add(t1);

		}

	}

	public void majContourCases(Joueur joueur, Case[][] grille, Group root2, int tour, Color couleur) {

		int[] tab;
		for (String mapKey = joueur.cases.firstKey(); mapKey != null; mapKey = joueur.cases.higherKey(mapKey)) {

			tab = joueur.demo(mapKey);

			grille[tab[0]][tab[1]].getRectangle().setStroke(couleur);

		}

	}

	// fonction qui met à jour les point d'un joueur
	public void majPoint(Joueur joueur, int tour) {
		if (tour == 0) {// singulier
			if (joueur.cases.size() > 1) {
				joueur.tj.setText("Joueur1 :  " + joueur.cases.size() + "cases");
			} // pluriel
			else {
				joueur.tj.setText("Joueur1 :  " + joueur.cases.size() + "case");
			}

		}

		if (tour == 1) {
			if (joueur.cases.size() > 1) {
				joueur.tj.setText("Joueur2 :  " + joueur.cases.size() + "cases");
			} else {
				joueur.tj.setText("Joueur2 :  " + joueur.cases.size() + "case");
			}

		}

		if (tour == 2) {
			if (joueur.cases.size() > 1) {
				joueur.tj.setText("Joueur3 :  " + joueur.cases.size() + "cases");
			} else {
				joueur.tj.setText("Joueur3 :  " + joueur.cases.size() + "case");
			}

		}

		if (tour == 3) {
			if (joueur.cases.size() > 1) {
				joueur.tj.setText("Joueur4 :  " + joueur.cases.size() + "cases");
			} else {
				joueur.tj.setText("Joueur4 :  " + joueur.cases.size() + "case");
			}

		}

	}

}
