/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.util.*;
/**
 *
 * @author sacha
 */
public class TestBoids {
    public static void main(String[] args) {
        
    Boids b = new Boids(0,0,0,0, 50 , 10, 1000,1000);
    Boids b1 = new Boids(2,5 , 14, 2, 50, 10,1000, 1000);
    Boids b2 = new Boids(1,3, 2, 5, 50, 10, 1000, 1000);
    Boids b3 = new Boids(2,6, 17, 19, 50, 10, 1000 , 1000);
    
    b.addVoisin(b1);
    b.addVoisin(b2);
    b.addVoisin(b3);
    Stack<Boids> B = new Stack();
    B.add(b1);
    B.add(b2);
    B.add(b3);
    
  
   b.moveBoid(B);

   b.moveBoid(B);
  
}

}