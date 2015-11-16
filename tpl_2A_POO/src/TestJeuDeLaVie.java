/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;
/*hop hop les gars je vous laisse un petit message ici comme ca c'est sur que vous le verrez.
 */

/**
 *
 * @author sacha
 */
public class TestJeuDeLaVie {

    // si on met pas le try et le catch dans le test ca compile pas !
    public static void main(String[] args) {
        JeuDeLaVie jeu = new JeuDeLaVie();
        /*  jeu.setProbabilité((float) 0.1);
         //si on voit tout noir c'est qu'il y a trop de cellules pour la taille
         try {
         jeu.setSizeSim(500, 500);
         jeu.setNombreCellule(300, 300);
            
         } 
         catch (RapportCelluleTailleException e) {
         }
         }*/
        GUISimulator gui = jeu.getguiSimulator();
        gui.setSimulable(jeu);

    }
}
