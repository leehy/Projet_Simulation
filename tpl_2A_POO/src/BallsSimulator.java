/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;
import gui.Simulable;
import static java.awt.Color.red;

/**
 *
 * @author hyun
 */
public class BallsSimulator implements Simulable {

    private Balls balls;
    private GUISimulator gui;
    
    public BallsSimulator() {
        this.balls = new Balls();
        this.gui = new GUISimulator(20,20,red);
    }
    /*
    public BallsSimulator(Balls balls) {
        super(balls);
    }  
    
    public BallsSimulator(GUISimulator guiSimulator, Balls balls){
        this.guiSimulator = guiSimulator;
        this.balls=balls;
    } */
    
    @Override
    public void restart() {
        this.balls.reInit();
        this.gui.reset();
        System.out.println(this.balls.toString());
    }

    @Override
    public void next() {
        this.balls.translateBalls(2,2);
        this.gui.next();;
        System.out.println(this.balls.toString());
    }

}
