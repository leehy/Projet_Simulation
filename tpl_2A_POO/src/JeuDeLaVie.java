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
    
    
    //constructeur par défaut crée un tableau de 25*25 cellules initialisées aléatoirement
    public JeuDeLaVie() {
        this.plateau = new Cellule[25][25];
        this.sauvegardeEtat = new int[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                plateau[i][j] = new Cellule();
                this.probabilité = (float)0.2;
            }
        }
        this.gui = new GUISimulator(500, 500, Color.BLACK);

    }

    
    //permet de choisir la probabilité qu'une cellule soit vivante
    public void setProbabilité(float p) {
        this.probabilité = p;
       
    }
    
    public float getProbabilité() {
    return this.probabilité;
}
    
    public GUISimulator getguiSimulator() {
        return this.gui;
    }

    /*renvoie le nombre de voisins vivants pour la cellule du tableau à la ligne i, colonne j du plateau*/
    public int getNombreVoisinsVivants(int i, int j) {
        int compteur = 0;
        /*si on est pas aux extrémités du tableau*/
        if (!(i == 0 || j == 0 || i == 24 || j == 24)) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        /*si on est sur la première ligne du plateau. Il suffit de remplacer tous les i-1 par la dernière ligne du plateau*/
        if (i == 0 && j != 0 && j != 24) {
            compteur = this.plateau[24][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[24][j].getEtat() + this.plateau[24][j + 1].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        /*si on est sur la dernière ligne du plateau.*/
        if (i == 24 && j != 0 && j != 24) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[0][j - 1].getEtat() + this.plateau[0][j + 1].getEtat() + this.plateau[0][j].getEtat();
        }
        /*si on est sur la première colonne du plateau*/
        if (j == 0 && i != 0 && i != 24) {
            compteur = this.plateau[i - 1][24].getEtat() + this.plateau[i][24].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[i + 1][24].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        /*si onest sur la dernière colonne du plateau.*/
        if (j == 24 && i != 0 && i != 24) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][0].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][0].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][0].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        /*si on est dans le coin en haut a gauche*/
        if (i == 0 && j == 0) {
            compteur = this.plateau[24][24].getEtat() + this.plateau[i][24].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[24][j].getEtat() + this.plateau[24][j + 1].getEtat() + this.plateau[i + 1][24].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        //si on est en dans le coin en bas a droite
        if (i == 24 && j == 24) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][0].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][0].getEtat() + this.plateau[0][j - 1].getEtat() + this.plateau[0][0].getEtat() + this.plateau[0][j].getEtat();
        }
        //si on est dans le coin en bas a gauche (j-1 devient 24 et i+1 devient 0)
        if (i == 24 && j == 0) {
            compteur = this.plateau[i - 1][24].getEtat() + this.plateau[i][24].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[0][24].getEtat() + this.plateau[0][j + 1].getEtat() + this.plateau[0][j].getEtat();
        }
        //si on est dans le coin en haut a droite (i-1 devient 24 et j+1 devient 0)
        if (i == 0 && j == 24) {
            compteur = this.plateau[24][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][0].getEtat() + this.plateau[24][j].getEtat() + this.plateau[24][0].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][0].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        return compteur;
    }

    @Override
    public void restart() {
        // On efface l'ecran
        System.out.println("ca recommence");
        this.gui.reset();
        //On réinitialise le plateau de manière aléatoire (valeur entre 0 et 1)
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                this.plateau[i][j].setEtatAléatoireProbabilité(this.getProbabilité());
            }
        }

        //on affiche toute les cases
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (plateau[i][j].getEtat() == 1) {
                    /*pour l'instant c'est juste sur le cas particulier d'une fenêtre gui de taille 500*500 et de cellule de taille 25*25 (provisoire) */
                    gui.addGraphicalElement(new Rectangle(i * 500 / 25 + 10, j * 500 / 25 + 10, Color.BLACK, Color.BLUE, 500 / 25, 500 / 25));
                }
                if (plateau[i][j].getEtat() == 0) {
                    gui.addGraphicalElement(new Rectangle(i * 500 / 25 + 10, j * 500 / 25 + 10, Color.BLACK, Color.WHITE, 500 / 25, 500 / 25));
                }
            }
            System.out.println(plateau[i][2].getEtat());
        }

    }

    @Override
    public void next() {
        // On efface l'écran
        gui.reset();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                //si la cellule i,j du plateau est morte
                if (this.plateau[i][j].getEtat() == 0) {
                    if (this.getNombreVoisinsVivants(i, j) > 2) {
                        /*si le nombre de voisins de la case morte est supérieur ou égal à 3 alors on crée une cellule vivante( */
                        this.sauvegardeEtat[i][j] = 1;
                        gui.addGraphicalElement(new Rectangle(i * 500 / 25 + 10, j * 500 / 25 + 10, Color.BLACK, Color.BLUE, 500 / 25, 500 / 25));
                    } //sinon on redessine une cellule morte
                    else {
                        this.sauvegardeEtat[i][j] = 0;
                        gui.addGraphicalElement(new Rectangle(i * 500 / 25 + 10, j * 500 / 25 + 10, Color.BLACK, Color.WHITE, 500 / 25, 500 / 25));
                    }
                }
                //si la cellule i,j du plateau est vivante
                if (this.plateau[i][j].getEtat() == 1) {
                    /*si le nombre de voisins de la case vivante est supérieur ou égal à 2 alors la cellule reste vivante( */
                    if (this.getNombreVoisinsVivants(i, j) > 1) {
                        this.sauvegardeEtat[i][j] = 1;
                        gui.addGraphicalElement(new Rectangle(i * 500 / 25 + 10, j * 500 / 25 + 10, Color.BLACK, Color.BLUE, 500 / 25, 500 / 25));
                    } //sinon elle meurt
                    else {
                       this.sauvegardeEtat[i][j] = 0;;
                        gui.addGraphicalElement(new Rectangle(i * 500 / 25 + 10, j * 500 / 25 + 10, Color.BLACK, Color.WHITE, 500 / 25, 500 / 25));
                    }
                }
            }
        }

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                plateau[i][j].setEtat(this.sauvegardeEtat[i][j]);
            }
        }
    }
}
