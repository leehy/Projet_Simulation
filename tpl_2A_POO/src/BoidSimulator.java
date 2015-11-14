/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.util.Stack; 

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
                hachage[i][j] = new Stack ();
            }
        }
    }
    
    public int mod ( int x , int y ){
        return (x+y)%y ;
    }
    
    private int[] access (int [] v){ //v doit être un couple (x,y)
        v[0] = mod (v[0],hauteur/rayon);
        v[1] = mod (v[1],longueur/rayon);
        return v;
    }
    
    
    
    
}
