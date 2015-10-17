/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.Simulable;

/**
 *
 * @author hyun
 */
public class BallsSimulator extends Balls implements Simulable {
    private Balls balls;
    
    public BallsSimulator(){
        super();
    }
    
    public BallsSimulator(Balls balls){
        super(balls);
    }
    
    @Override
    public void restart(){
        super.reInit();
        System.out.println(this.toString());
    }
    
    @Override
    public void next(){
        System.out.println(this.toString());
	}
    
}
