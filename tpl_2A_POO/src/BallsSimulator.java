/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;
import gui.Simulable;
import java.awt.Color;
import gui.Oval;
import java.util.ArrayList;
import java.lang.Math;
import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 *
 * @author hyun
 */
public class BallsSimulator implements Simulable {

    private Balls balls;
    private GUISimulator gui;

    //Attribut pour controler la taille de la simulation
    private int sizeSimX;
    private int sizeSimY;

    //Attribut pour choisir la vitesses des balles
    private int speedSimX;
    private int speedSimY;

    public BallsSimulator() {
        this.balls = new Balls();
        this.sizeSimX = 500;
        this.sizeSimY = 500;
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);
        this.speedSimX = 30;
        this.speedSimY = 20;
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

    public void setSpeed(int x, int y) {
        this.speedSimX = x;
        this.speedSimY = y;
    }

    public int getSpeedX() {
        return this.speedSimX;
    }

    public int getSpeedY() {
        return this.speedSimY;
    }

    public GUISimulator getGuiSimulator() {
        return this.gui;
    }

    public Balls getBalls() {
        return this.balls;
    }

    @Override
    public void restart() {
        // index de parcours du tableau
        int index = 0;
        // On reinitialise
        this.balls.reInit();
        // On efface l'ecran
        this.gui.reset();

        // On affiche les coordonnées
        System.out.println(this.balls.toString());

        //Tant que la taille de la liste est différent de l'index
        while (this.balls.getSizeList() != index) {
            //On dessine un objet oval de coordonnées du Point d'indice index, à la couleur #FFFFFF et qui a une taille de 20
            gui.addGraphicalElement(
                    new Oval((int) (this.balls.getListPoint().get(index).getX()), (int) (this.balls.getListPoint().get(index).getY()),
                            Color.decode("#FFFFFF"), Color.decode("#FFFFFF"), 20, 20));
            //On remet les directions de deplacement dans le sens normal
            this.balls.setSpeedCoeff(index, abs(this.balls.getSpeedCoeffX(index)), abs(this.balls.getSpeedCoeffY(index)));
            index++;
        }
    }

    @Override
    public void next() {
        // Indice du tableau
        int index = 0;
        // On efface l'écran
        gui.reset();
        // On translate la balle
        this.balls.translateBalls(this.getSpeedX(), this.getSpeedY());

        //Traitement dans le cas ou il y a des balles aux coordonnees negatives
        /*while (this.balls.getSizeList() != index) {
            if (this.balls.getListPoint().get(index).getX() < 0 || this.balls.getListPoint().get(index).getY() < 0) {
                while (this.balls.getListPoint().get(index).getX() < 0 || this.balls.getListPoint().get(index).getY() < 0) {
                    this.balls.getListPoint().get(index).setLocation(
                            (int) (this.balls.getListPoint().get(index).getX() + abs(0.1 * this.getSpeedX())), (int) (this.balls.getListPoint().get(index).getY() + abs(0.1 * this.getSpeedY())));
                }
            }
            index++;
        } */
        // On affiche
        System.out.println(this.balls.toString());

        while (this.balls.getSizeList() != index) {
            // Hors ecran simulation 
            int maxPrevCoord = max((int) (this.balls.getListPoint().get(index).getX() - abs(this.speedSimX)), (int) (this.balls.getListPoint().get(index).getY() - abs(this.speedSimY)));

            gui.addGraphicalElement(
                    new Oval((int) (this.balls.getListPoint().get(index).getX()), (int) (this.balls.getListPoint().get(index).getY()),
                            Color.decode("#FFFFFF"), Color.decode("#FFFFFF"), 20, 20));
            
            // Traitement du rebond lorsqu'on se retrouve sur le cote droit du simulateur
            if (this.balls.getListPoint().get(index).getX() > this.getSizeSimX() && (this.balls.getListPoint().get(index).getY() < this.getSizeSimY() && this.balls.getListPoint().get(index).getY()>0)) {
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), this.balls.getSpeedCoeffY(index));
                System.out.println("Test1");
            } 
            
