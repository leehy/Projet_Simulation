/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.*;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author amaury
 */
public class Immigration extends JeuDeLaVie {
    
    private int nombreCouleurs;
    
    public Immigration (int c){
        super ();
	nombreCouleurs = c;
    }
    
    @Override
    public Color getCouleur (int n){
		switch (n) {
			case 0 : return Color.WHITE; //cas d'une habitation libre
			case 1 : return Color.YELLOW;
			case 2 : return Color.ORANGE;
			case 3 : return Color.RED;
			case 4 : return Color.MAGENTA;
			case 5 : return Color.CYAN;
			default : return Color.BLUE;
		}
	}
    
    
    private int getNombreVoisinsSuperieur(int i, int j) { //Donne le nombre de voisins ayant le même état que la cellule plateau [i][j] + 1
        int compteur = 0;
        int etatA = (1 + (getPlateau()) [i][j].getEtat())%nombreCouleurs;
        for (int a = -1; a<2; a++){
            for (int b = -1; b<2; b++){
                int etatB = ((getPlateau())[((i + a) + getNombreCelluleHauteur())%getNombreCelluleHauteur()][((j + b)+ getNombreCelluleLongueur())% getNombreCelluleLongueur() ]).getEtat();
                //modulo un peu sale car java renvoie juste le reste de la division euclidienne (donc -1 pour -1) et du coup on sort du tableau avec juste %
                if (etatA == etatB){
                    compteur ++;
                }
            }
        }
        return compteur;
    }
    
    @Override
    public void restart() {
        // On efface l'ecran

        getGui().reset();
        //On réinitialise le plateau de manière aléatoire (valeur entre 0 et 1)
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                getPlateau ()[i][j].setEtatAléatoire(nombreCouleurs);
            }
        }

        //on affiche toute les cases
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                afficheCellule (i,j);
            }
        }
    }

    @Override
    public void next() {
       
            // On efface l'écran
            getGui().reset();
            
            for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
                for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                    if (getNombreVoisinsSuperieur (i,j) >= 3) {
                        getSauvegardeEtat () [i][j] = (1 + (getPlateau()) [i][j].getEtat())%nombreCouleurs;
                    }
                    else{
                        getSauvegardeEtat () [i][j] = (getPlateau()) [i][j].getEtat();
                    }
                }
            }

            for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
                for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                    getPlateau () [i][j].setEtat(getSauvegardeEtat() [i][j]);
                    afficheCellule (i,j);
                }
            }
    }

}
