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

        // 8) resourceLooses
        resourceLooses = new int[2][4];

        // CIVILIZACIÓN
        resourceLooses[0][0] += actualNumberUnitsCivilization[0] * FOOD_COST_SWORDSMAN;
        resourceLooses[0][1] += actualNumberUnitsCivilization[0] * WOOD_COST_SWORDSMAN;
        resourceLooses[0][2] += actualNumberUnitsCivilization[0] * IRON_COST_SWORDSMAN;

        resourceLooses[0][0] += actualNumberUnitsCivilization[1] * FOOD_COST_SPEARMAN;
        resourceLooses[0][1] += actualNumberUnitsCivilization[1] * WOOD_COST_SPEARMAN;
        resourceLooses[0][2] += actualNumberUnitsCivilization[1] * IRON_COST_SPEARMAN;

        resourceLooses[0][0] += actualNumberUnitsCivilization[2] * FOOD_COST_CROSSBOW;
        resourceLooses[0][1] += actualNumberUnitsCivilization[2] * WOOD_COST_CROSSBOW;
        resourceLooses[0][2] += actualNumberUnitsCivilization[2] * IRON_COST_CROSSBOW;

        resourceLooses[0][0] += actualNumberUnitsCivilization[3] * FOOD_COST_CANNON;
        resourceLooses[0][1] += actualNumberUnitsCivilization[3] * WOOD_COST_CANNON;
        resourceLooses[0][2] += actualNumberUnitsCivilization[3] * IRON_COST_CANNON;

        resourceLooses[0][0] += actualNumberUnitsCivilization[4] * FOOD_COST_ARROWTOWER;
        resourceLooses[0][1] += actualNumberUnitsCivilization[4] * WOOD_COST_ARROWTOWER;
        resourceLooses[0][2] += actualNumberUnitsCivilization[4] * IRON_COST_ARROWTOWER;

        resourceLooses[0][0] += actualNumberUnitsCivilization[5] * FOOD_COST_CATAPULT;
        resourceLooses[0][1] += actualNumberUnitsCivilization[5] * WOOD_COST_CATAPULT;
        resourceLooses[0][2] += actualNumberUnitsCivilization[5] * IRON_COST_CATAPULT;

        resourceLooses[0][0] += actualNumberUnitsCivilization[6] * FOOD_COST_ROCKETLAUNCHERTOWER;
        resourceLooses[0][1] += actualNumberUnitsCivilization[6] * WOOD_COST_ROCKETLAUNCHERTOWER;
        resourceLooses[0][2] += actualNumberUnitsCivilization[6] * IRON_COST_ROCKETLAUNCHERTOWER;

        resourceLooses[0][0] += actualNumberUnitsCivilization[7] * FOOD_COST_MAGICIAN;
        resourceLooses[0][1] += actualNumberUnitsCivilization[7] * WOOD_COST_MAGICIAN;
        resourceLooses[0][2] += actualNumberUnitsCivilization[7] * IRON_COST_MAGICIAN;

        resourceLooses[0][0] += actualNumberUnitsCivilization[8] * FOOD_COST_PRIEST;
        resourceLooses[0][1] += actualNumberUnitsCivilization[8] * WOOD_COST_PRIEST;
        resourceLooses[0][2] += actualNumberUnitsCivilization[8] * IRON_COST_PRIEST;

        resourceLooses[0][3] += resourceLooses[0][0] * 10 + resourceLooses[0][1] * 5 + resourceLooses[0][2];

        // ENEMIGO
        resourceLooses[1][0] += actualNumberUnitsEnemy[0] * FOOD_COST_SWORDSMAN;
        resourceLooses[1][1] += actualNumberUnitsEnemy[0] * WOOD_COST_SWORDSMAN;
        resourceLooses[1][2] += actualNumberUnitsEnemy[0] * IRON_COST_SWORDSMAN;

        resourceLooses[1][0] += actualNumberUnitsEnemy[1] * FOOD_COST_SPEARMAN;
        resourceLooses[1][1] += actualNumberUnitsEnemy[1] * WOOD_COST_SPEARMAN;
        resourceLooses[1][2] += actualNumberUnitsEnemy[1] * IRON_COST_SPEARMAN;

        resourceLooses[1][0] += actualNumberUnitsEnemy[2] * FOOD_COST_CROSSBOW;
        resourceLooses[1][1] += actualNumberUnitsEnemy[2] * WOOD_COST_CROSSBOW;
        resourceLooses[1][2] += actualNumberUnitsEnemy[2] * IRON_COST_CROSSBOW;

        resourceLooses[1][0] += actualNumberUnitsEnemy[3] * FOOD_COST_CANNON;
        resourceLooses[1][1] += actualNumberUnitsEnemy[3] * WOOD_COST_CANNON;
        resourceLooses[1][2] += actualNumberUnitsEnemy[3] * IRON_COST_CANNON;

        resourceLooses[1][3] += resourceLooses[1][0] * 10 + resourceLooses[1][1] * 5 + resourceLooses[1][2];

        // 9) wasteWoodIron
        wasteWoodIron = new int[2];
    }
    
    //Metodos creados 
    public String getBattleReport(int battles) {
		return battleDevelopment;
    	
    }
    public String getBattleDevelopment() {
		return battleDevelopment;
    	
    }
    public void updateResourcesLooses() {
    	
    }
    
    public void fleetResourceCost(ArrayList<MilitaryUnit> army) {
    	
    }
    public void initialFleetNumber(ArrayList<MilitaryUnit> army) {
    	
    }
    public int remainderPercentageFleet(ArrayList<MilitaryUnit> army) {
		return civilizationDrops;
    	
    }
    public int getGroupDefender(ArrayList<MilitaryUnit> army) {
		return civilizationDrops;
    	
    }
    
    public int getCivilizationGroupAttacker() {
		return civilizationDrops;
    	
    }
    
    public int getEnemyGroupAttacker() {
		return civilizationDrops;
    	
    }
    
    public void resetArmyArmor() {
    	
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
