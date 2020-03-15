package esi.g53298.atl.sokoban;

import esi.g53298.atl.sokoban.controller.Controller;
import java.io.FileNotFoundException;

/**
 *
 * @author israelmeiresonne
 */
public class Main {
     public static void main(String args[]) throws FileNotFoundException {
        Controller ctr = new Controller();
        ctr.startGame();
    }
}
