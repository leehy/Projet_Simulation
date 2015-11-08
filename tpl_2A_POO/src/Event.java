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
//Le implements est utilise pour pouvoir comparer deux objets de type Event
public abstract class Event implements java.lang.Comparable{
    private long date;
    
    public Event(){
        this.date=0;
    }
    
    public Event(long date){
        this.date = date;
    }
    
    // Accesseur
    public long getDate(){
        return this.date;
    }
    
    //Setter
    public void setDate(long date){
        this.date=date;
    }
    
    // Abstract method defined in sub classes
    public abstract void execute();
    
	
    /**
     * @param other 
     * @return  -1 if date1>date2, 0 if == and 1 if date1<date2
     */
    @Override
    public int compareTo(Object other) { 
      long date1 = ((Event) other).getDate(); 
      long date2 = this.getDate(); 
      if (date1 > date2)  return -1; 
      else if(date1 == date2) return 0; 
      else return 1; 
   } 
}
