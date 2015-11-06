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
public abstract class Event {
    private long date;
    
    public Event(long date){
        this.date = date;
    }
    
    public long getDate(){
        return this.date;
    }
    
    public abstract void execute();
    
}
