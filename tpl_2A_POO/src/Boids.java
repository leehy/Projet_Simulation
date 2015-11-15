/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.*;
import java.awt.Color;
import java.util.*;
import java.awt.Point;
import static java.lang.Math.abs;
import java.util.Vector;
import static java.lang.Math.atan;

/**
 *
 * @author sacha
 */
public class Boids extends Cellule {

    private double angle;
    private Point vitesse;
    //rayonAction est le rayon au delà duquel les autres boids ne sont plus concernés comme des voisins
    private int rayonAction;
    //rayonSécurité est le rayon en-decà duquel les autres boids sont concernés comme trop proche, il faut donc changer de direction
    private int rayonSecurite;
    private Stack<Boids> voisins;
    private int tailleFenetreHauteur;
    private int tailleFenetreLongueur;

      //ce constructeur crée un Boids sans angle, avec un rayonAction de 100, un rayonSecurite de 10 une vitesse (Vx,Vy)
    //une position (x,y), sans voisins, avec une taille de fenetre gui de tailleHauteur et tailleLargeur
    public Boids(int x, int y, int Vx, int Vy, int r, int rSecurite, int tailleLongueur, int tailleHauteur) {
        this.angle = 0;
        this.setEtat(0); //par défaut un boid est à l'état 0 
        this.rayonAction = r;
        this.rayonSecurite = rSecurite;
        this.vitesse = new Point();
        this.setLocalisation(x, y);
        this.vitesse.setLocation(Vx, Vy);
        this.voisins = new Stack();
        this.tailleFenetreHauteur = tailleHauteur;
        this.tailleFenetreLongueur = tailleLongueur;
    }

    public double getAngle() {
        return this.angle;
    }

    public Point getVitesse() {
        return this.vitesse;
    }

    public Stack<Boids> getVoisins() {
        return this.voisins;
    }

    //getRayonSecurite renvoie le rayonSecurite 
    public int getRayonSecurite() {
        return this.rayonSecurite;
    }

    //EstEgal permet de savoir si le boid concerné est égal à un autre Boid
    public boolean estEgal(Boids b) {
        if (this.rayonAction == b.getRayonAction() && this.rayonSecurite == b.getRayonSecurite() && this.angle == b.getAngle() && this.vitesse == b.getVitesse() && this.voisins == b.getVoisins() && this.getlocalisation() == b.getlocalisation()) {
            return true;
        } else {
            return false;
        }
    }

//estVraimentVoisin permet de savoir si le boid donné en argument est vraiment un voisin du boid concerné
    public boolean estVraimentVoisin(Boids b) {
        if (this.rayonAction * this.rayonAction > distanceCarre(b.getlocalisation())) {
            return true;
        } else {
            return false;
        }
    }

    //setVoisins récupère une pile de voisins potentiels et rempli la pile des voisins
    public void setVoisins(Stack<Boids> PileVoisinsPotentiels) {

        try {
                        
                      

            Boids b = PileVoisinsPotentiels.pop();
            //on vérifie si l'élément de la pile est vraiment un voisin de notre boid
            if (this.estVraimentVoisin(b)) //si oui on l'ajoute dans la liste des voisins
            {
                this.voisins.push(b);

            }
            setVoisins(PileVoisinsPotentiels);
        } catch (EmptyStackException e) {
        }

    }

    //setAngle permet de regler l'angle avec la verticale du boid. Elle renvoie un angle compris entre -Pi/2 et Pi/2
    public void setAngle(int valeur) {
        this.angle = atan(vitesse.getX() / vitesse.getY());
    }

    //addVoisin permet d'ajouter un voisin à la liste de voisins
    public void addVoisin(Boids b) {
        this.voisins.add(b);
    }

    public int getRayonAction() {
        return this.rayonAction;
    }

    //centre de masse renvoie le centre de masse des voisins du boids concerné
    public Point centreDeMasse() {
        int index = 0;
        Point centreMasse = new Point();
        int taille = this.voisins.size();
        while (index != taille) {
            centreMasse.setLocation(centreMasse.getX() + this.voisins.get(index).getlocalisation().getX() / taille,
                    centreMasse.getY() + this.voisins.get(index).getlocalisation().getY() / taille);
            index++;
        }
        return centreMasse;
    }

