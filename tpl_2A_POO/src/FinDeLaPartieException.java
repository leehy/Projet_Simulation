/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author sacha
 */
public class FinDeLaPartieException extends Exception {

    public FinDeLaPartieException() {
        System.out.println("Le jeu de la vie est fini. Recommencez");
    }
    
}
