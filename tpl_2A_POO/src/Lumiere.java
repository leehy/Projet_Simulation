/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;
import gui.GraphicalElement;
import gui.Oval;
import java.awt.Color;

/**
 *
 * @author sacha
 */
public class Lumiere extends Boids {

    public Lumiere(int x, int y, int Vx, int Vy, int r, int rSecurite, int tailleLongueur, int tailleHauteur) {
        super(x, y, Vx, Vy, r, rSecurite, tailleLongueur, tailleHauteur);
        this.setEtat(2);
        //l'etat d'une lumiere sera par defaut 2
    }
    
    @Override
    public void afficheBoid(GUISimulator gui) {
        GraphicalElement corps = new Oval((int)this.getlocalisation().getX(), 
                (int)this.getlocalisation().getY(),
                Color.WHITE, Color.RED, 
                25, 25);
    }
    
}
