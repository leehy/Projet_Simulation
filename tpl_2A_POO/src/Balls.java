package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/


//import java.awt.Point;


/**
 *
 * @author hyun
 */
public class Balls {
    private int x;
    private int y;
    
    public Balls(){
        this.x=0;
        this.y=0;
    }
    
    public Balls(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public Balls(Balls balls){
        this.x=(int)(balls.getX());
        this.y=(int)(balls.getY());
    }
    
    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    
    public Balls getLocation(){
       return new Balls(this.x, this.y);
    }
    
    public void move(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void setLocation(double x, double y){
        this.x = (int) Math.floor(x + 0.5);
        this.y = (int) Math.floor(y + 0.5);
        
    }
    
    public void setLocation(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void setLocation(Balls balls){
        this.x=balls.x;
        this.y=balls.y;
    }
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj != null && (obj.getClass().equals(this.getClass()))){
                if(obj instanceof Balls){
                    Balls balls =(Balls)obj;
                    result=((this.getX()== balls.getX()) && (this.getY() == balls.getY()));
                }
        }
        return result;
    }
    /*if (! (obj instanceof Point2D))
     return false;
     Point2D p = (Point2D) obj;
     return x == p.getX() && y == p.getY();
    void translate(int dx, int dy){
           this.x= this.x + dx;
           this.y= this.y + dy;
    } */
    void reInit(){
    }
    
    public String toString(){
        return "La position de la balle est (" + this.x + ", " + this.y + ")";
    }
}
