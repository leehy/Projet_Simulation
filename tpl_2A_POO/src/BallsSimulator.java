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
public class BallsSimulator implements Simulable {
    private Balls balls;
    
    public BallsSimulator(){
        this.balls= new Balls();
    }
    
    @Override
    public void restart(){
        
    }
    
    @Override
    public void next(){
        
    }
}
