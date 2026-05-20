package Proyecte_Civilizations;

import conexionbbdd.StartBattle;


public class Main {

    public static void main(String[] args) {


    	Civilization civ =     	StartBattle.loadCivi(1);
        new MainWindow(civ);  
    }
}
