package Proyecte_Civilizations;

import java.util.ArrayList;

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

        // 8) resourceLooses (VACÍO)
        resourceLooses = new int[2][4];

        // 9) wasteWoodIron (VACÍO)
        wasteWoodIron = new int[2];
    }

    
    //Metodos creados 
    public String getBattleReport(int battles) {
        return "";
    }

    public String getBattleDevelopment() {
        return "";
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

        int grupo = -1;
        int sumaTotal = 0;

        for(int i = 0; i < 9; i++) {
            sumaTotal += actualNumberUnitsCivilization[i];
        }

        if(sumaTotal == 0) {
            return -1;
        }

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < 9; i++) {
            acumulado += actualNumberUnitsCivilization[i];
            if(acumulado >= numAleatorio) {
                grupo = i;
                break;
            }
        }

        return grupo;
    }
    public int getGroupDefenderEnemy() {

        int grupo = -1;
        int sumaTotal = 0;

        for(int i = 0; i < 9; i++) {
            sumaTotal += actualNumberUnitsEnemy[i];
        }

        if(sumaTotal == 0) {
            return -1;
        }

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < 9; i++) {
            acumulado += actualNumberUnitsEnemy[i];
            if(acumulado >= numAleatorio) {
                grupo = i;
                break;
            }
        }

        return grupo;
    }

    public int getCivilizationGroupAttacker() {

        int grupo = -1;
        int sumaTotal = 0;

        for(int i = 0; i < 9; i++) {
            sumaTotal += CHANCE_ATTACK_CIVILIZATION_UNITS[i];
        }

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < 9; i++) {
            acumulado += CHANCE_ATTACK_CIVILIZATION_UNITS[i];
            if(acumulado >= numAleatorio) {
                grupo = i;
                break;
            }
        }

        return grupo;
    }

    public int getEnemyGroupAttacker() {

        int grupo = -1;
        int sumaTotal = 0;

        for(int i = 0; i < 4; i++) {
            sumaTotal += CHANCE_ATTACK_ENEMY_UNITS[i];
        }

        int numAleatorio = (int)(Math.random() * sumaTotal) + 1;

        int acumulado = 0;

        for(int i = 0; i < 4; i++) {
            acumulado += CHANCE_ATTACK_ENEMY_UNITS[i];
            if(acumulado >= numAleatorio) {
                grupo = i;
                break;
            }
        }

        return grupo;
    }



    
    public void resetArmyArmor() {
    	//Nuestro ejercito
    	for (int i = 0; i < 9 ;i++ ) {
    		for(int j = 0;j<armies[0][i].size();j++)
    		armies[0][i].get(j).resetArmor();
		}
    	for (int i = 0; i < 4 ;i++ ) {
    		for(int j = 0;j<armies[1][i].size();j++)
    		armies[1][i].get(j).resetArmor();
		}
    	
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
