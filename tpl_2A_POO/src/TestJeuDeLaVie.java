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

    public static void main(String[] args) {
        JeuDeLaVie jeu = new JeuDeLaVie();
        jeu.setProbabilité((float) 0.1);
        //si on voit tout noir c'est qu'il y a trop de cellules pour la taille
        jeu.setSizeSim(1500, 1000);
        jeu.setNombreCellule(200, 300);
        GUISimulator gui = jeu.getguiSimulator();
        gui.setSimulable(jeu);
        
        /* Cas à traiter : 
        Cas où le jeu ne peut plus avancer car la disposition des cellules ne permet pas d'avoir de nouvelles naissances
        Cas où toutes les cellules sont mortes
        Cas où toutes les cellules sont vivantes
        */
    }
}
