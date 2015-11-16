/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author hyun
 */
public class EventManager extends Event {

    // Temps courant de la simulation
    private long currentDate;
    // Tableau d'évènements associés à des dates
    private ArrayList<Event> listEvent;
    // Temps qui correspondant à la date du  dernier evenement
    private long finDate;
    // index afin de parcourir le tableau
    private int indexList;

    public EventManager() {
        super();
        this.currentDate = 0;
        this.listEvent = new ArrayList<>();
        this.finDate = 0;
        this.indexList = 0;
    }

    public EventManager(long date) {
        super(date);
    }

    public ArrayList<Event> getListEvent() {
        return this.listEvent;
    }

    public int getIndexList() {
        return this.indexList;
    }

    public long getCurrentDate() {
        return this.currentDate;
    }

    public long getFinalDate() {
        return this.finDate;
    }

    public void addEvent(Event e) {        
        // On ajoute l'évènement au tableau
        this.listEvent.add(e);
        // On tri le tableau dans l'ordre des dates croissante
        Collections.sort(listEvent);
        // On attribue finDate à la valeur du dernier élément du tableau (donc la date de valeur la plus élevée)
        this.finDate = this.listEvent.get(this.listEvent.size() - 1).getDate();
    }

    public void next() {
        // On incrémente la date
        this.currentDate++;
        // On affiche les dates courantes et finales pour suivre
        System.out.println("-----------------------------------");
        System.out.println("current date is :" + this.getCurrentDate());
        System.out.println("Final date is :" + this.getFinalDate());

        // A une date donnée, tant qu'il y a des évènements dans la liste qui ont une date associée égale
        // à celle de currentDate, on exécute et on passe à l'évènement suivant
        while (this.listEvent.get(indexList).getDate() == this.getCurrentDate()) {
            this.execute();
            System.out.println("On est à l'élément " + this.getIndexList() + " du tableau");
            indexList++;
            
            // Si on a atteint la fin du tableau, on sort de la boucle
            if(indexList == this.listEvent.size()){
                break;
            }
        }
        
    }

    public void restart() {
        this.currentDate = 0;
    }

    // retoune vrai si la date courante = date finale
    public boolean isFinished() {
        
        return this.getCurrentDate() == this.getFinalDate();
    }

    @Override
    public void execute() {
        // On execute l'évènement dans le tableau avec la bonne date et avec le bon index
        this.listEvent.get(indexList).execute();
    }

}
