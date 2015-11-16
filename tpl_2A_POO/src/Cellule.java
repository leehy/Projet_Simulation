/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author sacha
 */
public class Cellule {

    private Point localisation;
    private int etat;

    public Cellule() {
        this.localisation = new Point(0, 0);
        this.etat = 0;
    }

    public Cellule(Point localisation) {
        this.localisation = localisation;
        this.etat = 0;
    }

    public Cellule(Point localisation, int etat) {
        this.localisation = localisation;
        this.etat = etat;
    }

    /*méthode permettant de mettre l'état de la cellule de manière aléatoire entre 0 et e (ce ne sera que entre 0 et 1 vu la méthode setEtat mais peut etre que ca peut servir pour la question suivante)*/
    protected void setEtatAléatoire(int e) {
        Random r = new Random();
        this.setEtat(r.nextInt(e));
    }
    
    
    
    /*setEtatAléatoireProbabilité(float p) permet de choisir par le biais de p la probabilité que la cellule soit dans l'état vivant*/

    protected void setEtatAléatoireProbabilité(float p) {
        Random r = new Random();
        if (r.nextFloat() <= p) {
            this.etat = 1;
        } else {
            this.etat = 0;
        }
    }

    protected void setEtat(int e) {
    
        this.etat = e;
    }

    protected int getEtat() {
        return this.etat;
    }

    protected Point getlocalisation() {
        return this.localisation;
    }

    protected void setLocalisation(double x, double y) {
        this.localisation.setLocation(x,y);
    }
}
