package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/


import java.awt.Point;
import java.util.ArrayList;
/**
 *
 * @author hyun
 */
public class Balls extends Point{
    private ArrayList<Point> ListPoint ;
    private ArrayList<Point> LInitial ;
    //Iterator ListIterator = ListPoint.iterator();
    
    public Balls(){
        super();
        this.ListPoint= new ArrayList<>();
        this.LInitial= new ArrayList<>();
        this.addBall(new Point(300,300));
        this.addBall(new Point(20,50));
        this.addBall(new Point(80,120));
        this.addBall(new Point(140,70));
        
    }
    
    public Balls(Balls balls){
        this.ListPoint= balls.getListPoint();
    }
    
    public ArrayList<Point> getListPoint(){
        return this.ListPoint;
    }
    
    public ArrayList<Point> getListPointInitial(){
        return this.LInitial;
    }
    
    public void addBall(Point point){
        this.ListPoint.add(point);
        Point tmp = new Point(point);
        this.LInitial.add(tmp);
    }
    
    public void removeBall(Point point){
        this.ListPoint.remove(point);
    }
    
    void translateBalls (int dx, int dy){
        int index=0;
         while(!(index == this.getSizeList())){
             this.ListPoint.get(index).translate(dx,dy);
             index ++;
         }
    }
     
    public int getSizeList(){
        return ListPoint.size();
    }
    
    public int getSizeListInitial(){
        return LInitial.size();
    }
    
    @Override
    public String toString(){
        int index=0;
        String buff="";
        while(!(index == this.getSizeList())){
        buff =buff + "La position de la balle " + index + " est (" + ListPoint.get(index).getX() + ", " + ListPoint.get(index).getY() + ") \n";
        index ++;
        }
        return buff;
    }
    
    public String toString2(){
        int index=0;
        String buff="";
        while(!(index == this.getSizeList())){
        buff =buff + "La position de la balle " + index + " est (" + LInitial.get(index).getX() + ", " + LInitial.get(index).getY() + ") \n";
        index ++;
        }
        return buff;
    }
    
    void reInit(){
        int index =0;
        while(!(index == this.getSizeList())){
            this.ListPoint.get(index).setLocation(this.LInitial.get(index));
            //this.ListPoint.get(index).setLocation(this.ListPointInitial.get(index).getX(), this.ListPointInitial.get(index).getY());
            index++;   
        }
    }
    
    /*
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
   
    
    public String toString(){
        return "La position de la balle est (" + this.x + ", " + this.y + ")";
    } */
    
}