    //regle 1 : les boids veulent aller en direction du centre de masse de leur voisins. 
    //regle1 renvoie un vecteur de déplacement vers le centre de masse des voisins. 
    //Déplacement à hauteur de 1% de la distance du boids avec le centre de masse des voisins.
    public Point regle1() {
        Point deplacement = new Point();
        //la regle 1 ne s'applique pas si il n'y pas de voisins !
        if (this.voisins.size() != 0) {

            deplacement.setLocation(this.centreDeMasse().getX() - this.getlocalisation().getX(),
                    this.centreDeMasse().getY() - this.getlocalisation().getY());
        }
        deplacement.setLocation((deplacement.getX()/100 ), (deplacement.getY()/100 ));
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
        while (index < taille) {
            if (this.distanceCarre(this.voisins.get(index).getlocalisation()) < this.rayonSecurite * this.rayonSecurite) //si on est trop près du boid voisin
            {
                x = this.getlocalisation().getX() - this.voisins.get(index).getlocalisation().getX();
                y = this.getlocalisation().getY() - this.voisins.get(index).getlocalisation().getY();
                //ainsi le vecteur de deplacement sera pour chaque boid dans le sens opposé de l'autre boid
                deplacement.setLocation(deplacement.getX() + x, deplacement.getY() + y);
            }
            index++;
        }
        return deplacement;
    }

    //vitesseMoyenne renvoie un Point qui contient les valeurs de la vitesse moyenne selon x et selon y des boids entourant le boid
    public Point vitesseMoyenne() {
        int index = 0;
        Point vitesse = new Point();
        int taille = this.voisins.size();
        while (index != taille) {
            vitesse.setLocation(vitesse.getX() + this.voisins.get(index).vitesse.getX(),
                    vitesse.getY() + this.voisins.get(index).vitesse.getY());
            index++;
        }
        vitesse.setLocation(vitesse.getX() / taille, vitesse.getY() / taille);
        return vitesse;
    }

    // la regle 3 est qu'un boid va se rapprocher de la vitesse moyenne de ses voisins.
    //regle3 renvoie un point qui contient les valeurs de la nouvelle vitesse du boid du à cette règle
    public Point regle3() {
        Point deplacement = new Point();
        //la regle 3 ne s'applique pas si il n'y a pas de voisins
        if(this.voisins.size() != 0)   {  
        deplacement.setLocation(this.vitesseMoyenne().getX() - this.vitesse.getX(),
                this.vitesseMoyenne().getY() - this.vitesse.getY());
        deplacement.setLocation(deplacement.getX() / 8, deplacement.getY() / 8);
        }
        return deplacement;
       
    }

//moveBoid permet de mettre le boid dans l'état suivant en terme de position et de vitesse suivant les différentes règles définies.
//il faut lui envoyer une liste de voisins potentiels afin qu'il calcule les véritables voisins et ainsi que les règles puissent être appliqués    
    public void moveBoid(Stack<Boids> PileVoisinsPotentiels) {
        this.setVoisins(PileVoisinsPotentiels);
        this.vitesse.setLocation(this.vitesse.getX() + this.regle1().getX() + this.regle2().getX()  /*this.regle3().getX()*/,
                this.vitesse.getY() + this.regle1().getY() + this.regle2().getY()/* + this.regle3().getY()*/);
        this.setLocalisation(this.getlocalisation().getX() + this.vitesse.getX(),
                this.getlocalisation().getY() + this.vitesse.getY());
        this.voisins.clear(); //on réinitialise la liste de voisins à 0 pour la prochaine étape

    }

    @Override
    public String toString() {
        return "le boid se trouve à la position : " + this.getlocalisation().toString() + " et sa vitesse est : " + this.vitesse.toString();
    }

    //calcul x modulo y

    public int mod(int x, int y) {
        return (x + y) % y;
    }

    //access renvoie un point dont les coordonnées sont modulées
    private Point access(Point v) { //v doit être un couple (x,y)
        Point p = new Point();
        p.x = mod(v.x, this.tailleFenetreHauteur);
        p.y = mod(v.y, this.tailleFenetreLongueur);
        return p;
    }

    public void afficheBoid(GUISimulator gui) {

        //on affiche l'élément que si on est dans la fenêtre de gui
        if (this.getlocalisation().getX() < this.tailleFenetreLongueur && this.getlocalisation().getX() > 0
                && this.getlocalisation().getY() < this.tailleFenetreHauteur && this.getlocalisation().getY() > 0) {
            gui.addGraphicalElement(new Rectangle(
                    (int) this.getlocalisation().getX(),
                    (int) this.getlocalisation().getY(),
                    Color.GREEN,
                    Color.WHITE,
                    5, 5));
        }

    }

}
