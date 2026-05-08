package Proyecte_Civilizations;

abstract class AttackUnit implements MilitaryUnit,Variables{
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	public AttackUnit(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
		this.experience = 0;
		this.sanctified = false;
	}
	public int getArmor() {
		return armor;
	}
	public int getInitialArmor() {
		return initialArmor;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public boolean isSanctified() {
		return sanctified;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	public int getExperienceUnit() {
		return experience;
	}
	public void setExperienceUnit(int experience) {
		this.experience = experience;
	}
}
class Swordsam extends AttackUnit{
	//Nuestras unidades
	public Swordsam(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}
	//Unidades enemigas
	public Swordsam() {
		super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
	}
	public int attack() {
		return getBaseDamage();
	}
	public void takeDamage(int receivedDamage) {	
		setArmor(getArmor()-receivedDamage);
	}
	public int getActualArmor() {
		return getArmor();
	}
	public int getFoodCost() {
		return FOOD_COST_SWORDSMAN;
	}
	public int getWoodCost() {
		return WOOD_COST_SWORDSMAN;
	}
	public int getIronCost() {
		return IRON_COST_SWORDSMAN;
	}

	public int getManaCost() {
		return MANA_COST_SWORDSMAN;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_SWORDSMAN;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SWORDSMAN;
	}
	public void resetArmor() {	
		setArmor(getInitialArmor());
	}
	public void setExperience(int n) {
		setExperienceUnit(getExperienceUnit()+n);
	}
	public int getExperience() {	
		return getExperienceUnit();
	}
}

class Spearman extends AttackUnit{
	//Nuestras unidades
	public Spearman(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}
	//Unidades enemigas
	public Spearman() {
		super(ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
	}
	public int attack() {
		return getBaseDamage();
	}
	public void takeDamage(int receivedDamage) {	
		setArmor(getArmor()-receivedDamage);
	}
	public int getActualArmor() {
		return getArmor();
	}
	public int getFoodCost() {
		return FOOD_COST_SPEARMAN;
	}
	public int getWoodCost() {
		return WOOD_COST_SPEARMAN;
	}
	public int getIronCost() {
		return IRON_COST_SPEARMAN;
	}

	public int getManaCost() {
		return MANA_COST_SPEARMAN;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_SPEARMAN;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SPEARMAN;
	}
	public void resetArmor() {	
		setArmor(getInitialArmor());
	}
	public void setExperience(int n) {
		setExperienceUnit(getExperienceUnit()+n);
	}
	public int getExperience() {	
		return getExperienceUnit();
	}
}

class Crossbow extends AttackUnit{

	//Nuestras unidades
	public Crossbow(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}
	//Unidades enemigas
	public Crossbow() {
		super(ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
	}
	public int attack() {
		return getBaseDamage();
	}
	public void takeDamage(int receivedDamage) {	
		setArmor(getArmor()-receivedDamage);
	}
	public int getActualArmor() {
		return getArmor();
	}
	public int getFoodCost() {
		return FOOD_COST_CROSSBOW;
	}
	public int getWoodCost() {
		return WOOD_COST_CROSSBOW;
	}
	public int getIronCost() {
		return IRON_COST_CROSSBOW;
	}

	public int getManaCost() {
		return MANA_COST_CROSSBOW;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CROSSBOW;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CROSSBOW;
	}
	public void resetArmor() {	
		setArmor(getInitialArmor());
	}
	public void setExperience(int n) {
		setExperienceUnit(getExperienceUnit()+n);
	}
	public int getExperience() {	
		return getExperienceUnit();
	}
}
class Cannon extends AttackUnit{

	//Nuestras unidades
	public Cannon(int initialArmor, int baseDamage) {
		super(initialArmor, baseDamage);
	}
	//Unidades enemigas
	public Cannon() {
		super(ARMOR_CANNON, BASE_DAMAGE_CANNON);
	}
	public int attack() {
		return getBaseDamage();
	}
	public void takeDamage(int receivedDamage) {	
		setArmor(getArmor()-receivedDamage);
	}
	public int getActualArmor() {
		return getArmor();
	}
	public int getFoodCost() {
		return FOOD_COST_CANNON;
	}
	public int getWoodCost() {
		return WOOD_COST_CANNON;
	}
	public int getIronCost() {
		return IRON_COST_CANNON;
	}

	public int getManaCost() {
		return MANA_COST_CANNON;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CANNON;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CANNON;
	}
	public void resetArmor() {	
		setArmor(getInitialArmor());
	}
	public void setExperience(int n) {
		setExperienceUnit(getExperienceUnit()+n);
	}
	public int getExperience() {	
		return getExperienceUnit();
	}
}