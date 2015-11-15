/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import gui.*;
import java.awt.Color;
import java.util.*;

/**
 *
 * @author sacha
 */
public class Poisson extends Boids {
int tailleTete;
int tailleCorpsLongueur;
int tailleCorpsHauteur;
Color couleurTete;
Color couleurCorps;

    public Poisson(int x, int y, int Vx, int Vy, int rayonAction, int rayonSecurite, int tailleHauteur, int tailleLongueur) {
        super( x, y, Vx, Vy, rayonAction,rayonSecurite, tailleHauteur,  tailleLongueur);
        this.setEtat(1); //un poisson sera dans l'état 1
        this.tailleCorpsLongueur = 50;
        this.tailleTete = 10;
        this.tailleCorpsHauteur = 20;
        this.couleurTete = Color.pink;
        this.couleurCorps = Color.cyan;
    }
    
    @Override
    public void afficheBoid(GUISimulator gui) {
        GraphicalElement corps = new Oval((int)this.getlocalisation().getX(), 
                (int)this.getlocalisation().getY(),
                this.couleurCorps, Color.GRAY, 
                this.tailleCorpsLongueur, this.tailleCorpsHauteur);
        GraphicalElement tete = new Oval((int)this.getlocalisation().getX() + tailleCorpsLongueur/2, 
                (int)this.getlocalisation().getY(),
                this.couleurTete, Color.GRAY, 
                this.tailleTete);
        gui.addGraphicalElement(corps);
        gui.addGraphicalElement(tete);
    }
    
}
