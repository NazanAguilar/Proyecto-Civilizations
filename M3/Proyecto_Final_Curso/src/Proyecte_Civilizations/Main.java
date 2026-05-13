package Proyecte_Civilizations;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // ===== EJÉRCITO CIVILIZACIÓN =====
        ArrayList<MilitaryUnit> ejercitoCivilizacion = new ArrayList<>();

        ejercitoCivilizacion.add(new Swordsam());
        ejercitoCivilizacion.add(new Swordsam());
        ejercitoCivilizacion.add(new Spearman());
        ejercitoCivilizacion.add(new Crossbow());
        ejercitoCivilizacion.add(new Cannon());
        ejercitoCivilizacion.add(new Magician(0));
        ejercitoCivilizacion.add(new Priest(0));


        // ===== EJÉRCITO ENEMIGO =====
        ArrayList<MilitaryUnit> ejercitoEnemigo = new ArrayList<>();

        ejercitoEnemigo.add(new Swordsam());
        ejercitoEnemigo.add(new Spearman());
        ejercitoEnemigo.add(new Crossbow());
        ejercitoEnemigo.add(new Cannon());
        ejercitoEnemigo.add(new Swordsam());


        // ===== CREAR BATALLA =====
        Battle batalla = new Battle(ejercitoCivilizacion, ejercitoEnemigo);

        // ===== EJECUTAR BATALLA =====
        batalla.Batalla();

        // ===== MOSTRAR RESULTADO =====
        System.out.println(batalla.getBattleDevelopment());
        System.out.println(batalla.getBattleReport(1));
    }
}
