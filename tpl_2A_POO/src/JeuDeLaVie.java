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
 * @author sacha
 */
public class JeuDeLaVie implements Simulable {

    private Cellule plateau[][];
    private GUISimulator gui;
    private int sauvegardeEtat[][];
    private float probabilité;
    private int sizeSimX;
    private int sizeSimY;
    private int nombreCelluleHauteur;
    private int nombreCelluleLongueur;

    /**
    *constructeur par défaut crée un tableau de 25*25 cellules initialisées aléatoirement
    * @return
    */
    public JeuDeLaVie() {
        this.nombreCelluleHauteur = 25;
        this.nombreCelluleLongueur = 25;
        /* il y a autant de cellules en hauteur que de ligne dans le plateau et autant de cellule en longueur que de nombre de case en largeur dans le plateau*/
        this.plateau = new Cellule[nombreCelluleHauteur][nombreCelluleLongueur];
        this.sauvegardeEtat = new int[nombreCelluleHauteur][nombreCelluleLongueur];
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                plateau[i][j] = new Cellule();
                this.probabilité = (float) 0.2;
            }
        }
        this.sizeSimX = 500;
        this.sizeSimY = 500;
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);

    }

    /**
     * setSizeSim permet de régler la taille de la fenêtre gui
     * Elle prend en argument la taille en longueur puis la taille en hauteur
     * Elle peut envoyer une exception à cause si le rapport entre le nombre de cellule et la taille n'est pas bon
     * @return
      */
    public void setSizeSim(int sizeSimX, int sizeSimY) throws RapportCelluleTailleException {
        if (sizeSimX * sizeSimY < 9 * (this.getNombreCelluleHauteur() * this.getNombreCelluleLongueur())) {
            throw new RapportCelluleTailleException((float) (sizeSimX * sizeSimY / (this.getNombreCelluleHauteur() * this.getNombreCelluleLongueur())));
        } else {
            this.sizeSimX = sizeSimX;
            this.sizeSimY = sizeSimY;
        }
    }

    /**
    *getSizeSimX() renvoie la taille de la fenêtre gui en longueur
    * @return
    */
    public int getSizeSimX() {
        return this.sizeSimX;
    }

    /**
     * renvoie la taille de la fenêtre gui en hauteur 
     * @return 
     */
    public int getSizeSimY() {
        return this.sizeSimY;
    }
    
    /**
     * getPlateau() renvoie le plateau (un tableau de Cellules 2 dimensions)
     * @return 
     */
    protected Cellule [][] getPlateau() {
        return plateau;
    }
    
    /**
     * getSauvergardeEtat() renvoie un tableau 2 dimensions d'entiers de la sauvegarde de l'état des cellules
     * @return 
     */
    protected int [][] getSauvegardeEtat () {
        return sauvegardeEtat;
    }
    
    /**
     * getGui() renvoie le GUISimulator
     * @return 
     */
    protected GUISimulator getGui() {
        return gui;
    }
    
    /**setNombreCellule permet de régler le nombre de cellules en hauteur et en longueur
     * elle renvoie une exception si le rapport entre le nombre de cellules et la taille de la fenêtre n'est pas bon
     * @param NombreHauteur
     * @param NombreLongueur
     * @throws RapportCelluleTailleException 
     */
    public void setNombreCellule(int NombreHauteur, int NombreLongueur) throws RapportCelluleTailleException {
        this.nombreCelluleHauteur = NombreHauteur;
        this.nombreCelluleLongueur = NombreLongueur;
        if ((this.getSizeSimX() * this.getSizeSimY()) < 9 * (NombreHauteur * NombreLongueur)) {
            throw new RapportCelluleTailleException((float) (this.getSizeSimX() * this.getSizeSimY()) / (NombreHauteur * NombreLongueur));
        } else {
            this.plateau = new Cellule[this.getNombreCelluleHauteur()][this.getNombreCelluleLongueur()];
            for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
                for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                    plateau[i][j] = new Cellule();
                }
            }
            this.sauvegardeEtat = new int[this.getNombreCelluleHauteur()][this.getNombreCelluleLongueur()];
        }
    }

    /**
     * getNombreCelluleHauteur() renvoie le nombre de cellule par colonne
     * @return 
     */
    public int getNombreCelluleHauteur() {
        return this.nombreCelluleHauteur;
    }

    /**
     * getNombreCelluleLongueur renvoie le nombre de cellules par ligne
     * @return 
     */
    public int getNombreCelluleLongueur() {
        return this.nombreCelluleLongueur;
    }

    /**
     * setProbabilité permet de choisir la probabilité qu'une cellule soit vivante
     * @param p 
     */
    public void setProbabilité(float p) {
        this.probabilité = p;

    }

    /**
     * getProbabilité() renvoie la probabilité qu'un cellule soit vivante
     * @return 
     */
    public float getProbabilité() {
        return this.probabilité;
    }


  
    /**
     * getNombreVoisinsVivants renvoie le nombre de voisins vivants pour la cellule 
     * placée en (i,j) sur le plateau
     * @param i
     * @param j
     * @return 
     */
    public int getNombreVoisinsVivants(int i, int j) {
        int compteur = 0;
        for (int a = -1; a < 2; a++) {

            for (int b = -1; b < 2; b++) {
                /*modulo un peu sale car java renvoie juste le reste de la division euclidienne (donc -1 pour -1) et du coup on sort du tableau avec juste %*/
                compteur = compteur + (plateau[((i + a) % getNombreCelluleHauteur() + getNombreCelluleHauteur()) % getNombreCelluleHauteur()][((j + b) % getNombreCelluleLongueur() + getNombreCelluleLongueur()) % getNombreCelluleLongueur()]).getEtat();
            }
        }
        return compteur - plateau[i][j].getEtat();
    }

    /**
     * getCouleur renvoie la couleur d'une cellule
     * @param n
     * @return 
     */
    public Color getCouleur (int n){
            switch (n) {
		case 0 : return Color.WHITE; //cas d'une habitation libre
            	default: return Color.BLUE;
            }
	}
    
    /**
     * afficheCellule(i,j) permet d'afficher une cellule aux coordonnées i,j
     * @param i
     * @param j 
     */
    public void afficheCellule (int i, int j) {
        (getGui()).addGraphicalElement(new Rectangle(
                j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10,
                i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, 
                Color.BLACK, 
                getCouleur ((getPlateau()) [i][j].getEtat ()),
                this.getSizeSimX() / this.getNombreCelluleLongueur(), 
                this.getSizeSimY() / this.getNombreCelluleHauteur()));
    }
    
    /**
     * restart() permet de réinitialiser le plateau avec des cellules mortes et vivantes
     * réparties de manière aléatoire selon la probabilité choisie
     * @return
     */
    @Override
    public void restart() {
        // On efface l'ecran

        this.gui.reset();
        //On réinitialise le plateau de manière aléatoire (valeur entre 0 et 1)
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                this.plateau[i][j].setEtatAléatoireProbabilité(this.getProbabilité());
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
    /**
     * next() permet d'afficher le plateau à l'état suivant
     * @return
     */
    public void next() {
       
            // On efface l'écran

            gui.reset();
            for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
                for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                    //si la cellule i,j du plateau est morte
                    if (this.plateau[i][j].getEtat() == 0) {
                        if (this.getNombreVoisinsVivants(i, j) > 2) {
                            /*si le nombre de voisins de la case morte est supérieur ou égal à 3 alors on crée une cellule vivante( */
                            this.sauvegardeEtat[i][j] = 1;
                        } //sinon on redessine une cellule morte
                        else {
                            this.sauvegardeEtat[i][j] = 0;
                        }
                    }
                    //si la cellule i,j du plateau est vivante
                    if (this.plateau[i][j].getEtat() == 1) {
                        /*si le nombre de voisins de la case vivante est supérieur ou égal à 2 alors la cellule reste vivante( */
                        if (this.getNombreVoisinsVivants(i, j) > 1  && this.getNombreVoisinsVivants(i, j) < 4) {
                            this.sauvegardeEtat[i][j] = 1;
                        } //sinon elle meurt
                        else {
                            this.sauvegardeEtat[i][j] = 0;;
                        }
                    }
                }
            }

            for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
                for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                    plateau[i][j].setEtat(this.sauvegardeEtat[i][j]);
                    afficheCellule (i,j);
                }
            }
    }
}
