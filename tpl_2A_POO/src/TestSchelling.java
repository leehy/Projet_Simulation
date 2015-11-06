/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;

/**
 *
 * @author amaury
 */
public class TestSchelling {
    
    // si on met pas le try et le catch dans le test ca compile pas !
    
    public static void main(String[] args) {
        Schelling jeu = new Schelling(6,50,3);
        //si on voit tout noir c'est qu'il y a trop de cellules pour la taille
       try {
            jeu.setSizeSim(750, 750);
            jeu.setNombreCellule(50, 50);
            GUISimulator gui = jeu.getguiSimulator();
            gui.setSimulable(jeu);
        } catch (RapportCelluleTailleException e) {
        }
        }
}
