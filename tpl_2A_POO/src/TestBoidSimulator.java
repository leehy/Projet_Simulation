
package src;
import gui.GUISimulator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amaury
 */
public class TestBoidSimulator {
    public static void main(String[] args) {
        
        BoidSimulator b = new BoidSimulator(10,1000,600,150,20,1000);
        //si on voit tout noir c'est qu'il y a trop de cellules pour la taille
        GUISimulator gui = b.getGui();
        gui.setSimulable(b);
       
        }
}
