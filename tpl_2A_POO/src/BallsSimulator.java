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
    //Attribut pour controler les vitesses de chaque balle
    private ArrayList<Integer> SpeedTranslateX;
    private ArrayList<Integer> SpeedTranslateY;
    
    public BallsSimulator() {
        int index=0;
        this.balls = new Balls();
        this.sizeSimX = 500;
        this.sizeSimY = 500;
        this.gui = new GUISimulator(sizeSimX, sizeSimY, Color.BLACK);
        /*while (this.balls.getSizeList() != index) {
            this.SpeedTranslateX.add(index, 20);
            this.SpeedTranslateY.add(index, 50);
            index++;
        } */
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
    
    public void setSpeedTranslate(int index, int x, int y){
        this.SpeedTranslateX.remove(index);
        this.SpeedTranslateX.add(index, x);
        this.SpeedTranslateY.remove(index);
        this.SpeedTranslateY.add(index, y);
    }
    
    public int getSpeedTranslateX(int index){
        return this.SpeedTranslateX.get(index);
    }
     
    public int getSpeedTranslateY(int index){
        return this.SpeedTranslateY.get(index);
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
            index++;
        }
    }
    
    @Override
    public void next() {
        int translateX = 20;
        int translateY = 40;
        // Indice du tableau
        int index = 0;
        // On efface l'écran
        gui.reset();
        // On translate la balle
        this.balls.translateBalls(translateX, translateY);
        // On affiche
        System.out.println(this.balls.toString());
        // De même que plus haut
        while (this.balls.getSizeList() != index) {
            gui.addGraphicalElement(
                    new Oval((int) (this.balls.getListPoint().get(index).getX()), (int) (this.balls.getListPoint().get(index).getY()),
                            Color.decode("#FFFFFF"), Color.decode("#FFFFFF"), 20, 20));
            // Si la position est <0 ou > à la taille d'un cote de la simulation, la vitesse (x ou y) a la
            // inverse
            /*
            if (this.balls.getListPoint().get(index).getX() >= this.getSizeSimX() ||
                this.balls.getListPoint().get(index).getX() <= 0){
                this.setSpeedTranslate(index, - this.getSpeedTranslateX(index), this.getSpeedTranslateY(index));
            }
            else if(this.balls.getListPoint().get(index).getY() >= this.getSizeSimY() ||
                    this.balls.getListPoint().get(index).getX() <= 0){
                this.setSpeedTranslate(index, this.getSpeedTranslateX(index), -this.getSpeedTranslateY(index));
            } */
            
            index++;
        }
    }
    
}