            // Traitement du rebond lorsqu'on se retrouve sur le cote haut droit du simulateur
            else if (this.balls.getListPoint().get(index).getX() >= this.getSizeSimX() && (this.balls.getListPoint().get(index).getY() <= 0)) {
                System.out.println("Test1.1");
                if(this.balls.getListPoint().get(index).getX() == this.getSizeSimX() ){
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), this.balls.getSpeedCoeffY(index));
                    System.out.println("Test1.11");
                }
                else if(this.balls.getListPoint().get(index).getY()==0){
                    this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                    System.out.println("Test1.12");
                }
                
                else if(this.balls.getListPoint().get(index).getX() > this.getSizeSimX() && (this.balls.getListPoint().get(index).getY() < 0))
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("Test1.13");
            } 
            
            // Traitement du rebond lorsqu'on se retrouve sur le cote gauche du simulateur
            else if (this.balls.getListPoint().get(index).getX() < 0 && (this.balls.getListPoint().get(index).getY() < this.getSizeSimY() && this.balls.getListPoint().get(index).getY() >0)){
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), this.balls.getSpeedCoeffY(index));
                System.out.println("Test2");
            } 
            
            // Traitement du rebond lorsqu'on se retrouve sur le cote bas droit du simulateur
            else if (this.balls.getListPoint().get(index).getX() >= this.getSizeSimX() && (this.balls.getListPoint().get(index).getY() >= this.getSizeSimY())) {
                System.out.println("Test2.1");
                if(this.balls.getListPoint().get(index).getX() == this.getSizeSimX() ){
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), this.balls.getSpeedCoeffY(index));
                    System.out.println("Test2.11");
                }
                else if(this.balls.getListPoint().get(index).getY()==this.getSizeSimY()){
                    this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                    System.out.println("Test2.12");
                }
                
                else if(this.balls.getListPoint().get(index).getX() > this.getSizeSimX() && (this.balls.getListPoint().get(index).getY() > this.getSizeSimY()))
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("Test2.13");
            }

            // Traitement du rebond lorsqu'on est sur le cote bas du simulateur
            else if (this.balls.getListPoint().get(index).getY() > this.getSizeSimY() && this.balls.getListPoint().get(index).getX() < this.getSizeSimX()  && this.balls.getListPoint().get(index).getX() >0) {
                this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("Test3");
            }
            
            // Traitement du rebond lorsqu'on se retrouve sur le cote bas gauche du simulateur
            else if (this.balls.getListPoint().get(index).getX() <=0 && (this.balls.getListPoint().get(index).getY() >= this.getSizeSimY())) {
                System.out.println("Test3.1");
                if(this.balls.getListPoint().get(index).getX() == 0 ){
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), this.balls.getSpeedCoeffY(index));
                    System.out.println("Test3.11");
                }
                else if(this.balls.getListPoint().get(index).getY()==this.getSizeSimY()){
                    this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                    System.out.println("Test3.12");
                }
                
                else if(this.balls.getListPoint().get(index).getX() < 0 && (this.balls.getListPoint().get(index).getY() > this.getSizeSimY()))
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("Test3.13");
            }

            //Traitement du rebond lorsqu'on est sur le cote haut du simulateur
            else if (this.balls.getListPoint().get(index).getY() < 0 && this.balls.getListPoint().get(index).getX() < this.getSizeSimX() && this.balls.getListPoint().get(index).getX() > 0) {
                this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("Test4");
            } 
            
            // Traitement du rebond lorsqu'on se retrouve sur le cote haut gauche du simulateur
            else if (this.balls.getListPoint().get(index).getY() <= 0 && (this.balls.getListPoint().get(index).getX() <= 0)) {
                System.out.println("Test4.1");
                if(this.balls.getListPoint().get(index).getX() == 0 ){
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), this.balls.getSpeedCoeffY(index));
                    System.out.println("Test4.11");
                }
                else if(this.balls.getListPoint().get(index).getY()==0){
                    this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                    System.out.println("Test4.12");
                }
                
                else if(this.balls.getListPoint().get(index).getX() < 0 && (this.balls.getListPoint().get(index).getY() < 0))
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("Test4.13");
            }
            
            else if(
                    (this.balls.getListPoint().get(index).getX()==0 && this.balls.getListPoint().get(index).getY() ==0) ||
                    (this.balls.getListPoint().get(index).getX()==0 && this.balls.getListPoint().get(index).getY() ==this.getSizeSimY()) ||
                    (this.balls.getListPoint().get(index).getX()==this.getSizeSimX() && this.balls.getListPoint().get(index).getY() ==0) ||
                    (this.balls.getListPoint().get(index).getX()==this.getSizeSimX() && this.balls.getListPoint().get(index).getY() ==this.getSizeSimY()) ){
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("Test5");
            }
            
            System.out.println("SpeedX de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffX(index));
            System.out.println("SpeedY de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffY(index));
            index++;
            
            
            /*else if((this.balls.getListPoint().get(index).getX()) <= 0 && (this.balls.getListPoint().get(index).getY()<this.getSizeSimY() && this.balls.getListPoint().get(index).getY()>0)){
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                    System.out.println("Test5");
                }
            // Traitement hors simulation general 
            else if ((this.balls.getListPoint().get(index).getX() >= this.getSizeSimX()) && (this.balls.getListPoint().get(index).getY() >= this.getSizeSimY()) 
            || ((this.balls.getListPoint().get(index).getX() <= 0) && (this.balls.getListPoint().get(index).getY() >= this.getSizeSimY()))
                    ||((this.balls.getListPoint().get(index).getY() >= this.getSizeSimY()) && (this.balls.getListPoint().get(index).getX() >= this.getSizeSimX()) )
                    ||((this.balls.getListPoint().get(index).getY()) <= 0 && (this.balls.getListPoint().get(index).getX() <= 0))){
                
                if((this.balls.getListPoint().get(index).getX()) <= 0 && (this.balls.getListPoint().get(index).getY()<this.getSizeSimY() && this.balls.getListPoint().get(index).getY()>0)){
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                    System.out.println("Test6");
                }
                
                else {
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                    System.out.println("Test7");
                }
            }
                /*
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index))
                */
            //Traitement du rebond lorsqu'on est sur le cote en bas a droite du simulateur 
            //else if (this.balls.getListPoint().get(index).getX() >= this.getSizeSimX() && this.balls.getListPoint().get(index).getY() >= this.getSizeSimY()) {

                /*Traitement si la vitesse etait trop rapide et vient du cote en dessous de la diagonale
                if (maxPrevCoord > this.balls.getListPoint().get(index).getX() - this.getSpeedX()) {
                    this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                } //Traitement si la balle vient de la diagonale
                else if ((abs(this.getSpeedX()) + abs(this.getSpeedY())) % this.getSpeedX() == 0 && (abs(this.getSpeedX()) + abs(this.getSpeedY())) % this.getSpeedY() == 0) {
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                } //Traitement si la balle etait trop rapide et vient du cote au dessus de la diagonale
                else if (maxPrevCoord > this.balls.getListPoint().get(index).getX() - this.getSpeedX()) {
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                } */
                /*this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("SpeedX de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffX(index));
                System.out.println("SpeedY de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffY(index));
            } 
            //Traitement du rebond lorsqu'on est sur le cote en bas a gauche du simulateur
            else if (this.balls.getListPoint().get(index).getX() <= 0 && this.balls.getListPoint().get(index).getY() >= this.getSizeSimY()) {
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("SpeedX de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffX(index));
                System.out.println("SpeedY de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffY(index));
            } 
            //Traitement du rebond lorsqu'on est sur le cote en haut a droite du simulateur
            else if (this.balls.getListPoint().get(index).getY() >= this.getSizeSimY() && this.balls.getListPoint().get(index).getX() >= this.getSizeSimX()) {
                this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("SpeedX de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffX(index));
                System.out.println("SpeedY de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffY(index));
            } 
            //Traitement du rebond lorsqu'on est sur le cote en haut a gauche du simulateur
            else if (this.balls.getListPoint().get(index).getY() <= 0 && this.balls.getListPoint().get(index).getX() <= 0) {

                /*Traitement si la vitesse etait trop rapide et vient du cote en dessous de la diagonale
                if (maxPrevCoord > abs(this.balls.getListPoint().get(index).getX() - this.getSpeedX()) || (this.balls.getListPoint().get(index).getX() == 0 && this.balls.getListPoint().get(index).getX() != 0)) {
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), this.balls.getSpeedCoeffY(index));

                } //Traitement si la balle vient de la diagonale
                else if ((abs(this.getSpeedX()) + abs(this.getSpeedY())) % this.getSpeedX() == 0 && (abs(this.getSpeedX()) + abs(this.getSpeedY())) % this.getSpeedY() == 0) {
                    this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                } //Traitement si la balle etait trop rapide et vient du cote au dessus de la diagonale
                else if (maxPrevCoord > abs(this.balls.getListPoint().get(index).getY() - this.getSpeedY())) {

                    this.balls.setSpeedCoeff(index, this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                } */
               /* this.balls.setSpeedCoeff(index, -this.balls.getSpeedCoeffX(index), -this.balls.getSpeedCoeffY(index));
                System.out.println("SpeedX de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffX(index));
                System.out.println("SpeedY de " + this.balls.getListPoint().get(index) + " est " + this.balls.getSpeedCoeffY(index));
            */
            
        }
    }

}
