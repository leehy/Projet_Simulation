/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import gui.GUISimulator;



/**
 *TestJeuDeLaVie permet de tester le jeu de la vie, le constructeur par défaut met une configuration qui va bien
 * mais vous pouvez régler plusieurs paramètres comme la probabilité qu'une cellule soit morte ou vivante, 
 * la taille de la fenêtre, le nombre de cellule en décommentant les commentaires qui suivent
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
        GUISimulator gui = jeu.getGui();
        gui.setSimulable(jeu);

    }
}
