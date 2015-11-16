/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;

/**
 *TestImmigration permet de tester le jeu de l'immigration
 * @author amaury
 */
public class TestImmigration {
    
    // si on met pas le try et le catch dans le test ca compile pas !
    
    public static void main(String[] args) {
        Immigration jeu = new Immigration(3);
        //si on voit tout noir c'est qu'il y a trop de cellules pour la taille
       try {
            jeu.setSizeSim(750, 750);
            jeu.setNombreCellule(50, 50);
            GUISimulator gui = jeu.getGui();
            gui.setSimulable(jeu);
        } catch (RapportCelluleTailleException e) {
        }
        }
}
