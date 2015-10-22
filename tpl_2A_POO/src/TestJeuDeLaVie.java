/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;

/**
 *
 * @author sacha
 */
public class TestJeuDeLaVie {
    public static void main ( String [] args ) {
    JeuDeLaVie jeu = new JeuDeLaVie();
    jeu.setProbabilité((float)0.15);
    GUISimulator gui = jeu.getguiSimulator();
    gui.setSimulable(jeu) ;
}
}