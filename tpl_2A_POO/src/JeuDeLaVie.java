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
    
    
    //constructeur par défaut crée un tableau de 25*25 cellules initialisées aléatoirement
    public JeuDeLaVie() {
        this.nombreCelluleHauteur = 25;
        this.nombreCelluleLongueur = 25;
        this.plateau = new Cellule[nombreCelluleHauteur][nombreCelluleLongueur];
        this.sauvegardeEtat = new int[nombreCelluleHauteur][nombreCelluleLongueur];
        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                plateau[i][j] = new Cellule();
                this.probabilité = (float)0.2;
            }
        }
         this.sizeSimX = 500;
        this.sizeSimY = 500;
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);

    }
    
    public void setSizeSim(int sizeSimX, int sizeSimY) {
        this.sizeSimX = sizeSimX;
        this.sizeSimY = sizeSimY;
    }
    
    public int getSizeSimX() {
        return this.sizeSimX;
    }
    
    public int getSizeSimY() {
        return this.sizeSimY;
    }
    
    
    public void setNombreCellule(int NombreHauteur,int NombreLongueur) {
        this.nombreCelluleHauteur = NombreHauteur;
        this.nombreCelluleLongueur = NombreLongueur;
        this.plateau = new Cellule[this.getNombreCelluleHauteur()][this.getNombreCelluleLongueur()];
         for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                plateau[i][j] = new Cellule();
            }
            }
        this.sauvegardeEtat = new int[this.getNombreCelluleHauteur()][this.getNombreCelluleLongueur()];
    }

    
    public int getNombreCelluleHauteur() {
        return this.nombreCelluleHauteur;
    }
    
    public int getNombreCelluleLongueur() {
        return this.nombreCelluleLongueur;
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

    /*renvoie le nombre de voisins vivants pour la cellule du tableau à la ligne i, colonne j du plateau
    public int getNombreVoisinsVivants(int i, int j) {
        int compteur = 0;
        //si on est pas aux extrémités du tableau
        if (!(i == 0 || j == 0 || i == this.getNombreCelluleHauteur()-1 || j == this.getNombreCelluleLongueur()-1)) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        //si on est sur la première ligne du plateau. Il suffit de remplacer tous les i-1 par la dernière ligne du plateau
        if (i == 0 && j != 0 && j != this.getNombreCelluleLongueur()-1) {
            compteur = this.plateau[this.getNombreCelluleHauteur()-1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[this.getNombreCelluleHauteur()-1][j].getEtat() + this.plateau[this.getNombreCelluleHauteur()-1][j + 1].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        //si on est sur la dernière ligne du plateau.
        if (i == this.getNombreCelluleHauteur()-1 && j != 0 && j != this.getNombreCelluleLongueur()-1) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[0][j - 1].getEtat() + this.plateau[0][j + 1].getEtat() + this.plateau[0][j].getEtat();
        }
        //si on est sur la première colonne du plateau
        if (j == 0 && i != 0 && i != this.getNombreCelluleHauteur()-1) {
            compteur = this.plateau[i - 1][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[i + 1][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        //si onest sur la dernière colonne du plateau.
        if (j == this.getNombreCelluleLongueur()-1 && i != 0 && i != this.getNombreCelluleHauteur()-1) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][0].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][0].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][0].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        //si on est dans le coin en haut a gauche
        if (i == 0 && j == 0) {
            compteur = this.plateau[this.getNombreCelluleHauteur()-1][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[this.getNombreCelluleHauteur()-1][j].getEtat() + this.plateau[this.getNombreCelluleHauteur()-1][j + 1].getEtat() + this.plateau[i + 1][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i + 1][j + 1].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        //si on est en dans le coin en bas a droite
        if (i == this.getNombreCelluleHauteur()-1 && j == this.getNombreCelluleLongueur()-1) {
            compteur = this.plateau[i - 1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][0].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][0].getEtat() + this.plateau[0][j - 1].getEtat() + this.plateau[0][0].getEtat() + this.plateau[0][j].getEtat();
        }
        //si on est dans le coin en bas a gauche (j-1 devient this.getNombreCelluleLongueur()-1 et i+1 devient 0)
        if (i == this.getNombreCelluleHauteur()-1 && j == 0) {
            compteur = this.plateau[i - 1][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[i][j + 1].getEtat() + this.plateau[i - 1][j].getEtat() + this.plateau[i - 1][j + 1].getEtat() + this.plateau[0][this.getNombreCelluleLongueur()-1].getEtat() + this.plateau[0][j + 1].getEtat() + this.plateau[0][j].getEtat();
        }
        //si on est dans le coin en haut a droite (i-1 devient this.getNombreCelluleHauteur()-1 et j+1 devient 0)
        if (i == 0 && j == this.getNombreCelluleLongueur()-1) {
            compteur = this.plateau[this.getNombreCelluleHauteur()-1][j - 1].getEtat() + this.plateau[i][j - 1].getEtat() + this.plateau[i][0].getEtat() + this.plateau[this.getNombreCelluleHauteur()-1][j].getEtat() + this.plateau[this.getNombreCelluleHauteur()-1][0].getEtat() + this.plateau[i + 1][j - 1].getEtat() + this.plateau[i + 1][0].getEtat() + this.plateau[i + 1][j].getEtat();
        }
        return compteur;
    }
	*/
	
    public int getNombreVoisinsVivants(int i, int j) {
        int compteur = 0;
		for (int a = -1; a<2; a++){
			for (int b = -1; b<2; b++){
			compteur = compteur + plateau[(i + a) % nombreCelluleHauteur][(j + b) % nombreCelluleLongueur].getEtat();
			}
		}
		return compteur - plateau[i][j].getEtat();
	}




    @Override
    public void restart() {
        // On efface l'ecran
        System.out.println("ca recommence");
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
                if (plateau[i][j].getEtat() == 1) {
                    /*pour l'instant c'est juste sur le cas particulier d'une fenêtre gui de taille SizeSimX*SizeSimY et de cellule de taille NombreCelluleHauteur()*NombreCelluleLongueur() (provisoire) */
                    gui.addGraphicalElement(new Rectangle(j * getSizeSimX() / this.getNombreCelluleLongueur() + 10 , i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.BLUE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                }
                if (plateau[i][j].getEtat() == 0) {
                    gui.addGraphicalElement(new Rectangle(j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10, i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.WHITE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                }
            }
        }

    }

    @Override
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
                        gui.addGraphicalElement(new Rectangle(j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10, i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.BLUE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                    } //sinon on redessine une cellule morte
                    else {
                        this.sauvegardeEtat[i][j] = 0;
                        gui.addGraphicalElement(new Rectangle(j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10, i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.WHITE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                    }
                }
                //si la cellule i,j du plateau est vivante
                if (this.plateau[i][j].getEtat() == 1) {
                    /*si le nombre de voisins de la case vivante est supérieur ou égal à 2 alors la cellule reste vivante( */
                    if (this.getNombreVoisinsVivants(i, j) > 1) {
                        this.sauvegardeEtat[i][j] = 1;
                        gui.addGraphicalElement(new Rectangle(j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10, i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.BLUE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                    } //sinon elle meurt
                    else {
                       this.sauvegardeEtat[i][j] = 0;;
                        gui.addGraphicalElement(new Rectangle(j * this.getSizeSimX() / this.getNombreCelluleLongueur() + 10, i * this.getSizeSimY() / this.getNombreCelluleHauteur() + 10, Color.BLACK, Color.WHITE, this.getSizeSimX() / this.getNombreCelluleLongueur(), this.getSizeSimY() / this.getNombreCelluleHauteur()));
                    }
                }
            }
        }

        for (int i = 0; i < this.getNombreCelluleHauteur(); i++) {
            for (int j = 0; j < this.getNombreCelluleLongueur(); j++) {
                plateau[i][j].setEtat(this.sauvegardeEtat[i][j]);
            }
        }
    }
}