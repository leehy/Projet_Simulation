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

    private Cellule[][] Plateau;
    private GUISimulator Gui;

    public JeuDeLaVie() {
        this.Plateau = new Cellule[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                this.Plateau[i][j].setEtatAléatoire(1);
                /* permet de mettre de manière aléatoire des 0 et des 1 sur les cases du plateau */
            }
        }
        this.Gui = new GUISimulator(500, 500, Color.WHITE);

    }

public GUISimulator getGuiSimulator() {
        return this.Gui;
    }
    
    /*renvoie le nombre de voisins vivants pour la cellule du tableau à la ligne i, colonne j du plateau*/
    public int getNombreVoisinsVivants(int i, int j) {
        int compteur = 0;
        /*si on est pas aux extrémités du tableau*/
        if (!(i == 0 || j == 0 || i == 24 || j == 24)) {
            compteur = this.Plateau[i - 1][j - 1].getEtat() + this.Plateau[i][j - 1].getEtat() + this.Plateau[i][j + 1].getEtat() + this.Plateau[i - 1][j].getEtat() + this.Plateau[i - 1][j + 1].getEtat() + this.Plateau[i + 1][j - 1].getEtat() + this.Plateau[i + 1][j + 1].getEtat() + this.Plateau[i + 1][j].getEtat();
        }
        /*si on est sur la première ligne du plateau. Il suffit de remplacer tous les i-1 par la dernière ligne du plateau*/
        if (i == 0) {
            compteur = this.Plateau[24][j - 1].getEtat() + this.Plateau[i][j - 1].getEtat() + this.Plateau[i][j + 1].getEtat() + this.Plateau[24][j].getEtat() + this.Plateau[24][j + 1].getEtat() + this.Plateau[i + 1][j - 1].getEtat() + this.Plateau[i + 1][j + 1].getEtat() + this.Plateau[i + 1][j].getEtat();
        }
        /*si on est sur la dernière ligne du plateau.*/
        if (i == 24) {
            compteur = this.Plateau[i - 1][j - 1].getEtat() + this.Plateau[i][j - 1].getEtat() + this.Plateau[i][j + 1].getEtat() + this.Plateau[i - 1][j].getEtat() + this.Plateau[i - 1][j + 1].getEtat() + this.Plateau[0][j - 1].getEtat() + this.Plateau[0][j + 1].getEtat() + this.Plateau[0][j].getEtat();
        }
        /*si on est sur la première colonne du plateau*/
        if (j == 0) {
            compteur = this.Plateau[i - 1][24].getEtat() + this.Plateau[i][24].getEtat() + this.Plateau[i][j + 1].getEtat() + this.Plateau[i - 1][j].getEtat() + this.Plateau[i - 1][j + 1].getEtat() + this.Plateau[i + 1][24].getEtat() + this.Plateau[i + 1][j + 1].getEtat() + this.Plateau[i + 1][j].getEtat();
        }
        /*si onest sur la dernière colonne du plateau.*/
        if (j == 24) {
            compteur = this.Plateau[i - 1][j - 1].getEtat() + this.Plateau[i][j - 1].getEtat() + this.Plateau[i][0].getEtat() + this.Plateau[i - 1][j].getEtat() + this.Plateau[i - 1][0].getEtat() + this.Plateau[i + 1][j - 1].getEtat() + this.Plateau[i + 1][0].getEtat() + this.Plateau[i + 1][j].getEtat();
        }
        return compteur;
    }

    @Override
    public void restart() {
        // On efface l'ecran
        this.Gui.reset();
        //On réinitialise le plateau de manière aléatoire
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                this.Plateau[i][j].setEtatAléatoire(1);
            }
        }
        //on affiche toute les cases
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                if (Plateau[i][j].getEtat() == 1) {
                    /*pour l'instant c'est juste sur le cas particulier d'une fenêtre gui de taille 500*500 et de cellule de taille 25*25 (provisoire) */
                    Gui.addGraphicalElement(new Rectangle(i * 500 / 25, j * 500 / 25, Color.BLUE, Color.WHITE, (i + 1) * 500 / 25, (j + 1) * 500 / 25));
                }
                if (Plateau[i][j].getEtat() == 0) {
                    Gui.addGraphicalElement(new Rectangle(i * 500 / 25, j * 500 / 25, Color.WHITE, Color.WHITE, (i + 1) * 500 / 25, (j + 1) * 500 / 25));
                }
            }

        }
    }

    @Override
    public void next() {
        // On efface l'écran
        Gui.reset();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                //si la cellule i,j du plateau est morte
                if (this.Plateau[i][j].getEtat() == 0) {
                    if (this.getNombreVoisinsVivants(i, j) > 2) {
                        /*si le nombre de voisins de la case morte est supérieur ou égal à 3 alors on crée une cellule vivante( */
                        Gui.addGraphicalElement(new Rectangle(i * 500 / 25, j * 500 / 25, Color.BLUE, Color.WHITE, (i + 1) * 500 / 25, (j + 1) * 500 / 25));
                    } //sinon on redessine une cellule morte
                    else {
                        Gui.addGraphicalElement(new Rectangle(i * 500 / 25, j * 500 / 25, Color.WHITE, Color.WHITE, (i + 1) * 500 / 25, (j + 1) * 500 / 25));
                    }
                }
                //si la cellule i,j du plateai est vivante
                if (this.Plateau[i][j].getEtat() == 1) {
                    /*si le nombre de voisins de la case vivante est supérieur ou égal à 2 alors la cellule reste vivante( */
                    if (this.getNombreVoisinsVivants(i, j) > 1) {
                        Gui.addGraphicalElement(new Rectangle(i * 500 / 25, j * 500 / 25, Color.BLUE, Color.WHITE, (i + 1) * 500 / 25, (j + 1) * 500 / 25));
                    } //sinon elle meurt
                    else {
                        Gui.addGraphicalElement(new Rectangle(i * 500 / 25, j * 500 / 25, Color.WHITE, Color.WHITE, (i + 1) * 500 / 25, (j + 1) * 500 / 25));
                    }
                }
            }
        }
    }
}
