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
import java.awt.Point;

/**
 *
 * @author sacha
 */
public class Lumiere extends Boids {

    public Lumiere(int x, int y, int Vx, int Vy, int r, int rSecurite, int vitesseMax, int tailleLongueur, int tailleHauteur) {
        super(x, y, Vx, Vy, r, rSecurite, vitesseMax, tailleLongueur, tailleHauteur);
        this.setEtat(2);
        //l'etat d'une lumiere sera par defaut 2
    }
    
    @Override
    public void afficheBoid(GUISimulator gui) {
        GraphicalElement lumiere = new Oval((int)this.getlocalisation().getX(), 
                (int)this.getlocalisation().getY(),
                Color.WHITE, Color.YELLOW, 
                15, 15);
        gui.addGraphicalElement(lumiere);
    }
    
    
    //on override les 3 regles de sorte a ce qu'une lumiere soit fixe dans l'espace
    //elle ne bouge pas dans l'ocean. Son but est uniquement de troubler les poissons
    @Override
    public Point regle1() {
        return new Point(0,0);
        
    }
    
     @Override
    public Point regle2() {
        return new Point(0,0);
        
    }
    
     @Override
    public Point regle3() {
        return new Point(0,0);
        
    }
    
}
