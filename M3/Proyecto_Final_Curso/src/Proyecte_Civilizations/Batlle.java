package Proyecte_Civilizations;

import java.util.ArrayList;

import conexionbbdd.EndBattle;
import conexionbbdd.StartBattle;

class Battle implements Variables{

    private ArrayList<MilitaryUnit> civilizationArmy;
    private ArrayList<MilitaryUnit> enemyArmy;
    private ArrayList<MilitaryUnit>[][] armies;
    private String battleDevelopment;
    private int[][] initialCostFleet;
    private int initialNumberUnitsCivilization, initialNumberUnitsEnemy;
    private int[] wasteWoodIron;
    private int enemyDrops, civilizationDrops;
    private int[][] resourceLooses;
    private int[][] initialArmies;
    private int[] actualNumberUnitsCivilization, actualNumberUnitsEnemy;
    public Battle(ArrayList<MilitaryUnit> civilizationArmy, ArrayList<MilitaryUnit> enemyArmy) {

        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;

        battleDevelopment = "";
        enemyDrops = 0;
        civilizationDrops = 0;

        // 1) CREAR ARMIES
        armies = new ArrayList[2][9];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 9; j++) {
                armies[i][j] = new ArrayList<MilitaryUnit>();
            }
        }

        // 2) CLASIFICAR UNIDADES CIVILIZACIÓN
        for(int i = 0; i < civilizationArmy.size(); i++) {

            if(civilizationArmy.get(i) instanceof Swordsam) {
                armies[0][0].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof Spearman) {
                armies[0][1].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof Crossbow) {
                armies[0][2].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof Cannon) {
                armies[0][3].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof ArrowTower) {
                armies[0][4].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof Catapult) {
                armies[0][5].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof RocketLauncherTower) {
                armies[0][6].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof Magician) {
                armies[0][7].add(civilizationArmy.get(i));
            }
            if(civilizationArmy.get(i) instanceof Priest) {
                armies[0][8].add(civilizationArmy.get(i));
            }
        }

        // 3) CLASIFICAR UNIDADES ENEMIGO
        for(int i = 0; i < enemyArmy.size(); i++) {

            if(enemyArmy.get(i) instanceof Swordsam) {
                armies[1][0].add(enemyArmy.get(i));
            }
            if(enemyArmy.get(i) instanceof Spearman) {
                armies[1][1].add(enemyArmy.get(i));
            }
            if(enemyArmy.get(i) instanceof Crossbow) {
                armies[1][2].add(enemyArmy.get(i));
            }
            if(enemyArmy.get(i) instanceof Cannon) {
                armies[1][3].add(enemyArmy.get(i));
            }
        }

        // 4) UNIDADES INICIALES TOTALES
        initialNumberUnitsCivilization = civilizationArmy.size();
        initialNumberUnitsEnemy = enemyArmy.size();

        // 5) initialArmies
        initialArmies = new int[2][9];
        initialArmies[0][0] = armies[0][0].size();
        initialArmies[0][1] = armies[0][1].size();
        initialArmies[0][2] = armies[0][2].size();
        initialArmies[0][3] = armies[0][3].size();
        initialArmies[0][4] = armies[0][4].size();
        initialArmies[0][5] = armies[0][5].size();
        initialArmies[0][6] = armies[0][6].size();
        initialArmies[0][7] = armies[0][7].size();
        initialArmies[0][8] = armies[0][8].size();

        initialArmies[1][0] = armies[1][0].size();
        initialArmies[1][1] = armies[1][1].size();
        initialArmies[1][2] = armies[1][2].size();
        initialArmies[1][3] = armies[1][3].size();

        // 6) initialCostFleet
        initialCostFleet = new int[2][3];

        for(int i = 0; i < civilizationArmy.size(); i++) {
            if(civilizationArmy.get(i) instanceof Swordsam) {
                initialCostFleet[0][0] += FOOD_COST_SWORDSMAN;
                initialCostFleet[0][1] += WOOD_COST_SWORDSMAN;
                initialCostFleet[0][2] += IRON_COST_SWORDSMAN;
            }
            if(civilizationArmy.get(i) instanceof Spearman) {
                initialCostFleet[0][0] += FOOD_COST_SPEARMAN;
                initialCostFleet[0][1] += WOOD_COST_SPEARMAN;
                initialCostFleet[0][2] += IRON_COST_SPEARMAN;
            }
            if(civilizationArmy.get(i) instanceof Crossbow) {
                initialCostFleet[0][0] += FOOD_COST_CROSSBOW;
                initialCostFleet[0][1] += WOOD_COST_CROSSBOW;
                initialCostFleet[0][2] += IRON_COST_CROSSBOW;
            }
            if(civilizationArmy.get(i) instanceof Cannon) {
                initialCostFleet[0][0] += FOOD_COST_CANNON;
                initialCostFleet[0][1] += WOOD_COST_CANNON;
                initialCostFleet[0][2] += IRON_COST_CANNON;
            }
            if(civilizationArmy.get(i) instanceof ArrowTower) {
                initialCostFleet[0][0] += FOOD_COST_ARROWTOWER;
                initialCostFleet[0][1] += WOOD_COST_ARROWTOWER;
                initialCostFleet[0][2] += IRON_COST_ARROWTOWER;
            }
            if(civilizationArmy.get(i) instanceof Catapult) {
                initialCostFleet[0][0] += FOOD_COST_CATAPULT;
                initialCostFleet[0][1] += WOOD_COST_CATAPULT;
                initialCostFleet[0][2] += IRON_COST_CATAPULT;
            }
            if(civilizationArmy.get(i) instanceof RocketLauncherTower) {
                initialCostFleet[0][0] += FOOD_COST_ROCKETLAUNCHERTOWER;
                initialCostFleet[0][1] += WOOD_COST_ROCKETLAUNCHERTOWER;
                initialCostFleet[0][2] += IRON_COST_ROCKETLAUNCHERTOWER;
            }
            if(civilizationArmy.get(i) instanceof Magician) {
                initialCostFleet[0][0] += FOOD_COST_MAGICIAN;
                initialCostFleet[0][1] += WOOD_COST_MAGICIAN;
                initialCostFleet[0][2] += IRON_COST_MAGICIAN;
            }
            if(civilizationArmy.get(i) instanceof Priest) {
                initialCostFleet[0][0] += FOOD_COST_PRIEST;
                initialCostFleet[0][1] += WOOD_COST_PRIEST;
                initialCostFleet[0][2] += IRON_COST_PRIEST;
            }
        }

        for(int i = 0; i < enemyArmy.size(); i++) {
            if(enemyArmy.get(i) instanceof Swordsam) {
                initialCostFleet[1][0] += FOOD_COST_SWORDSMAN;
                initialCostFleet[1][1] += WOOD_COST_SWORDSMAN;
                initialCostFleet[1][2] += IRON_COST_SWORDSMAN;
            }
            if(enemyArmy.get(i) instanceof Spearman) {
                initialCostFleet[1][0] += FOOD_COST_SPEARMAN;
                initialCostFleet[1][1] += WOOD_COST_SPEARMAN;
                initialCostFleet[1][2] += IRON_COST_SPEARMAN;
            }
            if(enemyArmy.get(i) instanceof Crossbow) {
                initialCostFleet[1][0] += FOOD_COST_CROSSBOW;
                initialCostFleet[1][1] += WOOD_COST_CROSSBOW;
                initialCostFleet[1][2] += IRON_COST_CROSSBOW;
            }
            if(enemyArmy.get(i) instanceof Cannon) {
                initialCostFleet[1][0] += FOOD_COST_CANNON;
                initialCostFleet[1][1] += WOOD_COST_CANNON;
                initialCostFleet[1][2] += IRON_COST_CANNON;
            }
        }

        // 7) actualNumberUnits
        actualNumberUnitsCivilization = new int[9];
        actualNumberUnitsEnemy = new int[9];

        for(int j = 0; j < 9; j++) {
            actualNumberUnitsCivilization[j] = armies[0][j].size();
            actualNumberUnitsEnemy[j] = armies[1][j].size();
        }

        // 8) resourceLooses 
        resourceLooses = new int[2][4];

        // 9) wasteWoodIron 
        wasteWoodIron = new int[2];
    }

    
    //Metodos creados 

    public String getBattleDevelopment() {
        return battleDevelopment; // Datos de Batalla
    }
    
    public String getBattleReport(int battles) {

        String r = "===== REPORTE DE BATALLA " + battles + " =====\n";

        r += "Unidades eliminadas civilización: " + civilizationDrops + "\n";
        r += "Unidades eliminadas enemigo: " + enemyDrops + "\n";

        r += "\nPérdidas civilización (comida, madera, hierro, ponderado):\n";
        r += resourceLooses[0][0] + " | " + resourceLooses[0][1] + " | " +
             resourceLooses[0][2] + " | " + resourceLooses[0][3] + "\n";

        r += "\nPérdidas enemigo (comida, madera, hierro, ponderado):\n";
        r += resourceLooses[1][0] + " | " + resourceLooses[1][1] + " | " +
             resourceLooses[1][2] + " | " + resourceLooses[1][3] + "\n";

        r += "\nResiduos generados (madera, hierro):\n";
        r += wasteWoodIron[0] + " | " + wasteWoodIron[1] + "\n";

        return r;
    }

    
    public void initInitialArmies() {
    	
    	
    }
    
	public void updateResourcesLooses() {

	    resourceLooses[0][0] = 0; 
	    resourceLooses[0][1] = 0; 
	    resourceLooses[0][2] = 0; 
	    resourceLooses[0][3] = 0;

	    resourceLooses[1][0] = 0; 
	    resourceLooses[1][1] = 0;
	    resourceLooses[1][2] = 0;
	    resourceLooses[1][3] = 0; 


	    //   PÉRDIDAS CIVILIZACIÓN

	    for(int i = 0; i < 9; i++) {

	        int perdidas = initialArmies[0][i] - actualNumberUnitsCivilization[i];

	        if(perdidas > 0) {

	            if(i == 0) { 
	                resourceLooses[0][0] += perdidas * FOOD_COST_SWORDSMAN;
	                resourceLooses[0][1] += perdidas * WOOD_COST_SWORDSMAN;
	                resourceLooses[0][2] += perdidas * IRON_COST_SWORDSMAN;
	            }
	            else if(i == 1) {
	                resourceLooses[0][0] += perdidas * FOOD_COST_SPEARMAN;
	                resourceLooses[0][1] += perdidas * WOOD_COST_SPEARMAN;
	                resourceLooses[0][2] += perdidas * IRON_COST_SPEARMAN;
	            }
	            else if(i == 2) { 
	                resourceLooses[0][0] += perdidas * FOOD_COST_CROSSBOW;
	                resourceLooses[0][1] += perdidas * WOOD_COST_CROSSBOW;
	                resourceLooses[0][2] += perdidas * IRON_COST_CROSSBOW;
	            }
	            else if(i == 3) {
	                resourceLooses[0][0] += perdidas * FOOD_COST_CANNON;
	                resourceLooses[0][1] += perdidas * WOOD_COST_CANNON;
	                resourceLooses[0][2] += perdidas * IRON_COST_CANNON;
	            }
	            else if(i == 4) { 
	                resourceLooses[0][0] += perdidas * FOOD_COST_ARROWTOWER;
	                resourceLooses[0][1] += perdidas * WOOD_COST_ARROWTOWER;
	                resourceLooses[0][2] += perdidas * IRON_COST_ARROWTOWER;
	            }
	            else if(i == 5) {
	                resourceLooses[0][0] += perdidas * FOOD_COST_CATAPULT;
	                resourceLooses[0][1] += perdidas * WOOD_COST_CATAPULT;
	                resourceLooses[0][2] += perdidas * IRON_COST_CATAPULT;
	            }
	            else if(i == 6) { 
	                resourceLooses[0][0] += perdidas * FOOD_COST_ROCKETLAUNCHERTOWER;
	                resourceLooses[0][1] += perdidas * WOOD_COST_ROCKETLAUNCHERTOWER;
	                resourceLooses[0][2] += perdidas * IRON_COST_ROCKETLAUNCHERTOWER;
	            }
	            else if(i == 7) {
	                resourceLooses[0][0] += perdidas * FOOD_COST_MAGICIAN;
	                resourceLooses[0][1] += perdidas * WOOD_COST_MAGICIAN;
	                resourceLooses[0][2] += perdidas * IRON_COST_MAGICIAN;
	            }
	            else if(i == 8) {
	                resourceLooses[0][0] += perdidas * FOOD_COST_PRIEST;
	                resourceLooses[0][1] += perdidas * WOOD_COST_PRIEST;
	                resourceLooses[0][2] += perdidas * IRON_COST_PRIEST;
	            }
	        }
	    }
	    //osea 1 de hierro es 10 de comida y 1 de madera es son 2 de comida
	    resourceLooses[0][3] =resourceLooses[0][2]*10 + resourceLooses[0][1] * 2 + resourceLooses[0][0];
	    //Enemigos
	    for(int i = 0; i < 4; i++) {

	        int perdidas = initialArmies[1][i] - actualNumberUnitsEnemy[i];

	        if(perdidas > 0) {

	            if(i == 0) { 
	                resourceLooses[1][0] += perdidas * FOOD_COST_SWORDSMAN;
	                resourceLooses[1][1] += perdidas * WOOD_COST_SWORDSMAN;
	                resourceLooses[1][2] += perdidas * IRON_COST_SWORDSMAN;
	            }
	            else if(i == 1) { 
	                resourceLooses[1][0] += perdidas * FOOD_COST_SPEARMAN;
	                resourceLooses[1][1] += perdidas * WOOD_COST_SPEARMAN;
	                resourceLooses[1][2] += perdidas * IRON_COST_SPEARMAN;
	            }
	            else if(i == 2) { 
	                resourceLooses[1][0] += perdidas * FOOD_COST_CROSSBOW;
	                resourceLooses[1][1] += perdidas * WOOD_COST_CROSSBOW;
	                resourceLooses[1][2] += perdidas * IRON_COST_CROSSBOW;
	            }
	            else if(i == 3) { 
	                resourceLooses[1][0] += perdidas * FOOD_COST_CANNON;
	                resourceLooses[1][1] += perdidas * WOOD_COST_CANNON;
	                resourceLooses[1][2] += perdidas * IRON_COST_CANNON;
	            }
	        }
	    }


	    resourceLooses[1][3] =resourceLooses[1][2] + resourceLooses[1][1] * 5 +resourceLooses[1][0] * 10;

	}
    
    public int[][] fleetResourceCost() {
    	int[][] lista ;
    	lista = new int[2][3];
    	lista[0][0] = initialCostFleet[0][0];
    	lista[0][1] = initialCostFleet[0][1];
    	lista[0][2] = initialCostFleet[0][2];
    	lista[1][0] = initialCostFleet[1][0];
    	lista[1][1] = initialCostFleet[1][1];
    	lista[1][2] = initialCostFleet[1][2];
    	return lista;
    	
    }
    public int[] initialFleetNumber() {
    	int[] lista;
    	lista = new int[2];
    	lista[0] = initialNumberUnitsCivilization;
    	lista[1] = initialNumberUnitsEnemy;
		return lista;
    }
    
    public int[] remainderPercentageFleet() {
    	int[]lista;
    	lista = new int[2];

        for(int j = 0; j < 9; j++) {
            lista[0] += actualNumberUnitsCivilization[j];
            lista[1] += actualNumberUnitsEnemy[j];
        }
        lista[0] = (lista[0] * 100) / initialNumberUnitsCivilization;
        lista[1] = (lista[1] * 100) / initialNumberUnitsEnemy;
    	return lista;
    	
    }
    public int getGroupDefenderCivilization() {

        int sumaTotal = 0;

        for(int i = 0; i < 9; i++) {
            sumaTotal += actualNumberUnitsCivilization[i];
        }

        if (sumaTotal == 0) return 0;

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < 9; i++) {
            acumulado += actualNumberUnitsCivilization[i];
            if(acumulado >= numAleatorio) {
                if (actualNumberUnitsCivilization[i] > 0) {
                	return i;
                } 
            }
        }

        return 0;
    }
    
    public int getGroupDefenderEnemy() {

        int sumaTotal = 0;

        for(int i = 0; i < 9; i++) {
            sumaTotal += actualNumberUnitsEnemy[i];
        }

        if (sumaTotal == 0) return 0;

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < 9; i++) {
            acumulado += actualNumberUnitsEnemy[i];
            if(acumulado >= numAleatorio) {
                if (actualNumberUnitsEnemy[i] > 0) {
                	return i;
                } 
            }
        }

        return 0;
    }
    
    public int getCivilizationGroupAttacker() {

        int sumaTotal = 0;

        for(int i = 0; i < CHANCE_ATTACK_CIVILIZATION_UNITS.length; i++) {
            sumaTotal += CHANCE_ATTACK_CIVILIZATION_UNITS[i];
        }

        if (sumaTotal == 0) {
            return 0;
        }

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < CHANCE_ATTACK_CIVILIZATION_UNITS.length; i++) {
            acumulado += CHANCE_ATTACK_CIVILIZATION_UNITS[i];
            if(acumulado >= numAleatorio) {
            	if(actualNumberUnitsCivilization[i] != 0) {
                    return i;

            	}
            }
        }

        return 0;
    }
    public int getEnemyGroupAttacker() {

        int sumaTotal = 0;

        for(int i = 0; i < CHANCE_ATTACK_ENEMY_UNITS.length; i++) {
            sumaTotal += CHANCE_ATTACK_ENEMY_UNITS[i];
        }

        if (sumaTotal == 0) {
            return 0;
        }

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < CHANCE_ATTACK_ENEMY_UNITS.length; i++) {
            acumulado += CHANCE_ATTACK_ENEMY_UNITS[i];
            if(acumulado >= numAleatorio) {
            	if(actualNumberUnitsEnemy[i] != 0) {
                    return i;

            	}
            }
        }

        return 0;
    }
    private int getChanceAttackAgain(MilitaryUnit u) {

        if (u instanceof Swordsam) {
            return CHANCE_ATTACK_AGAIN_SWORDSMAN;
        }

        if (u instanceof Spearman) {
            return CHANCE_ATTACK_AGAIN_SPEARMAN;
        }

        if (u instanceof Crossbow) {
            return CHANCE_ATTACK_AGAIN_CROSSBOW;
        }

        if (u instanceof Cannon) {
            return CHANCE_ATTACK_AGAIN_CANNON;
        }

        if (u instanceof ArrowTower) {
            return CHANCE_ATTACK_AGAIN_ARROWTOWER;
        }

        if (u instanceof Catapult) {
            return CHANCE_ATTACK_AGAIN_CATAPULT;
        }

        if (u instanceof RocketLauncherTower) {
            return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
        }

        if (u instanceof Magician) {
            return CHANCE_ATTACK_AGAIN_MAGICIAN;
        }

        if (u instanceof Priest) {
            return CHANCE_ATTACK_AGAIN_PRIEST;
        }

        return 0;
    }


    
    public void resetArmyArmor() {
    	for (int i = 0; i < 9 ;i++ ) {
    		for(int j = 0;j<armies[0][i].size();j++)
    		armies[0][i].get(j).resetArmor();
		}
    	for (int i = 0; i < 4 ;i++ ) {
    		for(int j = 0;j<armies[1][i].size();j++)
    		armies[1][i].get(j).resetArmor();
		}
    	
    }
    private void addWaste(MilitaryUnit u) {

        if (Math.random() * 100 < u.getChanceGeneratinWaste()) {

            wasteWoodIron[0] += u.getWoodCost();   
            wasteWoodIron[1] += u.getIronCost();   

            battleDevelopment += "La unidad dejó residuos: +" 
                                 + u.getWoodCost() + " madera, +" 
                                 + u.getIronCost() + " hierro.\n";
        }
    }
    
    private int getNonEmptyGroup(int armyIndex, int initialGroup) {

        if (armies[armyIndex][initialGroup].size() != 0) {
            return initialGroup;
        }

        for (int i = 0; i < armies[armyIndex].length; i++) {
            if (armies[armyIndex][i].size() !=0) {
                return i;
            }
        }

        return initialGroup;
    }
    
    public int[] Batalla() {

        int comienzo = (int)(Math.random() * 2) + 1;
        int ronda = 1;
        int numBat = StartBattle.loadNumBattle();
        
        //Creamos registros en BBDD para BattleStats para a continuación poder crear en la tabla de Logs sin entorpecer por las relaciones entre tablas
        EndBattle.newBattle(1, numBat+1, 0, 0, 2);
        EndBattle.newBattle(2, numBat+1, 0, 0, 1);

        battleDevelopment += "===== INICIO DE LA BATALLA =====\n";

        while (true) {

            battleDevelopment += "\n========== RONDA " + ronda + " ==========\n";

            int[] porcentajes = remainderPercentageFleet();

            if (porcentajes[0] <= 20 || porcentajes[1] <= 20) {
                battleDevelopment += "La batalla termina: un ejército tiene menos del 20%\n";
                break;
            }

            // CIVILIZACIÓN ATACA 
            if (comienzo == 1) {

                battleDevelopment += "--- Turno de la CIVILIZACIÓN ---\n";

                int grupoAtacante = getCivilizationGroupAttacker();
                int grupoDefensor = getGroupDefenderEnemy();

                grupoAtacante = getNonEmptyGroup(0, grupoAtacante);
                grupoDefensor = getNonEmptyGroup(1, grupoDefensor);

                int idxAtacante = (int)(Math.random() * armies[0][grupoAtacante].size());
                MilitaryUnit atacante = armies[0][grupoAtacante].get(idxAtacante);

                int idxDefensor = (int)(Math.random() * armies[1][grupoDefensor].size());
                MilitaryUnit defensor = armies[1][grupoDefensor].get(idxDefensor);

                int dano = atacante.attack();
                defensor.takeDamage(dano);

                battleDevelopment += "Civilización: " + atacante.getClass().getSimpleName() +
                                     " ataca a " + defensor.getClass().getSimpleName() +
                                     " por " + dano + " daño.\n";
                String logAt = "Civilización: " + atacante.getClass().getSimpleName() + " ataca a " + defensor.getClass().getSimpleName() + " por " + dano + " daño.\n";
                EndBattle.insertBattleLogs(1,numBat+1,logAt,2);
                if (defensor.getActualArmor() <= 0) {
                    battleDevelopment += "nidad enemiga eliminada.\n";
                    String logAt2 = "Unidad enemiga eliminada.";
                    EndBattle.insertBattleLogs(1,numBat+1,logAt2,2);
                    
                    enemyDrops++;
                    addWaste(defensor);
                    armies[1][grupoDefensor].remove(defensor);
                    actualNumberUnitsEnemy[grupoDefensor]--;
                }

                int chance = getChanceAttackAgain(atacante);

                if (Math.random() * 100 < chance) {

                    battleDevelopment += "ATAQUE EXTRA :\n";

                    grupoDefensor = getGroupDefenderEnemy();
                    grupoDefensor = getNonEmptyGroup(1, grupoDefensor);

                    int idxNuevo = (int)(Math.random() * armies[1][grupoDefensor].size());
                    MilitaryUnit nuevoDefensor = armies[1][grupoDefensor].get(idxNuevo);

                    int danoExtra = atacante.attack();
                    nuevoDefensor.takeDamage(danoExtra);

                    battleDevelopment += "Civilizacion: " + atacante.getClass().getSimpleName() +
                                         " golpea a " + nuevoDefensor.getClass().getSimpleName() +
                                         " por " + danoExtra + " daño.\n";
                    String logAt3 = "Civilizacion: " + atacante.getClass().getSimpleName() + " golpea a " + nuevoDefensor.getClass().getSimpleName() + " por " + danoExtra + " daño.\n";
                    EndBattle.insertBattleLogs(1,numBat+1,logAt3,2);
                    
                    if (nuevoDefensor.getActualArmor() <= 0) {
                        enemyDrops++;
                        addWaste(nuevoDefensor);
                        armies[1][grupoDefensor].remove(nuevoDefensor);
                        actualNumberUnitsEnemy[grupoDefensor]--;
                        battleDevelopment += "Unidad enemiga eliminada en ataque extra.\n";
                        String logAt4 = "Unidad enemiga eliminada en ataque extra.";
                        EndBattle.insertBattleLogs(1,numBat+1,logAt4,2);
                    }
                }

                comienzo++;
            //  ENEMIGO ATACA
            } else {

                battleDevelopment += "--- Turno del ENEMIGO ---\n";

                int grupoAtacante = getEnemyGroupAttacker();
                int grupoDefensor = getGroupDefenderCivilization();

                grupoAtacante = getNonEmptyGroup(1, grupoAtacante);
                grupoDefensor = getNonEmptyGroup(0, grupoDefensor);

                int idxAtacante = (int)(Math.random() * armies[1][grupoAtacante].size());
                MilitaryUnit atacante = armies[1][grupoAtacante].get(idxAtacante);

                int idxDefensor = (int)(Math.random() * armies[0][grupoDefensor].size());
                MilitaryUnit defensor = armies[0][grupoDefensor].get(idxDefensor);

                int dano = atacante.attack();
                defensor.takeDamage(dano);

                battleDevelopment += "Enemigo: " + atacante.getClass().getSimpleName() +
                                     " ataca a " + defensor.getClass().getSimpleName() +
                                     " por " + dano + " daño.\n";
                String logEn = "Enemigo: " + atacante.getClass().getSimpleName() + " ataca a " + defensor.getClass().getSimpleName() + " por " + dano + " daño.\n";
                EndBattle.insertBattleLogs(2,numBat+1,logEn,1);

                if (defensor.getActualArmor() <= 0) {
                    battleDevelopment += "Unidad de la civilización eliminada.\n";
                    String logEn2 = "Unidad de la civilización eliminada.";
                    EndBattle.insertBattleLogs(2,numBat+1,logEn2,1);
                    civilizationDrops++;
                    addWaste(defensor);
                    armies[0][grupoDefensor].remove(defensor);
                    actualNumberUnitsCivilization[grupoDefensor]--;
                }

                int chance = getChanceAttackAgain(atacante);

                if (Math.random() * 100 < chance) {

                    battleDevelopment += "ATAQUE EXTRA ENEMIGO :\n";

                    grupoDefensor = getGroupDefenderCivilization();
                    grupoDefensor = getNonEmptyGroup(0, grupoDefensor);

                    int idxNuevo = (int)(Math.random() * armies[0][grupoDefensor].size());
                    MilitaryUnit nuevoDefensor = armies[0][grupoDefensor].get(idxNuevo);

                    int danoExtra = atacante.attack();
                    nuevoDefensor.takeDamage(danoExtra);

                    battleDevelopment += "Enemigo: " + atacante.getClass().getSimpleName() +
                                         " golpea a " + nuevoDefensor.getClass().getSimpleName() +
                                         " por " + danoExtra + " daño.\n";
                    String logEn3 = "Enemigo: " + atacante.getClass().getSimpleName() + " golpea a " + nuevoDefensor.getClass().getSimpleName() + " por " + danoExtra + " daño.\n";
                    EndBattle.insertBattleLogs(2,numBat+1,logEn3,1);
                    
                    if (nuevoDefensor.getActualArmor() <= 0) {
                        civilizationDrops++;
                        addWaste(nuevoDefensor);
                        armies[0][grupoDefensor].remove(nuevoDefensor);
                        actualNumberUnitsCivilization[grupoDefensor]--;
                        battleDevelopment += "Unidad de la civilización eliminada en ataque extra.\n";
                        String logEn4 = "Unidad de la civilización eliminada en ataque extra.";
                        EndBattle.insertBattleLogs(2,numBat+1,logEn4,1);
                    }
                }

                comienzo--;
            }

            ronda++; 
        }
        resetArmyArmor();

        // SUBIR EXPERIENCIA
        for (int grupo = 0; grupo < 9; grupo++) {
            for (MilitaryUnit u : armies[0][grupo]) {
            	if (grupo != 8) {
            		u.setExperience(u.getExperience() + 1);
            	}
            }
            for (MilitaryUnit u : armies[1][grupo]) {
            	if (grupo != 8) {
            		 u.setExperience(u.getExperience() + 1);
            	}
            } 
        }

        // BONUS DE EXPERIENCIA
        for (int grupo = 0; grupo < 9; grupo++) {

            for (MilitaryUnit u : armies[0][grupo]) {
                int exp = u.getExperience();
                if (exp > 0 && grupo != 8) {
                    if (u instanceof AttackUnit a) {
                        a.setArmor(a.getArmor() + (PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT * exp));
                        a.setBaseDamage(a.getBaseDamage() + (PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * exp));
                    }
                    else if (u instanceof DefenseUnit d) {
                        d.setArmor(d.getArmor() + (PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT * exp));
                        d.setBaseDamage(d.getBaseDamage() + (PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * exp));
                    }
                    else if (u instanceof SpecialUnit s) {
                        s.setArmor(s.getArmor() + (PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT * exp));
                        s.setBaseDamage(s.getBaseDamage() + (PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * exp));
                    }
                }
            }

            for (MilitaryUnit u : armies[1][grupo]) {
                int exp = u.getExperience();
                if (exp > 0 && grupo != 8) {
                    if (u instanceof AttackUnit a) {
                        a.setArmor(a.getArmor() + (PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT * exp));
                        a.setBaseDamage(a.getBaseDamage() + (PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * exp));
                    }
                    else if (u instanceof DefenseUnit d) {
                        d.setArmor(d.getArmor() + (PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT * exp));
                        d.setBaseDamage(d.getBaseDamage() + (PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * exp));
                    }
                    else if (u instanceof SpecialUnit s) {
                        s.setArmor(s.getArmor() + (PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT * exp));
                        s.setBaseDamage(s.getBaseDamage() + (PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT * exp));
                    }
                }
            }
        }

        boolean haySacerdotesFinal = armies[0][8].size() > 0;

        if (haySacerdotesFinal) {

            for (int grupo = 0; grupo < 9; grupo++) {

                for (MilitaryUnit u : armies[0][grupo]) {

                    if (u instanceof AttackUnit a) {
                        a.setArmor(a.getArmor() + (a.getArmor() * PLUS_ARMOR_UNIT_SANCTIFIED) / 100);
                        a.setBaseDamage(a.getBaseDamage() + (a.getBaseDamage() * PLUS_ATTACK_UNIT_SANCTIFIED) / 100);
                    }
                    else if (u instanceof DefenseUnit d) {
                        d.setArmor(d.getArmor() + (d.getArmor() * PLUS_ARMOR_UNIT_SANCTIFIED) / 100);
                        d.setBaseDamage(d.getBaseDamage() + (d.getBaseDamage() * PLUS_ATTACK_UNIT_SANCTIFIED) / 100);
                    }
                }
            }

            battleDevelopment += "Las unidades sobrevivientes han sido santificadas (+7% ataque y armadura).\n";
        }

        battleDevelopment += "Bonus de experiencia aplicado a todas las unidades vivas.\n";

        updateResourcesLooses();

        int perdidasCivilizacion = resourceLooses[0][3];
        int perdidasEnemigo = resourceLooses[1][3];

        if (perdidasCivilizacion < perdidasEnemigo) {
            battleDevelopment += "GANADOR: Civilización (menos pérdidas de recursos)\n";
            battleDevelopment += "La civilización obtiene los residuos: "
                                 + wasteWoodIron[0] + " madera, "
                                 + wasteWoodIron[1] + " hierro.\n";
            EndBattle.updateBattleStats(1, numBat+1, wasteWoodIron[0], wasteWoodIron[1]);
            
            String logAt5 = "GANADOR: Civilización (menos pérdidas de recursos).";
            EndBattle.insertBattleLogs(1,numBat+1,logAt5,2);
            String logAt6 = "La civilización obtiene los residuos: " + wasteWoodIron[0] + " madera, " + wasteWoodIron[1] + " hierro.";
            EndBattle.insertBattleLogs(1,numBat+1,logAt6,2);
            
            return wasteWoodIron;
        } 
        else if (perdidasEnemigo < perdidasCivilizacion) {
            battleDevelopment += "GANADOR: Enemigo (menos pérdidas de recursos)\n";
            battleDevelopment += "El enemigo obtiene los residuos: "
                                 + wasteWoodIron[0] + " madera, "
                                 + wasteWoodIron[1] + " hierro.\n";
            EndBattle.updateBattleStats(2, numBat+1, wasteWoodIron[0], wasteWoodIron[1]);
            
            String logEn5 = "GANADOR: Enemigo (menos pérdidas de recursos).";
            EndBattle.insertBattleLogs(1,numBat+1,logEn5,2);
            String logEn6 = "El enemigo obtiene los residuos: " + wasteWoodIron[0] + " madera, " + wasteWoodIron[1] + " hierro.";
            EndBattle.insertBattleLogs(1,numBat+1,logEn6,2);
        } 
        else {
            battleDevelopment += "EMPATE: Ambos ejércitos han perdido la misma cantidad de recursod:\n";
            
            String logEmpate = "EMPATE: Ambos ejércitos han perdido la misma cantidad de recursos.";
            EndBattle.insertBattleLogs(1,numBat+1,logEmpate,2);
            EndBattle.insertBattleLogs(2,numBat+1,logEmpate,1);

            
        }
        int[] lista = new int[2];
        return lista;
    }


    
    //Getter y Setters
    
	public ArrayList<MilitaryUnit> getCivilizationArmy() {
		return civilizationArmy;
	}

	public void setCivilizationArmy(ArrayList<MilitaryUnit> civilizationArmy) {
		this.civilizationArmy = civilizationArmy;
	}

	public ArrayList<MilitaryUnit> getEnemyArmy() {
		return enemyArmy;
	}

	public void setEnemyArmy(ArrayList<MilitaryUnit> enemyArmy) {
		this.enemyArmy = enemyArmy;
	}

	public ArrayList<MilitaryUnit>[][] getArmies() {
		return armies;
	}

	public void setArmies(ArrayList<MilitaryUnit>[][] armies) {
		this.armies = armies;
	}
	

	public int[][] getInitialCostFleet() {
		return initialCostFleet;
	}

	public void setInitialCostFleet(int[][] initialCostFleet) {
		this.initialCostFleet = initialCostFleet;
	}

	public int getInitialNumberUnitsCivilization() {
		return initialNumberUnitsCivilization;
	}

	public void setInitialNumberUnitsCivilization(int initialNumberUnitsCivilization) {
		this.initialNumberUnitsCivilization = initialNumberUnitsCivilization;
	}

	public int getInitialNumberUnitsEnemy() {
		return initialNumberUnitsEnemy;
	}

	public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
		this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
	}

	public int[] getWasteWoodIron() {
		return wasteWoodIron;
	}

	public void setWasteWoodIron(int[] wasteWoodIron) {
		this.wasteWoodIron = wasteWoodIron;
	}

	public int getEnemyDrops() {
		return enemyDrops;
	}

	public void setEnemyDrops(int enemyDrops) {
		this.enemyDrops = enemyDrops;
	}

	public int getCivilizationDrops() {
		return civilizationDrops;
	}

	public void setCivilizationDrops(int civilizationDrops) {
		this.civilizationDrops = civilizationDrops;
	}

	public int[][] getResourceLooses() {
		return resourceLooses;
	}

	public void setResourceLooses(int[][] resourceLooses) {
		this.resourceLooses = resourceLooses;
	}

	public int[][] getInitialArmies() {
		return initialArmies;
	}

	public void setInitialArmies(int[][] initialArmies) {
		this.initialArmies = initialArmies;
	}

	public int[] getActualNumberUnitsCivilization() {
		return actualNumberUnitsCivilization;
	}

	public void setActualNumberUnitsCivilization(int[] actualNumberUnitsCivilization) {
		this.actualNumberUnitsCivilization = actualNumberUnitsCivilization;
	}

	public int[] getActualNumberUnitsEnemy() {
		return actualNumberUnitsEnemy;
	}

	public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
		this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
	}   
}