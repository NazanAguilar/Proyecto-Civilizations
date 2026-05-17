package Proyecte_Civilizations;

public class Main {

    public static void main(String[] args) {

        Civilization civ = new Civilization(
                0, 0,          // tecnologías
                100000, 100000,  // wood, iron
                50000,         // food
                20000,         // mana
                0, 0, 0, 0, 0, // edificios
                0              // battles
        );

        new MainWindow(civ);  
    }
}
