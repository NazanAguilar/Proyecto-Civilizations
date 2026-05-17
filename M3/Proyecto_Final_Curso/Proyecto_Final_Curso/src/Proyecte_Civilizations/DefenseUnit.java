package Proyecte_Civilizations;

abstract class DefenseUnit implements MilitaryUnit,Variables{
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	public DefenseUnit(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
		this.experience = 0;
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
	
	public int getExperienceUnit() {
		return experience;
	}
	public void setExperienceUnit(int experience) {
		this.experience = experience;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
	
}
class ArrowTower extends DefenseUnit{
	public ArrowTower(int armor, int baseDamage) {
		super(armor, baseDamage);
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
		return FOOD_COST_ARROWTOWER;
	}
	public int getWoodCost() {
		return WOOD_COST_ARROWTOWER;
	}
	public int getIronCost() {
		return IRON_COST_ARROWTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ARROWTOWER;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_ARROWTOWER;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ARROWTOWER;
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

class Catapult extends DefenseUnit{
	public Catapult(int armor, int baseDamage) {
		super(armor, baseDamage);
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
		return FOOD_COST_CATAPULT;
	}
	public int getWoodCost() {
		return WOOD_COST_CATAPULT;
	}
	public int getIronCost() {
		return IRON_COST_CATAPULT;
	}

	public int getManaCost() {
		return MANA_COST_CATAPULT;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CATAPULT;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CATAPULT;
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
class RocketLauncherTower extends DefenseUnit{
	public RocketLauncherTower(int armor, int baseDamage) {
		super(armor, baseDamage);
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
		return FOOD_COST_ROCKETLAUNCHERTOWER;
	}
	public int getWoodCost() {
		return WOOD_COST_ROCKETLAUNCHERTOWER;
	}
	public int getIronCost() {
		return IRON_COST_ROCKETLAUNCHERTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ROCKETLAUNCHERTOWER;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
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