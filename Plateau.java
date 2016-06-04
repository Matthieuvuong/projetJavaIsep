package projet;

import javafx.scene.shape.*;
import javafx.scene.text.Text;

import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Plateau {

	public Case[][] grille;
	public Stage stage;

	// constructeurs
	public Plateau(int taille, Stage stage) {
		this.stage = stage;
		int i, j;

		this.grille = new Case[taille][taille];

		for (i = 0; i < taille; i++) {
			for (j = 0; j < taille; j++) {
				this.grille[i][j] = new Case();
				this.grille[i][j].setX(j);
				this.grille[i][j].setY(i);
			}
		}
	}

	public Rectangle afficherRectangle(int xPos, int yPos, int width, int height, Color fond, Color contour) {
		Rectangle r = new Rectangle();
		r.setX(xPos);
		r.setY(yPos);
		r.setWidth(width);
		r.setHeight(height);
		r.setFill(fond);
		r.setStroke(contour);
		return r;

	}

	public Case[][] getGrille() {
		return grille;
	}

	public void setGrille(Case[][] grille) {
		this.grille = grille;
	}

}
