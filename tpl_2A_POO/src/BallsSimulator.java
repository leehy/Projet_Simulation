/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;
import gui.Simulable;

/**
 *
 * @author hyun
 */
public class BallsSimulator extends Balls implements Simulable {

    private Balls balls;
    
    public BallsSimulator() {
        super();
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
        this.guiSimulator.reset();
    }

    @Override
    public void next() {
        this.guiSimulator.next();
    }

}
