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
public class RapportCelluleTailleException  extends Exception {
public RapportCelluleTailleException() {
    System.out.println("Vous avez mis une fenêtre gui trop grande par rapport au nombre de cellules (si on vous laisse faire vous verrez tout noir !) ! Le rapport minimal qui vous est autorisée est (aire-de-la-fenêtre)/(nombre-total-de-cellules) = 9");
    
}

public RapportCelluleTailleException(float f) {
      System.out.println("Vous avez mis une fenêtre gui trop grande par rapport au nombre de cellules (vous voyez donc tout noir !) ! Le rapport minimal qui vous est autorisée est (aire-de-la-fenêtre)/(nombre-total-de-cellules) = 9");
  System.out.println("le rapport que vous avez mis est le suivant : " + f);
}
}





