package src;

import gui.*;
import java.awt.Color;
import java.util.Random;

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

	public Color getCouleur (int n)
	{
		switch (n) {
			case 0 : return Color.WHITE;
			case 1 : return Color.RED;
			case 2 : return Color.YELLOW;
			case 3 : return Color.GREEN;
			case 4 : return Color.ORANGE;
			case 5 : return Color.PURPLE;
			case 6 : return Color.BLUE;
		}
	}

    private void swapP (int a, int b, int c, int d) //swap les éléments (a,b) et (c,d) de plateau
    {
        Cellule e = plateau [a][b];
        plateau [a][b] = plateau [c][d];
        plateau [c][d] = e;
    }

	private void swapRand (int a, int b) //échange la case (i,j) avec une case choisie aléatoirement
	{
		Random r = new Random();
        Random s = new Random();
        swapP (r.nextInt(this.getNombreCelluleHauteur()),r.nextInt(this.getNombreCelluleLongueur()),a,b);
	}

	@Override
    public void restart() { 
        // On efface l'ecran
        System.out.println("Ça recommence !");
        this.gui.reset();

		private int acc;
		acc = getNombreCelluleHauteur () * getNombreCelluleLongueur () * densité / 100; //acc représente le nbre de cellules à disposer sur le plateau

        //On réinitialise acc cellules de manière aléatoire (valeur entre 0 et nombreCouleurs - 1)
        for (int i = 0; i < this.getNombreCelluleHauteur() && acc > 0; i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur() && acc > 0; j++, acc--) {
                this.plateau[i][j].setEtatAléatoire(nombreCouleurs-1);
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
                if (plateau[i][j].getEtat() == 1) {
                    //pour l'instant c'est juste sur le cas particulier d'une fenêtre gui de taille SizeSimX*SizeSimY et de cellule de taille NombreCelluleHauteur()*NombreCelluleLongueur() (provisoire) 
                    gui.addGraphicalElement(new Rectangle(j * getSizeSimX() / this.getNombreCelluleLongueur() + 10 , i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.BLUE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                }
                if (plateau[i][j].getEtat() == 0) {
                    gui.addGraphicalElement(new Rectangle(j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10, i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.WHITE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                }
            }
        }

    }

    private int getNombreVoisinsIdentiques(int i, int j) { //Donne le nombre de voisins ayant le même état que la cellule plateau [i][j]
        int compteur = 0;
        int comp = plateau [i][j].getEtat();
		for (int a = -1; a<2; a++){
                   
			for (int b = -1; b<2; b++){
                            //modulo un peu sale car java renvoie juste le reste de la division euclidienne (donc -1 pour -1) et du coup on sort du tableau avec juste %
			if (comp == (plateau[((i + a) % getNombreCelluleHauteur() + getNombreCelluleHauteur())%getNombreCelluleHauteur()][((j + b) % getNombreCelluleLongueur() + getNombreCelluleLongueur())% getNombreCelluleLongueur() ]).getEtat()){
                compteur ++;
                }
			}
		}
		return compteur - 1;
	}

    public void afficheCellule (int i, int j) {
        gui.addGraphicalElement(new Rectangle(j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10, i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, getCouleur (plateau [i][j].getEtat ()), this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
    }

	private void swapRandVide (int a, int b) //échange la case (i,j) avec une case vide choisie aléatoirement
	{
		Random r = new Random();
        Random s = new Random();
        int c = r.nextInt(this.getNombreCelluleHauteur());
        int d = r.nextInt(this.getNombreCelluleLongueur());
        if (plateau [c][d].getEtat > 0) {
            swapRandVide (a,b); //parce qu'il y a au pire une chance sur deux de tomber sur une habitation vide, on peut se permettre cet appel récursif
                                //qui peut s'interpréter comme "prends des habitations au hasard jusqu'à tomber sur une vide"
                                //dans le cas d'une densité à 50%, parce que 1/2 + 1/4 + 1/8 + ... tend vers 1, swapRandVide ne s'appelle qu'une fois en moyenne
                                //cela ne change donc rien à la complexité de la fonction demenage
        }
        else {
        swapP (c,d,a,b);
        afficheCellule (a,b);
        afficheCellule (c,d);
        }
	}

    private void demenage (l) { //On fait déménager toutes les habitations retenues en mémoire
        if est_vide (l) {
            return;
        }
        else {
            swapRandVide (l.int (), l.suiv.int ());
            demenage (l.suiv.suiv);
        }
    }

    private void libere (l) { //Et on libère les habitations précédemment habitées
        if est_vide (l) {
            return;
        }
        else {
            plateau [l.int ()][l.suiv.int ()].setEtat (0);
            afficheCellule (l.int (), l.suiv.int ());
        }
        libere (l.suiv.suiv () );
    }


    @Override
    public void next() {
        // On efface l'écran
        gui.reset();
        Liste l = new Liste ();
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                if (getEtat () > 0 && getNombreVoisinsIdentiques (i,j) > K) { //Si la case est occupée et qu'elle doit déménager, l'ajouter à une liste l sinon, ne rien faire
                    ajout_tete (j,l);
                    ajout_tete (i,l);
                }
            }
        }
        
        demenage (l);   //On fait déménager toutes les habitations retenues en mémoire
    
        libere (l);     //Et on libère les habitations précédemment habitées

    }

}
