/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.util.Stack; 
import java.awt.Point;

/**
 *
 * @author amaury
 */
public class BoidSimulator {
    
    private Stack hachage [][];
    private int hauteur;
    private int longueur;
    private int NbBoids;
    private int rayon;
    
    BoidSimulator (int n, int h, int l, int r){
        NbBoids = n;
        hauteur = h;
        longueur = l;
        rayon = r;
        
        for (int i = 0; i < h/r; i++) {
            for (int j = 0; j < l/r; j++) {
                hachage[i][j] = new Stack (); //Initialise le tableau de jeu avec des piles de Boids
            }
        }
    }
    
    public int mod ( int x , int y ){
        return (x+y)%y ;
    }
    
    private void access (Point v){ //v doit être un couple (x,y)
        v.x = mod (v.x,hauteur/rayon);
        v.y = mod (v.y,longueur/rayon);
    }
    
    private Point caseCorrespondante (Boids b){ //Calcule la position d'un boids dans le plateau
        Point p = new Point ();
        p.setLocation( b.getlocalisation().getX() / b.getRayon (), b.getlocalisation().getY() / b.getRayon ());
        return p;
    }
    
    private Stack voisinsPotentiels (Boids b){ //retourne la pile des boids dans les 9 cases entourant un boid
        Stack pile = new Stack ();
        Point caseDuBoid = caseCorrespondante (b);
        Point acc = new Point ();
        
        for (int i = -1; i<2; i++){
            for (int j = -1; j<2; j++){
                acc.setLocation(caseDuBoid.getX() + i,caseDuBoid.getY() + j);
                access (acc);
                pile.addAll(hachage [acc.x][acc.y]);
            }
        }
        
        return pile; 
    }
    
    
    
}
