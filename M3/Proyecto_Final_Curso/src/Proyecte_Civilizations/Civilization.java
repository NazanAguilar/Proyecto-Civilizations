package Proyecte_Civilizations;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import javax.swing.JOptionPane;
import conexionbbdd.*;

public class Civilization implements Variables{
	private int technologyDefense ;
	private int technologyAtack ;
	private int wood;
	private int iron;
	private int food;
	private int mana;
	private int magicTower;
	private int church;
	private int farm;
	private int smithy;
	private int carpentry;
	private int battles;
	private ArrayList<MilitaryUnit>[] army;
	private Timer battleTimer;
	private Random random = new Random();

    private boolean timersPaused = false;
	public Civilization(int technologyDefense, int technologyAtack, int wood, int iron, int food, int mana,
			int magicTower, int church, int farm, int smithy, int carpentry, int battles) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAtack = technologyAtack;
		this.wood = wood;
		this.iron = iron;
		this.food = food;
		this.mana = mana;
		this.magicTower = magicTower;
		this.church = church;
		this.farm = farm;
		this.smithy = smithy;
		this.carpentry = carpentry;
		this.battles = battles;
        army = new ArrayList[9];
        for (int i = 0; i < army.length; i++) {
            army[i] = new ArrayList<MilitaryUnit>();
        }
        
