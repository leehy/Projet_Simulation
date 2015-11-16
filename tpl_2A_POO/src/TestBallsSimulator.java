/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;
import java.awt.Color;

/**
 *
 * @author hyun
 */
public class TestBallsSimulator {

    public static void main(String[] args)  {
        BallsSimulator bs = new BallsSimulator();
        BallsSimulator bs2 = new BallsSimulator();
        GUISimulator gui = bs.getGuiSimulator();
        EventManager manager = new EventManager();
        gui.setSimulable(bs);
        /*
        for (int i = 1; i <= 50; i += 1) {
            manager.addEvent(new BallsSimulator(bs.getBalls(),i, gui));
        }

        for (int i = 1; i <= 50; i += 3) {
            manager.addEvent(new BallsSimulator(bs2.getBalls(),i, gui));
        } 
        while (!manager.isFinished()) {           
            
           manager.next();
            Thread.sleep(1000);

        } */
        
    }




}
