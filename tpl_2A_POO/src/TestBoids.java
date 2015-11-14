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
public class TestBoids {
    public static void main(String[] args) {
        
    Boids b = new Boids();
    Boids b1 = new Boids(2,5 , 14, 2);
    Boids b2 = new Boids(1,3, 2, 5);
    Boids b3 = new Boids(2,6, 17, 19);
    
    b.addVoisin(b1);
    b.addVoisin(b2);
    b.addVoisin(b3);
    
    
   b.toString();
   b.moveBoid();
   b.toString();
}

}