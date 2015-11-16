package src;

import gui.*;
import java.awt.Color;
import java.util.Random;
import java.util.Stack; 

/**
 *
 * @author Amaury
 */

public class Schelling extends JeuDeLaVie{
	
    private int nombreCouleurs;
    private int densité;
    private int K;
	

    public Schelling (int c, int d, int k){
        super ();
	nombreCouleurs = c; //c est le nombre de couleurs : pour le moment, 0<c<6
	densité = d; //d représente la densité de population en pourcentage : 0<d<50
        K = k; //K représente le seuil défini dans l'énoncé
    }
    
    @Override
    public Color getCouleur (int n){
		switch (n) {
			case -1: return Color.WHITE; //cas d'une habitation libre
			case 0 : return Color.RED;
			case 1 : return Color.BLUE;
			case 2 : return Color.GREEN;
			case 3 : return Color.ORANGE;
			case 4 : return Color.MAGENTA;
			default : return Color.YELLOW;
		}
	}

    private void swapP (int a, int b, int c, int d){ //swap les éléments (a,b) et (c,d) de plateau
        Cellule e = (this.getPlateau()) [a][b];
        (getPlateau()) [a][b] = (getPlateau()) [c][d];
        (getPlateau()) [c][d] = e;
    }

    private void swapRand (int a, int b){ //échange la case (i,j) avec une case choisie aléatoirement
	Random r = new Random();
        Random s = new Random();
        swapP (r.nextInt(this.getNombreCelluleHauteur()),r.nextInt(this.getNombreCelluleLongueur()),a,b);
    }

    @Override
    public void restart() { 
        // On efface l'ecran
        System.out.println("Ça recommence !");
        (getGui()).reset();

	int acc;
	acc = getNombreCelluleHauteur () * getNombreCelluleLongueur () * densité / 100; //acc représente le nbre de cellules à disposer sur le plateau

        //On réinitialise acc cellules de manière aléatoire (valeur entre 0 et nombreCouleurs - 1)
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                if(acc>0){
                    (getPlateau ())[i][j].setEtatAléatoire(nombreCouleurs);
                    acc --;
                }
                else{
                    (getPlateau ())[i][j].setEtat (-1);
                }
            }
        }//on a initialisé les premières cases du tableau reste plus qu'à les switcher

        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                swapRand (i,j);
            }
        }

        //on affiche toutes les cases
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                afficheCellule (i,j);
            }
        }

    }

    private int getNombreVoisinsDifferents(int i, int j) { //Donne le nombre de voisins ayant le même état que la cellule plateau [i][j]
        int compteur = 0;
        int etatA = (getPlateau()) [i][j].getEtat();
        for (int a = -1; a<2; a++){
            for (int b = -1; b<2; b++){
                int etatB = ((getPlateau())[((i + a) % getNombreCelluleHauteur() + getNombreCelluleHauteur())%getNombreCelluleHauteur()][((j + b) % getNombreCelluleLongueur() + getNombreCelluleLongueur())% getNombreCelluleLongueur() ]).getEtat();
                //modulo un peu sale car java renvoie juste le reste de la division euclidienne (donc -1 pour -1) et du coup on sort du tableau avec juste %
                if (etatB >= 0 && etatB != etatA){
                    compteur ++;
                }
            }
        }
        return compteur;
    }

            
    private void dedoubleVide (int a, int b) //dédouble la case (a,b) avec une case vide choisie aléatoirement
    {
        Random r = new Random();
        Random s = new Random();
        int c = r.nextInt(this.getNombreCelluleHauteur());
        int d = r.nextInt(this.getNombreCelluleLongueur());
        if (((getPlateau()) [c][d]).getEtat() >= 0) {
            dedoubleVide (a,b); //parce qu'il y a au pire une chance sur deux de tomber sur une habitation vide, on peut se permettre cet appel récursif
                                //qui peut s'interpréter comme "prends des habitations au hasard jusqu'à tomber sur une vide"
                                //dans le cas d'une densité à 50%, parce que 1/2 + 1/4 + 1/8 + ... tend vers 1, swapRandVide ne s'appelle qu'une fois en moyenne
                                //cela ne change donc rien à la complexité de la fonction demenage
        }
        else {
            ((getPlateau()) [c][d]).setEtat(((getPlateau()) [a][b]).getEtat());
            afficheCellule (c,d);
        }
    }

    private void demenage (Stack l) { //On dédouble toutes les habitations retenues en mémoire
        if (l.empty ()) {
            return;
        }
        dedoubleVide (Integer.parseInt(l.pop ().toString()), Integer.parseInt(l.pop ().toString()));
        demenage (l);
        
    }
    
    private void libere (Stack l) { //Et on libère les habitations précédemment habitées
        if (l.empty ()) {
            return;
        }
        else {
            int a = Integer.parseInt(l.pop ().toString());
            int b = Integer.parseInt(l.pop ().toString());
            (getPlateau()) [a][b].setEtat (-1);
            afficheCellule (a,b);
        }
        libere (l);
    }


    @Override
    public void next() {
        Stack l = new Stack ();
        Stack l2 = new Stack ();
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                if ((getPlateau())[i][j].getEtat () >= 0 && getNombreVoisinsDifferents (i,j) > K) { //Si la case est occupée et qu'elle doit déménager, l'ajouter à la liste, sinon, ne rien faire
                    l.push (j);
                    l.push (i);
                    l2.push(j);
                    l2.push(i);
                }
            }
        }
        demenage (l);   //On fait déménager toutes les habitations retenues en mémoire
    
        libere (l2);     //Et on libère les habitations précédemment habitées

    }

}
