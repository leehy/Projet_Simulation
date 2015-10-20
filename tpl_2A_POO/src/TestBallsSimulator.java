/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import gui.GUISimulator ;
import java.awt.Color ;

/**
 *
 * @author hyun
 */
public class TestBallsSimulator {
    public static void main ( String [] args ) {
    BallsSimulator bs = new BallsSimulator();
    GUISimulator gui = new GUISimulator (500 , 500 , Color . BLACK ) ;
    gui.setSimulable(bs) ;
    //System.out.println(bs.toString());
}

}
