/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.*;
import java.awt.Point;
import java.util.Vector;

/**
 *
 * @author sacha
 */
public class Boids extends Cellule {

    private int angle;
    private Point vitesse;
    private float rayonAction;
    private Stack<Boids> voisins;

    public Boids() {
        this.angle = 0;
        this.vitesse = new Point();
        this.rayonAction = 50;
        this.voisins = new Stack();
    }

    public Boids(double x, double y) {
        this.angle = 0;
        this.rayonAction = 50;
        this.vitesse = new Point();
        this.setLocalisation(x, y);
        this.voisins = new Stack(); 
    }

    public Boids(double x, double y, double Vx, double Vy) {
        this.angle = 0;
        this.rayonAction = 50;
        this.vitesse = new Point();
        this.setLocalisation(x, y);
        this.vitesse.setLocation(Vx, Vy);
        this.voisins = new Stack();
    }

    //setAngle permet de regler l'angle avec la verticale du boid
    public void setAngle(int valeur) {
        this.angle = valeur;
    }

    //addVoisin permet d'ajouter un voisin à la liste de voisins
    public void addVoisin(Boids b) {
        this.voisins.add(b);
    }

    //setRayon permet de regler le rayon d'action des voisins
    public void setRayon(float r) {
        this.rayonAction = r;
    }

    public float getRayon() {
        return this.rayonAction;
    }

    //centre de masse renvoie le centre de masse des voisins du boids concerné
    public Point centreDeMasse() {
        int index = 0;
        Point centreMasse = new Point();
        int taille = this.voisins.size();
        while (index != taille) {
            centreMasse.setLocation(centreMasse.getX() + this.voisins.get(index).getlocalisation().getX(),
                    centreMasse.getY() + this.voisins.get(index).getlocalisation().getY());
            index++;
        }
        centreMasse.setLocation(centreMasse.getX() / taille, centreMasse.getY() / taille);
        return centreMasse;
    }

    //regle 1 : les boids veulent aller en direction du centre de masse de leur voisins. 
    //regle1 renvoie un vecteur de déplacement vers le centre de masse des voisins. 
    //Déplacement à hauteur de 1% de la distance du boids avec le centre de masse des voisins.
    public Point regle1() {
        Point deplacement = new Point();
        deplacement.setLocation(this.centreDeMasse().getX() - this.getlocalisation().getX(),
                this.centreDeMasse().getY() - this.getlocalisation().getY());
        deplacement.setLocation(deplacement.getX() / 100, deplacement.getY() / 100);
        return deplacement;
    }

    //distanceCarre renvoie la distance au carré du boid concerné à un point donné p
    public double distanceCarre(Point p) {
        double distanceX = this.getlocalisation().getX() - p.getX();
        double distanceY = this.getlocalisation().getY() - p.getY();
        return distanceX * distanceX + distanceY * distanceY;
    }

//regle2 permet d'éviter que les boids ne se rentrent dedans.
    public Point regle2() {
        Point deplacement = new Point();
        int index = 0;
        double x = 0;
        double y = 0;
        int taille = this.voisins.size();
        while (index != taille) {
            if (this.distanceCarre(this.voisins.get(index).getlocalisation()) < 10
                    || this.distanceCarre(this.voisins.get(index).getlocalisation()) > -10) {
                x = this.getlocalisation().getX() - this.voisins.get(index).getlocalisation().getX();
                y = this.getlocalisation().getY() - this.voisins.get(index).getlocalisation().getY();
                deplacement.setLocation(deplacement.getX() - x, deplacement.getY() - y);
                index++;

            }
        }
        return deplacement;
    }

    //vitesseMoyenne renvoie un Point qui contient les valeurs de la vitesse moyenne selon x et selon y des boids entourant le boid
    public Point vitesseMoyenne() {
        int index = 0;
        Point vitesse = new Point();
        int taille = this.voisins.size();
        while (index != taille) {
            vitesse.setLocation(vitesse.getX() + this.voisins.get(index).vitesse.getX(), vitesse.getY() + this.voisins.get(index).vitesse.getY());
            index++;
        }
        vitesse.setLocation(vitesse.getX() / taille, vitesse.getY() / taille);
        return vitesse;
    }

    // la regle 3 est qu'un boid va se rapprocher de la vitesse moyenne de ses voisins.
    //regle3 renvoie un point qui contient les valeurs de la nouvelle vitesse du boid du à cette règle
    public Point regle3() {
        Point deplacement = new Point();
        deplacement.setLocation(this.vitesseMoyenne().getX() - this.vitesse.getX(),
                this.vitesseMoyenne().getY() - this.vitesse.getY());
        deplacement.setLocation(deplacement.getX() / 8, deplacement.getY() / 8);
        return deplacement;
    }

//moveBoid permet de mettre le boid dans l'état suivant en terme de position et de vitesse suivant les différentes règles définies
    public void moveBoid() {
        this.vitesse.setLocation(this.vitesse.getX() + this.regle1().getX() + this.regle2().getX() + this.regle3().getX(),
                this.vitesse.getY() + this.regle1().getY() + this.regle2().getY() + this.regle3().getY());
        this.setLocalisation(this.getlocalisation().getX() + this.vitesse.getX(),
                this.getlocalisation().getY() + this.vitesse.getY());

    }
    
    @Override
    public String toString() {
        return "le boid se trouve à la position : " + this.getlocalisation().toString() + " et sa vitesse est : " + this.vitesse.toString();
    }

}
