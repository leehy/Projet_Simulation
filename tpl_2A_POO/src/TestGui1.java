/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;

/**
 *
 * @author hyun
 */
public class TestGui1 {

    public static void main(String[] args) {
        GUISimulator window = new GUISimulator(500, 500, Color.BLACK);

        for (int i = 100; i < 500; i += 100) {

            window.addGraphicalElement(
                    new Rectangle(i, 250,
                            Color.decode("#1f77B4"), Color.decode("#1f77B4"), 10));
        }
    }

}


