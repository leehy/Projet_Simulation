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
public class TestEventManager {

    public static void main(String[] args) throws InterruptedException {
        // On cr é e un simulateur
        EventManager manager = new EventManager();
        // On poste un é v é nement [ PING ] tous les deux pas de temps

        for (int i = 2; i <= 10; i += 2) {
            manager.addEvent(new MessageEvent(i, " [ PING ] "));

        }
// On poste un é v é nement [ PONG ] tous les trois pas de temps
        for (int i = 3; i <= 9; i += 3) {
            manager.addEvent(new MessageEvent(i, " [ PONG ] "));
        }

        while (!manager.isFinished()) {

            manager.next();
            Thread.sleep(1000);

        }

    }
}
