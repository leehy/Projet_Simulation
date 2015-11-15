/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.util.LinkedList;
import java.awt.Point;
/**
 *
 * @author hyun
 */
public class TestBalls {
    public static void main(String[] args) {
        
        Balls balls = new Balls();

        System.out.println(balls.toString());
        System.out.println(balls.toString2());
        
        balls.translateBalls(2, 3);
        System.out.println(balls.toString());
        System.out.println(balls.toString2());
        
        balls.reInit();
        System.out.println(balls.toString());
        System.out.println(balls.toString2());
        
        Balls balls2 = new Balls(balls);
        System.out.println(balls2.toString());
    }
}
