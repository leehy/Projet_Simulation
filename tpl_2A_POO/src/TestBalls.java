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
        Point point1 = new Point(5,3);
        Point point2 = new Point(2,4);
        Point point3 = new Point(5,6);
        Point point4 = new Point(8,4);
        Point point5 = new Point(3,7);
        
        Balls balls = new Balls();
        balls.addBall(point1);
        balls.addBall(point2);
        balls.addBall(point3);
        balls.addBall(point4);
        balls.addBall(point5);
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
