/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.*;
import java.awt.Color;
import java.awt.Point;
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

    public Poisson(int x, int y, int Vx, int Vy, int rayonAction, int rayonSecurite, int vitesseMax, int tailleHauteur, int tailleLongueur) {
        super(x, y, Vx, Vy, rayonAction, rayonSecurite, vitesseMax , tailleHauteur, tailleLongueur);
        this.setEtat(1); //un poisson sera dans l'état 1
        this.tailleCorpsLongueur = 30;
        this.tailleTete = 10;
        this.tailleCorpsHauteur = 10;
        this.couleurTete = Color.pink;
        this.couleurCorps = Color.cyan;
    }

    @Override
    public void afficheBoid(GUISimulator gui) {
        GraphicalElement corps = new Oval((int) this.getlocalisation().getX(),
                (int) this.getlocalisation().getY(),
                this.couleurCorps, Color.GRAY,
                this.tailleCorpsLongueur, this.tailleCorpsHauteur);
        GraphicalElement tete = new Oval((int) this.getlocalisation().getX() + tailleCorpsLongueur / 2,
                (int) this.getlocalisation().getY(),
                this.couleurTete, Color.GRAY,
                this.tailleTete);
        gui.addGraphicalElement(corps);
        gui.addGraphicalElement(tete);
    }

    @Override
//on override le centre de masse de sorte a ce que si le poisson a dans ses voisins une lumiere
//il sera vraiment très attiré par la lumière, beaucoup plus que par ses amis poissons 
//(il est attiré par les poisssons de l'ordre de 1% et par la lumière de l'ordre de 100%)
    public Point centreDeMasse() {
        int index = 0;
        Point centreMasse = new Point();
        int taille = this.voisins.size();
        while (index < taille) {
            //si c'est un poisson le poids reste le meme
            if (this.voisins.get(index).getEtat() == 1) {
                centreMasse.setLocation(centreMasse.getX() + this.voisins.get(index).getlocalisation().getX() / taille,
                        centreMasse.getY() + this.voisins.get(index).getlocalisation().getY() / taille);
            }
            //si c'est une lumiere le poids est bien plus important (2*nombreDeVoisins fois plus important)
            /*else if(this.voisins.get(index).getEtat() == 2)
             {
             centreMasse.setLocation(centreMasse.getX() + this.voisins.get(index).getlocalisation().getX()*10 ,
             centreMasse.getY() + this.voisins.get(index).getlocalisation().getY()*10 );
             }*/
            if (this.voisins.get(index).getEtat() == 2) {

                centreMasse.setLocation(this.voisins.get(index).getlocalisation());
                index++;
            }
            index++;

        }
        return centreMasse;
    }
}
