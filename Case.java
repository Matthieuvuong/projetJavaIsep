package projet;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Case {

	int x;
	int y;

	public static final Color[] tableauCouleur = { Color.RED, Color.BLUE, Color.MAGENTA, Color.GREEN, Color.YELLOW,
			Color.ORANGE };

	private Color couleur;
	private Color interdite = Color.GREY;
	private Rectangle rectangle;
	private Text t;
	private float xRectangle;
	private float YRectangle;

	// constructeur
	public Case(Color couleur) {
		this.couleur = couleur;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public Case() {
		Random rand = new Random();

		int nombreAleatoire = rand.nextInt(6);
		this.couleur = tableauCouleur[nombreAleatoire];
	}

	// acceseurs

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
		this.rectangle.setFill(couleur);
	}

	public static Color[] getTableaucouleur() {
		return tableauCouleur;
	}

	public Color getInterdite() {
		return interdite;
	}

	public void setInterdite(Color interdite) {
		this.interdite = interdite;
	}

	public Text getT() {
		return t;
	}

	public void setT(Text t) {
		this.t = t;
	}

	public float getxRectangle() {
		return xRectangle;
	}

	public void setxRectangle(float xRectangle) {
		this.xRectangle = xRectangle;
	}

	public float getYRectangle() {
		return YRectangle;
	}

	public void setYRectangle(float yRectangle) {
		YRectangle = yRectangle;
	}

}