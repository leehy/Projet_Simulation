/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.util.*; 
import java.awt.Point;
import gui.*;
import java.awt.Color;

/**
 *
 * @author amaury
 */
public class BoidSimulator implements Simulable {
    
    private Stack<Boids> hachage [][];  
    private int hauteur;
    private int longueur;
    private int NbBoids;
    private int rayon;
    private GUISimulator gui;
    
    BoidSimulator (int n, int l, int h, int r){
        NbBoids = n;
        longueur = l;
        hauteur = h;
        rayon = r;
        
        hachage = new Stack [l/r][h/r];
        
        for (int i = 0; i < l/r; i++) {
            for (int j = 0; j < h/r; j++) {
                hachage[i][j] = new Stack<Boids> (); //Initialise le tableau de jeu avec des piles de Boids
            }
        }
        
        this.gui = new GUISimulator(h/r*r+10, l/r*r+10, Color.BLACK);
        
    }
    
    public int mod ( int x , int y ){
        return (x+y)%y ;
    }
    
    private void access (Point v){ //v doit être un couple (x,y)
        v.x = mod (v.x,longueur/rayon);
        v.y = mod (v.y,hauteur/rayon);
    }
    
    private Point caseCorrespondante (Boids b){ //Calcule la position d'un boids dans le plateau
        Point p = new Point ();
        p.setLocation( b.getlocalisation().getX() / b.getRayonAction (), b.getlocalisation().getY() / b.getRayonAction ());
        return p;
    }
    
    private void addBoid (Boids b) { //ajoute un boid à la table de hachage
        Point p = caseCorrespondante (b);
        if (p.x > 0 && p.y > 0 && p.x < longueur/rayon && p.y < hauteur/rayon){
            hachage [p.x][p.y].push (b);
        }
    }
    
    private Stack<Boids> auxDelete (Boids b, Stack<Boids> s1, Stack<Boids> s2){
        try {
            Boids acc = s2.pop ();
            if (!(b.estEgal (acc))) {
                s1.push(acc);
            }
            return auxDelete (b,s1,s2);
        }
        catch (EmptyStackException e) {
            return s1;
        }
    }
    
    private void delBoid (Boids b) { //supprime un boid à la table de hachage
        Point p = caseCorrespondante (b);
        Stack<Boids> s = new Stack<Boids> ();
        hachage [p.x][p.y] = auxDelete(b,s,hachage [p.x][p.y]);
    }
    
    private void concatene (Stack<Boids> s1,Stack<Boids> s2){ //met s1 dans s2
        try{
            Boids b = s1.pop();
            s2.push(b);
            concatene (s1,s2);
            s1.push(b);
        }
        catch (EmptyStackException e){
        }
    }
    
    private Stack<Boids> voisinsPotentiels (Boids b){ //retourne la pile des boids dans les 9 cases entourant un boid
        Stack<Boids> pile = new Stack<Boids> ();
        Point caseDuBoid = caseCorrespondante (b);
        Point acc = new Point ();
        
        for (int i = -1; i<2; i++){
            for (int j = -1; j<2; j++){
                acc.setLocation(caseDuBoid.getX() + i,caseDuBoid.getY() + j);
                access (acc);
                concatene(hachage [acc.x][acc.y],pile);
            }
        }
        return pile; 
    }
    
    public GUISimulator getGui (){
        return gui;
    }
    
    //copiePile renvoie une copie de la pile mise en argument
    public Stack<Boids> copiePile(Stack<Boids> pileInitiale) {
        Stack<Boids> nouvellePile = new Stack();
        Stack<Boids> pileProvisoire = new Stack();
        int taille = pileInitiale.size();
        int index = 0;
        Boids b;
        //on copie dans la pile que l'on va renvoyer
        while(index != taille) {
            b = pileInitiale.pop();
            nouvellePile.push(b);
            pileProvisoire.push(b);
            index ++;
        }
        //on remet dans la pile initiale les élément de la pile
        index = 0;
        while(index != taille) {
            b = pileProvisoire.pop();
            pileInitiale.push(b);
            index ++;
        }
    return nouvellePile;
    }
    
    private void auxAffiche (Stack<Boids> hach){
        try {
        Boids b = hach.pop ();
        b.afficheBoid (gui);
        auxAffiche (hach);
        hach.push(b);
        }
        catch (EmptyStackException e){
        }
    }
    
    private void affiche (){
        for (int i = 0; i < longueur/rayon; i++) {
            for (int j = 0; j < hauteur/rayon; j++) {
                auxAffiche (hachage[i][j]);
            }
        }
    }
    
    private void auxAfficheNext (Stack<Boids> hach, Stack<Boids> parcours){
        try {
        Boids b = parcours.pop ();  //On prend le premier élément de la pile
        
        delBoid (b);    //On supprime cet élément de la table de hachage
        b.moveBoid(voisinsPotentiels(b));   //On calcule sa nouvelle position
        System.out.println(voisinsPotentiels(b));
        addBoid (b);    //On le met dans sa nouvelle position dans la table de hachage
        
        b.afficheBoid (gui);    //On l'affiche
        auxAfficheNext (hach,parcours); //On appelle récursivement la fonction
        }
        catch (EmptyStackException e){  //Quand on arrive au bout de la pile, on s'arrête
        }
    }
    
    private void afficheNext (){
        for (int i = 0; i < longueur/rayon; i++) {
            for (int j = 0; j < hauteur/rayon; j++) {
                auxAfficheNext (hachage[i][j], copiePile (hachage[i][j])); 
            }
        }
    }
    
    private void ajoute (int x, int y){
        Boids b = new Boids (x,y,0,0,rayon,longueur,hauteur);
        addBoid (b);
    }
    
    @Override
    public void restart (){
        gui.reset();
        int l = longueur/rayon*rayon;
        int h = hauteur/rayon*rayon;
        GraphicalElement e = new Rectangle(l/2,h/2, Color.RED, Color.BLACK, l,h);
        gui.addGraphicalElement(e);
        
        for (int i = 0; i < longueur/rayon; i++) {
            for (int j = 0; j < hauteur/rayon; j++) {
                hachage[i][j] = new Stack<Boids> (); //Initialise le tableau de jeu avec des piles de Boids
            }
        }
    
            
        //ajoute (l*9/10,h*9/10);
        ajoute (200,225);
        //ajoute (500,200);
        //ajoute (250,25);
        ajoute (200,250);
        
        
        /*for (int ici = 0; ici < NbBoids; ici++){
            System.out.println (ici);
            System.out.println (l*ici/50);
            System.out.println (h*ici/50);
            ajoute (l*ici/NbBoids,h*ici/NbBoids);
        }*/
        
        System.out.println ("fin des ajouts");
        
        affiche ();
    }
    
    @Override
    public void next (){
        gui.reset();
        int l = longueur/rayon*rayon;
        int h = hauteur/rayon*rayon;
        GraphicalElement e = new Rectangle(l/2,h/2, Color.RED, Color.BLACK, l,h);
        gui.addGraphicalElement(e);
        
        afficheNext ();
    }
    
}
