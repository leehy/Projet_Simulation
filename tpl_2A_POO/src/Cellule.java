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
    private Point Localisation;
    private int etat;

    public Cellule(Point Localisation) {
        this.Localisation = Localisation;
        this.etat = 0 ; 
    }

    public Cellule(Point Localisation, int etat) {
        this.Localisation = Localisation;
        this.etat = etat;
    }
    
   /*méthode permettant de mettre l'état de la cellule de manière aléatoire entre 0 et e (ce ne sera que entre 0 et 1 vu la méthode setEtat mais peut etre que ca peut servir pour la question suivante)*/
    public void setEtatAléatoire(int e){
        Random r = new Random();
        this.setEtat(r.nextInt(e));
    }
    
    
    public void setEtat(int e) {
        if(e<=0) {
            this.etat = e;
        }
        else {
            System.out.println("erreur : l'état ne peut être que 1 (vivante) ou 0 (morte)");
        }
    }
    
    
    public int getEtat() {
        return this.etat;
    }
   
    
    public Point getLocalisation() {
        return this.Localisation;
    }
    
}
