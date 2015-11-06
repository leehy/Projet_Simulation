/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author hyun
 */
public class EventManager extends Event {
    private long currentDate;

    public EventManager(long date) {
        super(date);
    }
    
    public long getCurrentDate(){
        return this.currentDate;
    }
    
    public void addEvent(Event e){
    }
    
    public void next(){
        
    }
    
    public void restart(){
        
    }
    
    public boolean isFinished(){
        return false;
    }
    
    public void execute(){
        
    }
    
}