        startAutomaticBattles();
	}
	public void newChurch(){
		if (food < FOOD_COST_CHURCH || iron < IRON_COST_CHURCH
		        || wood < WOOD_COST_CHURCH|| mana < MANA_COST_CHURCH) {
		    try {
		        throw new ResourceException("No hay recursos para construir la iglesia");
		    } catch (ResourceException e) {
		        System.out.println(e.getMessage());
		    }
		} else {
		    church += 1;
		    food -= FOOD_COST_CHURCH;
		    iron -= IRON_COST_CHURCH;
		    wood -= WOOD_COST_CHURCH;
		    mana -= MANA_COST_CHURCH;
		    UpdateBuildings.buyChurch();
		}

	}
	public void newMagicTower() {
		if (food < FOOD_COST_MAGICTOWER || iron < IRON_COST_MAGICTOWER
				|| wood < WOOD_COST_MAGICTOWER ) {
			try {
				throw new ResourceException("No hay recursos para contruir la Torre magica");

			} catch ( ResourceException e) {
				System.out.println(e.getMessage());
			}
		}else {
			magicTower +=1;
		    food -= FOOD_COST_MAGICTOWER;
		    iron -= IRON_COST_MAGICTOWER;
		    wood -= WOOD_COST_MAGICTOWER;
		    UpdateBuildings.buyMagicTower();
		}
	}
	public void newFarm() {
		if (food < FOOD_COST_FARM || iron < IRON_COST_FARM
				|| wood < WOOD_COST_FARM) {
			try {
				throw new ResourceException("No hay recursos para contruir la Granja");

			} catch ( ResourceException e) {
				System.out.println(e.getMessage());
			}
		}else {
			farm +=1;
		    food -= FOOD_COST_FARM;
		    iron -= IRON_COST_FARM;
		    wood -= WOOD_COST_FARM;
		    UpdateBuildings.buyFarm();
		}
	}
	public void newCarpentry() {
		if (food < FOOD_COST_CARPENTRY || iron < IRON_COST_CARPENTRY
				|| wood < WOOD_COST_CARPENTRY ) {
			try {
				throw new ResourceException("No hay recursos para contruir la Carpenteria");

			} catch ( ResourceException e) {
				System.out.println(e.getMessage());
			}
		}else {
			carpentry += 1;
		    food -= FOOD_COST_CARPENTRY;
		    iron -= IRON_COST_CARPENTRY;
		    wood -= WOOD_COST_CARPENTRY;
		    UpdateBuildings.buyCarpentry();
		}
	}
	public void newSmithy() {	
		if (food < FOOD_COST_SMITHY || iron < IRON_COST_SMITHY
				|| wood < WOOD_COST_SMITHY ) {
			try {
				throw new ResourceException("No hay recursos para contruir la Herreria");

			} catch ( ResourceException e) {
				System.out.println(e.getMessage());
			}
		}else {
			smithy +=1;
		    food -= FOOD_COST_SMITHY;
		    iron -= IRON_COST_SMITHY;
		    wood -= WOOD_COST_SMITHY;
		    UpdateBuildings.buySmithy();
		}
	}
	public void upgradeTechnologyDefense() {
		int precioiron = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST + UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST *technologyDefense;
		int preciowood = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST + UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST * technologyDefense;
		if (iron < precioiron || wood < preciowood ) {
			try {
				throw new ResourceException("No hay recursos para mejorar la Tecnologia de Defensa");

			} catch ( ResourceException e) {
				System.out.println(e.getMessage());
			}
		}else {
			technologyDefense +=1;
			iron -= precioiron;
			wood -= preciowood;
			StartBattle.modifyLevel(1,"defensa");
		}
	}
	public void upgradeTechnologyAttack() {
		int precioiron =UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST + UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST *technologyAtack ;
		int preciowood = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST + UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST * technologyAtack;
		if (iron < precioiron || wood <  preciowood ) {
			try {
				throw new ResourceException("No hay recursos para mejorar la Tecnologia de Ataque");

			} catch ( ResourceException e) {
				System.out.println(e.getMessage());
			}
		}else {
			technologyAtack +=1;
			iron -= precioiron;
			wood -= preciowood;
			StartBattle.modifyLevel(1,"ataque");
		}
	}
	
	public void newSwordsman(int n) {
		int creados = 0;
		for(int i = 0; i< n ;i++) {
			if(food<  FOOD_COST_SWORDSMAN || wood < WOOD_COST_SWORDSMAN ||iron < IRON_COST_SWORDSMAN) {
	            try {
	                throw new ResourceException("No hay recursos para añadir todos los Swordsman");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los Swordsman");
	                break;
	            }
			}else {
				army[0].add(new Swordsam(ARMOR_SWORDSMAN+(PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY*technologyDefense),BASE_DAMAGE_SWORDSMAN+(PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY*technologyAtack)));
				food -= FOOD_COST_SWORDSMAN;
				wood -= WOOD_COST_SWORDSMAN;
				iron -= IRON_COST_SWORDSMAN;
				creados +=1;
				UpdateAttack.buySwordsman();
			}
		}
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + " Swordsman"
	        );
	    }

	}
	public void newSpearman(int n) {
		int creados = 0;
		for(int i = 0; i< n ;i++) {
			if(food<  FOOD_COST_SPEARMAN || wood < WOOD_COST_SPEARMAN ||iron < IRON_COST_SPEARMAN) {
	            try {
	                throw new ResourceException("No hay recursos para añadir todos los Spearman");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los Spearman");
	                break;
	            }
			}else {
				army[1].add(new Spearman(ARMOR_SPEARMAN+(PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY*technologyDefense),BASE_DAMAGE_SPEARMAN+(PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY*technologyAtack)));
				food -= FOOD_COST_SPEARMAN;
				wood -= WOOD_COST_SPEARMAN;
				iron -= IRON_COST_SPEARMAN;
				creados +=1;
				UpdateAttack.buySpearman();
			}
		}
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + " Spearman"
	        );
	    }
	}
	public void newCrossbow(int n) {
		int creados = 0;
		for(int i = 0; i< n ;i++) {
			if(food<  FOOD_COST_CROSSBOW || wood < WOOD_COST_CROSSBOW ||iron < IRON_COST_CROSSBOW) {
	            try {
	                throw new ResourceException("No hay recursos para añadir todos los Crossbow");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los Crossbow");
	                break;
	            }
			}else {
				army[2].add(new Crossbow(ARMOR_CROSSBOW+(PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY*technologyDefense),BASE_DAMAGE_CROSSBOW+(PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY*technologyAtack)));
				food -= FOOD_COST_CROSSBOW;
				wood -= WOOD_COST_CROSSBOW;
				iron -= IRON_COST_CROSSBOW;
				creados +=1;
				UpdateAttack.buyCrossbow();
			}
		}
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + " Crossbow"
	        );
	    }
	}
	public void newCannon(int n) {
		int creados = 0;
		for(int i = 0; i< n ;i++) {
			if(food<  FOOD_COST_CANNON || wood < WOOD_COST_CANNON ||iron < IRON_COST_CANNON) {
	            try {
	                throw new ResourceException("No hay recursos para añadir todos los Cannon");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los Cannon");
	                break;
	            }
			}else {
				army[3].add(new Cannon(ARMOR_CANNON+(PLUS_ARMOR_CANNON_BY_TECHNOLOGY*technologyDefense),BASE_DAMAGE_CANNON+(PLUS_ATTACK_CANNON_BY_TECHNOLOGY*technologyAtack)));
				food -= FOOD_COST_CANNON;
				wood -= WOOD_COST_CANNON;
				iron -= IRON_COST_CANNON;
				creados +=1;
				UpdateAttack.buyCannon();
			}
		}
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + " Canon"
	        );
	    }
	}
	public void newArrowTower(int n) {
		int creados = 0;
		for(int i = 0; i< n ;i++) {
			if(food<  FOOD_COST_ARROWTOWER || wood < WOOD_COST_ARROWTOWER ||iron < IRON_COST_ARROWTOWER) {
	            try {
	                throw new ResourceException("No hay recursos para añadir todos los ArrowTower");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los ArrowTower");
	                break;
	            }
			}else {
				army[4].add(new ArrowTower(ARMOR_ARROWTOWER+(PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY*technologyDefense),BASE_DAMAGE_ARROWTOWER+(PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY*technologyAtack)));
				food -= FOOD_COST_ARROWTOWER;
				wood -= WOOD_COST_ARROWTOWER;
				iron -= IRON_COST_ARROWTOWER;
				creados +=1;
				UpdateDefense.buyArrowTower();
			}
		}
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + "Arrow Tower"
	        );
	    }
	}
	public void newCatapult(int n) {
		int creados = 0;
		for(int i = 0; i< n ;i++) {
			if(food<  FOOD_COST_CATAPULT || wood < WOOD_COST_CATAPULT ||iron < IRON_COST_CATAPULT) {
	            try {
	                throw new ResourceException("No hay recursos para añadir todos las Catapulas");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos las Catapultas");
	                break;
	            }
			}
			army[5].add(new Catapult(ARMOR_CATAPULT+(PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY*technologyDefense),BASE_DAMAGE_CATAPULT+(PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY*technologyAtack)));
			food -= FOOD_COST_CATAPULT;
			wood -= WOOD_COST_CATAPULT;
			iron -= IRON_COST_CATAPULT;
			creados +=1;
			UpdateDefense.buyCatapult();

		}
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + "Catapultas"
	        );
	    }
	}
	public void newRocketLauncher(int n) {
	    int creados = 0;

	    for (int i = 0; i < n; i++) {

	        if (food < FOOD_COST_ROCKETLAUNCHERTOWER ||
	            wood < WOOD_COST_ROCKETLAUNCHERTOWER ||
	            iron < IRON_COST_ROCKETLAUNCHERTOWER) {

	            try {
	                throw new ResourceException("No hay recursos para añadir todos los Rocket Launcher Tower");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los Rocket Launcher Tower");
	                break;
	            }
	        }

	        army[6].add(new RocketLauncherTower(
	            ARMOR_ROCKETLAUNCHERTOWER + (PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * technologyDefense),
	            BASE_DAMAGE_ROCKETLAUNCHERTOWER + (PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY * technologyAtack)
	        ));

	        food -= FOOD_COST_ROCKETLAUNCHERTOWER;
	        wood -= WOOD_COST_ROCKETLAUNCHERTOWER;
	        iron -= IRON_COST_ROCKETLAUNCHERTOWER;

	        creados++;
	        UpdateDefense.buyArrowTower();
	    }

	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + " Rocket Launcher Tower"
	        );
	    }
	}

	public void newMagician(int n) {
		int creados = 0;
		for(int i = 0; i< n ;i++) {
			if(food<  FOOD_COST_MAGICIAN || wood < WOOD_COST_MAGICIAN ||iron < IRON_COST_MAGICIAN || mana < MANA_COST_MAGICIAN) {
	            try {
	                throw new ResourceException("No hay recursos para añadir todos los Magician");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los Magician");
	                break;
	            }
			}
			army[7].add(new Magician(BASE_DAMAGE_MAGICIAN+(PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY*technologyAtack)));
			food -= FOOD_COST_SPEARMAN;
			wood -= WOOD_COST_SPEARMAN;
			iron -= IRON_COST_SPEARMAN;
			mana -= MANA_COST_MAGICIAN;
			creados +=1;
			UpdateSpecial.buyMagician();

		}
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + " Magician"
	        );
	    }
	}
	public void newPriest(int n) {
	    int creados = 0;

	    for(int i = 0; i < n; i++) {

	        if (i == church) {
                JOptionPane.showMessageDialog(
	                    null,
	                    "No puedes crear más Priests porque solo tienes " + church + " iglesias.");
	                break;
	        }

	        if(food < FOOD_COST_PRIEST ||
	           wood < WOOD_COST_PRIEST ||
	           iron < IRON_COST_PRIEST ||
	           mana < MANA_COST_PRIEST) {

	            try {
	                throw new ResourceException("No hay recursos para añadir todos los Priest");

	            } catch (ResourceException e) {

	                JOptionPane.showMessageDialog(
	                    null,
	                    "No hay recursos para añadir todos los Priest");
	                break;
	            }

	        }
            army[8].add(new Priest(0));
            food -= FOOD_COST_PRIEST;
            wood -= WOOD_COST_PRIEST;
            iron -= IRON_COST_PRIEST;
            mana -= MANA_COST_PRIEST;
            creados++;
            UpdateSpecial.buyPriest();
	    }
	    if (creados > 0) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Se han creado " + creados + " Priest"
	        );
	    }
	}

	
	public void printStats() {

	    System.out.println("****************************CIVILIZATION STATS****************************");

	    System.out.println("------------------------------------------------TECHNOLOGY------------------------------------------------");
	    System.out.printf("%-25s %-25s\n", "Atack", "Defense");
	    System.out.printf("%-25d %-25d\n", technologyAtack, technologyDefense);

	    System.out.println("\n------------------------------------------------BUILDINGS------------------------------------------------");
	    System.out.printf("%-12s %-12s %-12s %-15s %-12s\n",
	            "Farm", "Smithy", "Carpentry", "Magic Tower", "Church");
	    System.out.printf("%-12d %-12d %-12d %-15d %-12d\n",
	            farm, smithy, carpentry, magicTower, church);

	    System.out.println("\n------------------------------------------------DEFENSES------------------------------------------------");
	    System.out.printf("%-18s %-15s %-15s\n",
	            "Arrow Tower", "Catapult", "Rocket Launcher");
	    System.out.printf("%-18d %-15d %-15d\n",
	            army[4].size(), army[5].size(), army[6].size());

	    System.out.println("\n------------------------------------------------ATTACK UNITS------------------------------------------------");
	    System.out.printf("%-15s %-15s %-15s %-15s\n",
	            "Swordsman", "Spearman", "Crossbow", "Cannon");
	    System.out.printf("%-15d %-15d %-15d %-15d\n",
	            army[0].size(), army[1].size(), army[2].size(), army[3].size());

	    System.out.println("\n------------------------------------------------ESPECIAL UNITS------------------------------------------------");
	    System.out.printf("%-12s %-12s\n", "Magician", "Priest");
	    System.out.printf("%-12d %-12d\n", army[7].size(), army[8].size());

	    System.out.println("\n------------------------------------------------RESOURCES------------------------------------------------");
	    System.out.printf("%-12s %-12s %-12s %-12s\n",
	            "Food", "Wood", "Iron", "Mana");
	    System.out.printf("%-12d %-12d %-12d %-12d\n",
	            food, wood, iron, mana);

	    System.out.println("\n------------------------------------------------GENERATION RESOURCES------------------------------------------------");
	    System.out.printf("%-12s %-12s %-12s %-12s\n",
	            "Food", "Wood", "Iron", "Mana");
	    System.out.printf("%-12d %-12d %-12d %-12d\n",
	            farm * 8000, carpentry * 5000, smithy * 1500, magicTower * 0);
	}
    private void pauseAllTimers() {
        timersPaused = true;
    }

    private void resumeAllTimers() {
        timersPaused = false;
    }

	private void startAutomaticBattles() {

	    battleTimer = new Timer();

	    TimerTask battleTask = new TimerTask() {
	        @Override
	        public void run() {

	            if (timersPaused) return; 

	            try {
	                ArrayList<MilitaryUnit> playerArmy = getCompleteArmy();
	                if (playerArmy.size() <= 0) return;

	                ArrayList<MilitaryUnit> enemyArmy = generateBalancedEnemyArmy(playerArmy.size());

	                Battle battle = new Battle(playerArmy, enemyArmy);
	                battle.Batalla();
	                battles++;

	                pauseAllTimers();

	                showBattleWindow(
	                    battle.getBattleDevelopment(),
	                    battle.getBattleReport(battles)
	                );

	                resumeAllTimers();

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    };

	    battleTimer.scheduleAtFixedRate(battleTask, 60000, 60000);

	    TimerTask threatTask = new TimerTask() {
	        @Override
	        public void run() {

	            if (timersPaused) return;

	            ArrayList<MilitaryUnit> playerArmy = getCompleteArmy();
	            if (playerArmy.size() <= 0) return;

	            ArrayList<MilitaryUnit> enemyArmy = generateBalancedEnemyArmy(playerArmy.size());

	            showThreatWindow(enemyArmy);
	        }
	    };

	    battleTimer.scheduleAtFixedRate(threatTask, 45000, 60000);
	}

	
	private ArrayList<MilitaryUnit> getCompleteArmy() {

	    ArrayList<MilitaryUnit> completeArmy = new ArrayList<>();

	    for(int i = 0; i < army.length; i++) {
	        completeArmy.addAll(army[i]);
	    }

	    return completeArmy;
	}
	
	private ArrayList<MilitaryUnit> generateBalancedEnemyArmy(int playerUnits) {

	    ArrayList<MilitaryUnit> enemyArmy = new ArrayList<>();

	    int minUnits = Math.max(1, playerUnits - 5);
	    int maxUnits = playerUnits + 5;

	    int enemyUnits = random.nextInt((maxUnits - minUnits) + 1) + minUnits;

	    for(int i = 0; i < enemyUnits; i++) {

	        int type = random.nextInt(4);

	        switch(type) {

	            case 0:
	                enemyArmy.add(
	                    new Swordsam(
	                        ARMOR_SWORDSMAN +
	                        (PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY * technologyDefense),

	                        BASE_DAMAGE_SWORDSMAN +
	                        (PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY * technologyAtack)
	                    )
	                );
	                break;

	            case 1:
	                enemyArmy.add(
	                    new Spearman(
	                        ARMOR_SPEARMAN +
	                        (PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY * technologyDefense),

	                        BASE_DAMAGE_SPEARMAN +
	                        (PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY * technologyAtack)
	                    )
	                );
	                break;

	            case 2:
	                enemyArmy.add(
	                    new Crossbow(
	                        ARMOR_CROSSBOW +
	                        (PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY * technologyDefense),

	                        BASE_DAMAGE_CROSSBOW +
	                        (PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY * technologyAtack)
	                    )
	                );
	                break;

	            case 3:
	                enemyArmy.add(
	                    new Cannon(
	                        ARMOR_CANNON +
	                        (PLUS_ARMOR_CANNON_BY_TECHNOLOGY * technologyDefense),

	                        BASE_DAMAGE_CANNON +
	                        (PLUS_ATTACK_CANNON_BY_TECHNOLOGY * technologyAtack)
	                    )
	                );
	                break;
	        }
	    }

	    return enemyArmy;
	}
	
	private void showBattleWindow(String development, String report) {

	    JFrame frame = new JFrame("BATALLA EN CURSO");

	    frame.setSize(900, 650);
	    frame.setLocationRelativeTo(null);

	    JTextArea textArea = new JTextArea();

	    textArea.setEditable(false);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);

	    textArea.setBackground(new java.awt.Color(30, 30, 30));
	    textArea.setForeground(java.awt.Color.WHITE);
	    textArea.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

	    String text = "===== DESARROLLO DE BATALLA =====\n\n" +
	                  development +
	                  "\n\n" +
	                  report;

	    textArea.setText(text);

	    JScrollPane scroll = new JScrollPane(textArea);
	    frame.add(scroll);

	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setVisible(true);

	    while (frame.isVisible()) {
	        try { Thread.sleep(200); } catch (Exception e) {}
	    }
	}

	private void showThreatWindow(ArrayList<MilitaryUnit> enemyArmy) {

	    JFrame frame = new JFrame("NEW THREAT COMING");

	    frame.setSize(350, 250);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setAlwaysOnTop(true);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JTextArea text = new JTextArea();
	    text.setEditable(false);
	    text.setBackground(new java.awt.Color(0, 0, 0));
	    text.setForeground(java.awt.Color.WHITE);
	    text.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

	    int swords = 0, spears = 0, cross = 0, cannons = 0;

	    for (MilitaryUnit u : enemyArmy) {
	        if (u instanceof Swordsam) swords++;
	        if (u instanceof Spearman) spears++;
	        if (u instanceof Crossbow) cross++;
	        if (u instanceof Cannon) cannons++;
	    }

	    String msg = "   NEW THREAT COMING\n\n" +
	                 "Swordsman: " + swords + "\n" +
	                 "Spearman: " + spears + "\n" +
	                 "Crossbow: " + cross + "\n" +
	                 "Cannon: " + cannons + "\n";

	    text.setText(msg);

	    JScrollPane scroll = new JScrollPane(text);
	    frame.add(scroll);

	    frame.setVisible(true);

	    pauseAllTimers();

	    while (frame.isVisible()) {
	        try { Thread.sleep(200); } catch (Exception e) {}
	    }

	    resumeAllTimers();
	}

	
	public int getTechnologyDefense() {
		return technologyDefense;
	}

	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}

	public int getTechnologyAtack() {
		return technologyAtack;
	}

	public void setTechnologyAtack(int technologyAtack) {
		this.technologyAtack = technologyAtack;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getMagicTower() {
		return magicTower;
	}

	public void setMagicTower(int magicTower) {
		this.magicTower = magicTower;
	}

	public int getChurch() {
		return church;
	}

	public void setChurch(int church) {
		this.church = church;
	}

	public int getFarm() {
		return farm;
	}

	public void setFarm(int farm) {
		this.farm = farm;
	}

	public int getSmithy() {
		return smithy;
	}

	public void setSmithy(int smithy) {
		this.smithy = smithy;
	}

	public int getCarpentry() {
		return carpentry;
	}

	public void setCarpentry(int carpentry) {
		this.carpentry = carpentry;
	}

	public int getBattles() {
		return battles;
	}

	public void setBattles(int battles) {
		this.battles = battles;
	}

	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}

	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
    public int getFarmFoodCost() 
    { 
        return FOOD_COST_FARM;  
    }

    public int getFarmWoodCost() 
    { 
        return WOOD_COST_FARM;  
    }

    public int getFarmIronCost() 
    { 
        return IRON_COST_FARM;  
    }

    public int getCarpentryFoodCost() 
    { 
        return FOOD_COST_CARPENTRY;  
    }

    public int getCarpentryWoodCost() 
    { 
        return WOOD_COST_CARPENTRY;  
    }

    public int getCarpentryIronCost() 
    { 
        return IRON_COST_CARPENTRY;  
    }

    public int getSmithyFoodCost() 
    { 
        return FOOD_COST_SMITHY;  
    }

    public int getSmithyWoodCost() 
    { 
        return WOOD_COST_SMITHY;  
    }

    public int getSmithyIronCost() 
    { 
        return IRON_COST_SMITHY;  
    }

    public int getChurchFoodCost() 
    { 
        return FOOD_COST_CHURCH;  
    }

    public int getChurchWoodCost() 
    { 
        return WOOD_COST_CHURCH;  
    }

    public int getChurchIronCost() 
    { 
        return IRON_COST_CHURCH;  
    }

    public int getMagicTowerFoodCost() 
    { 
        return FOOD_COST_MAGICTOWER;  
    }

    public int getMagicTowerWoodCost() 
    { 
        return WOOD_COST_MAGICTOWER;  
    }

    public int getMagicTowerIronCost() 
    { 
        return IRON_COST_MAGICTOWER;  
    }

    public int getUnitFoodCost(int tipo) 
    { 
        return FOOD_COST_UNITS[tipo - 1];  
    }

    public int getUnitWoodCost(int tipo) 
    { 
        return WOOD_COST_UNITS[tipo - 1];  
    }

    public int getUnitIronCost(int tipo) 
    { 
        return IRON_COST_UNITS[tipo - 1];  
    }

    public int getTechDefenseIronCost() 
    {
        return UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST +
               UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST * technologyDefense;
    }

    public int getTechDefenseWoodCost() 
    {
        return UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST +
               UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST * technologyDefense;
    }

    public int getTechAttackIronCost() 
    {
        return UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST +
               UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST * technologyAtack;
    }

    public int getTechAttackWoodCost() 
    {
        return UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST +
               UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST * technologyAtack;
    }
    
    public void startResourceTimer(TopPanel topPanel) 
    {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
        	public void run() {

        	    if (timersPaused) return; // ⛔ DETIENE GENERACIÓN

        	    int baseFood = CIVILIZATION_FOOD_GENERATED;
        	    int baseWood = CIVILIZATION_WOOD_GENERATED;
        	    int baseIron = CIVILIZATION_IRON_GENERATED;
        	    
        	    double foodMultiplier = 1 + (0.10 * farm);
        	    double woodMultiplier = 1 + (0.10 * carpentry);
        	    double ironMultiplier = 1 + (0.10 * smithy);

        	    int foodGen = (int)(baseFood * foodMultiplier);
        	    int woodGen = (int)(baseWood * woodMultiplier);
        	    int ironGen = (int)(baseIron * ironMultiplier);

        	    int manaGen = 0;
        	    if(magicTower > 0) {
        	        manaGen+= (200*magicTower);
        	    }

        	    food += foodGen/60;
        	    wood += woodGen/60;
        	    iron += ironGen/60;
        	    mana += manaGen/60;

        	    topPanel.refresh();
        	}

        };

        timer.schedule(task, 0, 1000);
    }
}