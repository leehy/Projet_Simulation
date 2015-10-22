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
    jeu.setProbabilité((float)0.1);
    //il y a un problème lorsqu'on setSizeSim avec 2 tailles différentes je verrai ca plus tard peut etre
    jeu.setSizeSim(500,500);
    jeu.setNombreCellule(100, 100);
    GUISimulator gui = jeu.getguiSimulator();
    gui.setSimulable(jeu) ;
}
}