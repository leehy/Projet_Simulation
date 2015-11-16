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
import static java.lang.Math.abs;

/**
 *
 * @author hyun
 */
public class BallsSimulator extends Event implements Simulable {

    private Balls balls;
    private GUISimulator gui;

    //Attribut pour controler la taille de la simulation
    private int sizeSimX;
    private int sizeSimY;

    //Attribut pour choisir la vitesses des balles
    private int speedSimX;
    private int speedSimY;

    // Attribut pour integrer le gestionnaire d'evenement
    private ArrayList<Balls> ArrayOfBalls = new ArrayList<>();

    public BallsSimulator() {
        this.balls = new Balls();
        this.sizeSimX = 500;
        this.sizeSimY = 500;
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);
        this.speedSimX = 15;
        this.speedSimY = 25;
        this.ArrayOfBalls = new ArrayList<>();
    }

    public BallsSimulator(Balls balls, int date) {
        super(date);
        this.sizeSimX = 500;
        this.sizeSimY = 500;
        this.speedSimX = 20;
        this.speedSimY = 20;
        this.balls = new Balls(balls);
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);
        this.ArrayOfBalls.add(this.getBalls());
    }


    public BallsSimulator(Balls balls, int date, GUISimulator gui) {
        super(date);
        this.sizeSimX = 500;
        this.sizeSimY = 500;
        this.speedSimX = 20;
        this.speedSimY = 20;
        this.balls = new Balls(balls);
        this.gui = gui;
        this.ArrayOfBalls.add(this.getBalls());
    }

    public BallsSimulator(Balls balls, int sizeSimX, int sizeSimY) {
        this.balls = balls;
        this.sizeSimX = sizeSimX;
        this.sizeSimY = sizeSimY;
    }

    public BallsSimulator(Balls balls, int sizeSimX, int sizeSimY, int speedSimX, int speedSimY) {
        this.balls = balls;
        this.sizeSimX = sizeSimX;
        this.sizeSimY = sizeSimY;
        this.speedSimX = speedSimX;
        this.speedSimY = speedSimY;
    }

    public BallsSimulator(Balls balls, int sizeSimX, int sizeSimY, int speedSimX, int speedSimY, GUISimulator gui) {
        this.balls = balls;
        this.sizeSimX = sizeSimX;
        this.sizeSimY = sizeSimY;
        this.speedSimX = speedSimX;
        this.speedSimY = speedSimY;
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);
    }

    public BallsSimulator(Balls balls, int sizeSimX, int sizeSimY, int speedSimX, int speedSimY, GUISimulator gui, EventManager manager) {
        this.balls = balls;
        this.sizeSimX = sizeSimX;
        this.sizeSimY = sizeSimY;
        this.speedSimX = speedSimX;
        this.speedSimY = speedSimY;
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);
        this.ArrayOfBalls = new ArrayList<>();
        this.ArrayOfBalls.add(this.balls);
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
        // On incremente le temps et on execute les prochains evenements prevu à la date CurrentDate + 1
        //this.execute();

        // On efface l'écran
        gui.reset();
        // Indice de ListPoint 
        int indexArray = 0;
        // On translate les balles 
        this.balls.translateBalls(this.getSpeedX(), this.getSpeedY());
        // On affiche sur l'écran
        System.out.println(this.balls.toString());
        // On dessine dans le simulateur 
        //Tant que la taille de la liste est différent de l'index
        while (this.balls.getSizeList() != indexArray) {
            gui.addGraphicalElement(new Oval((int) (this.balls.getListPoint().get(indexArray).getX()), (int) (this.balls.getListPoint().get(indexArray).getY()), Color.decode("#FFFFFF"), Color.decode("#FFFFFF"), 20, 20));

            // Traitement du rebond lorsqu'on se retrouve sur le cote droit du simulateur
            if (this.balls.getListPoint().get(indexArray).getX() > this.getSizeSimX() && (this.balls.getListPoint().get(indexArray).getY() < this.getSizeSimY() && this.balls.getListPoint().get(indexArray).getY() > 0)) {
                this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), this.balls.getSpeedCoeffY(indexArray));
                //System.out.println("Test1");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote haut droit du simulateur
            else if (this.balls.getListPoint().get(indexArray).getX() >= this.getSizeSimX() && (this.balls.getListPoint().get(indexArray).getY() <= 0)) {
                //System.out.println("Test1.1");
                if (this.balls.getListPoint().get(indexArray).getX() == this.getSizeSimX()) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), this.balls.getSpeedCoeffY(indexArray));
                    //System.out.println("Test1.11");
                } else if (this.balls.getListPoint().get(indexArray).getY() == 0) {
                    this.balls.setSpeedCoeff(indexArray, this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                    //System.out.println("Test1.12");
                } else if (this.balls.getListPoint().get(indexArray).getX() > this.getSizeSimX() && (this.balls.getListPoint().get(indexArray).getY() < 0)) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test1.13");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote gauche du simulateur
            else if (this.balls.getListPoint().get(indexArray).getX() < 0 && (this.balls.getListPoint().get(indexArray).getY() < this.getSizeSimY() && this.balls.getListPoint().get(indexArray).getY() > 0)) {
                this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), this.balls.getSpeedCoeffY(indexArray));
                //System.out.println("Test2");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote bas droit du simulateur
            else if (this.balls.getListPoint().get(indexArray).getX() >= this.getSizeSimX() && (this.balls.getListPoint().get(indexArray).getY() >= this.getSizeSimY())) {
                //System.out.println("Test2.1");
                if (this.balls.getListPoint().get(indexArray).getX() == this.getSizeSimX()) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), this.balls.getSpeedCoeffY(indexArray));
                    //System.out.println("Test2.11");
                } else if (this.balls.getListPoint().get(indexArray).getY() == this.getSizeSimY()) {
                    this.balls.setSpeedCoeff(indexArray, this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                    //System.out.println("Test2.12");
                } else if (this.balls.getListPoint().get(indexArray).getX() > this.getSizeSimX() && (this.balls.getListPoint().get(indexArray).getY() > this.getSizeSimY())) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test2.13");

                if (this.balls.getSpeedCoeffX(indexArray) > 0) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), this.balls.getSpeedCoeffY(indexArray));
                } else if (this.balls.getSpeedCoeffY(indexArray) > 0) {
                    this.balls.setSpeedCoeff(indexArray, this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                }
            } // Traitement du rebond lorsqu'on est sur le cote bas du simulateur
            else if (this.balls.getListPoint().get(indexArray).getY() > this.getSizeSimY() && this.balls.getListPoint().get(indexArray).getX() < this.getSizeSimX() && this.balls.getListPoint().get(indexArray).getX() > 0) {
                this.balls.setSpeedCoeff(indexArray, this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));

                //System.out.println("Test3");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote bas gauche du simulateur
            else if (this.balls.getListPoint().get(indexArray).getX() <= 0 && (this.balls.getListPoint().get(indexArray).getY() >= this.getSizeSimY())) {
                //System.out.println("Test3.1");
                if (this.balls.getListPoint().get(indexArray).getX() == 0) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), this.balls.getSpeedCoeffY(indexArray));
                    //System.out.println("Test3.11");
                } else if (this.balls.getListPoint().get(indexArray).getY() == this.getSizeSimY()) {
                    this.balls.setSpeedCoeff(indexArray, this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                    // System.out.println("Test3.12");
                } else if (this.balls.getListPoint().get(indexArray).getX() < 0 && (this.balls.getListPoint().get(indexArray).getY() > this.getSizeSimY())) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test3.13");
            } //Traitement du rebond lorsqu'on est sur le cote haut du simulateur
            else if (this.balls.getListPoint().get(indexArray).getY() < 0 && this.balls.getListPoint().get(indexArray).getX() < this.getSizeSimX() && this.balls.getListPoint().get(indexArray).getX() > 0) {
                this.balls.setSpeedCoeff(indexArray, this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                //System.out.println("Test4");
            } //Traitement du rebond lorsqu'on se retrouve sur le cote haut gauche du simulateur
            else if (this.balls.getListPoint().get(indexArray).getY() <= 0 && (this.balls.getListPoint().get(indexArray).getX() <= 0)) {
                //System.out.println("Test4.1");
                if (this.balls.getListPoint().get(indexArray).getX() == 0) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), this.balls.getSpeedCoeffY(indexArray));
                    //System.out.println("Test4.11");
                } else if (this.balls.getListPoint().get(indexArray).getY() == 0) {
                    this.balls.setSpeedCoeff(indexArray, this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                    //System.out.println("Test4.12");
                } else if (this.balls.getListPoint().get(indexArray).getX() < 0 && (this.balls.getListPoint().get(indexArray).getY() < 0)) {
                    this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test4.13");
            } else if ((this.balls.getListPoint().get(indexArray).getX() == 0 && this.balls.getListPoint().get(indexArray).getY() == 0)
                    || (this.balls.getListPoint().get(indexArray).getX() == 0 && this.balls.getListPoint().get(indexArray).getY() == this.getSizeSimY())
                    || (this.balls.getListPoint().get(indexArray).getX() == this.getSizeSimX() && this.balls.getListPoint().get(indexArray).getY() == 0)
                    || (this.balls.getListPoint().get(indexArray).getX() == this.getSizeSimX() && this.balls.getListPoint().get(indexArray).getY() == this.getSizeSimY())) {
                this.balls.setSpeedCoeff(indexArray, -this.balls.getSpeedCoeffX(indexArray), -this.balls.getSpeedCoeffY(indexArray));
                //System.out.println("Test5");
            }

            //System.out.println("SpeedX de " + this.balls.getListPoint().get(indexArray) + " est " + this.balls.getSpeedCoeffX(indexArray));
            //System.out.println("SpeedY de " + this.balls.getListPoint().get(indexArray) + " est " + this.balls.getSpeedCoeffY(indexArray));
            indexArray++;

        }
    }

    @Override
    public void execute() {
        // On efface l'écran
        gui.reset();
        // Indice de ListPoint 
        int indexArray = 0;
        // Indice du tableau de Balls
        int indexArrayOfBalls = 0;

     // On translate la balle
        //while (indexArrayOfBalls != this.ArrayOfBalls.size()) {
        //this.ArrayOfBalls.get(indexArrayOfBalls).translateBalls(this.getSpeedX(), this.getSpeedY());
        this.balls.translateBalls(this.getSpeedX(), this.getSpeedY());
        // On affiche sur l'écran
        System.out.println(this.ArrayOfBalls.get(indexArrayOfBalls).toString());
        // On affiche dans le simulateur
        while (indexArray != this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().size()) {

            gui.addGraphicalElement(new Oval((int) (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX()), (int) (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY()), Color.decode("#FFFFFF"), Color.decode("#FFFFFF"), 20, 20));

            // Traitement du rebond lorsqu'on se retrouve sur le cote droit du simulateur
            if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() > this.getSizeSimX() && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() < this.getSizeSimY() && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() > 0)) {
                this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                //System.out.println("Test1");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote haut droit du simulateur
            else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() >= this.getSizeSimX() && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() <= 0)) {
                //System.out.println("Test1.1");
                if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == this.getSizeSimX()) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    //System.out.println("Test1.11");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == 0) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    //System.out.println("Test1.12");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() > this.getSizeSimX() && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() < 0)) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test1.13");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote gauche du simulateur
            else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() < 0 && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() < this.getSizeSimY() && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() > 0)) {
                this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                //System.out.println("Test2");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote bas droit du simulateur
            else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() >= this.getSizeSimX() && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() >= this.getSizeSimY())) {
                //System.out.println("Test2.1");
                if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == this.getSizeSimX()) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    //System.out.println("Test2.11");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == this.getSizeSimY()) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    //System.out.println("Test2.12");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() > this.getSizeSimX() && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() > this.getSizeSimY())) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test2.13");

                if (this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray) > 0) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray) > 0) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                }
            } // Traitement du rebond lorsqu'on est sur le cote bas du simulateur
            else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() > this.getSizeSimY() && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() < this.getSizeSimX() && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() > 0) {
                this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));

                //System.out.println("Test3");
            } // Traitement du rebond lorsqu'on se retrouve sur le cote bas gauche du simulateur
            else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() <= 0 && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() >= this.getSizeSimY())) {
                //System.out.println("Test3.1");
                if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == 0) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    //System.out.println("Test3.11");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == this.getSizeSimY()) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    // System.out.println("Test3.12");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() < 0 && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() > this.getSizeSimY())) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test3.13");
            } //Traitement du rebond lorsqu'on est sur le cote haut du simulateur
            else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() < 0 && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() < this.getSizeSimX() && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() > 0) {
                this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                //System.out.println("Test4");
            } //Traitement du rebond lorsqu'on se retrouve sur le cote haut gauche du simulateur
            else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() <= 0 && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() <= 0)) {
                //System.out.println("Test4.1");
                if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == 0) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    //System.out.println("Test4.11");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == 0) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                    //System.out.println("Test4.12");
                } else if (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() < 0 && (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() < 0)) {
                    this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                }
                //System.out.println("Test4.13");
            } else if ((this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == 0 && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == 0)
                    || (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == 0 && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == this.getSizeSimY())
                    || (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == this.getSizeSimX() && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == 0)
                    || (this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getX() == this.getSizeSimX() && this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray).getY() == this.getSizeSimY())) {
                this.ArrayOfBalls.get(indexArrayOfBalls).setSpeedCoeff(indexArray, -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray), -this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
                //System.out.println("Test5");
            }

            System.out.println("SpeedX de " + this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray) + " est " + this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffX(indexArray));
            System.out.println("SpeedY de " + this.ArrayOfBalls.get(indexArrayOfBalls).getListPoint().get(indexArray) + " est " + this.ArrayOfBalls.get(indexArrayOfBalls).getSpeedCoeffY(indexArray));
            indexArray++;

        }

        indexArrayOfBalls++;
    }
}
