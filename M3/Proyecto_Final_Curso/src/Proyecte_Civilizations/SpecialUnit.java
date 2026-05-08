package Proyecte_Civilizations;


abstract class SpecialUnit implements MilitaryUnit,Variables{
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	public SpecialUnit(int baseDamage) {
		super();
		this.armor = 0;
		this.initialArmor = 0;
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

class Magician extends SpecialUnit{
	public Magician(int baseDamage) {
		super(baseDamage);
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
		return FOOD_COST_MAGICIAN;
	}
	public int getWoodCost() {
		return WOOD_COST_MAGICIAN;
	}
	public int getIronCost() {
		return IRON_COST_MAGICIAN;
	}

	public int getManaCost() {
		return MANA_COST_MAGICIAN;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_MAGICIAN;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_MAGICIAN;
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

class Priest extends SpecialUnit{
	public Priest(int baseDamage) {
		super(0);
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
		return FOOD_COST_PRIEST;
	}
	public int getWoodCost() {
		return WOOD_COST_PRIEST;
	}
	public int getIronCost() {
		return IRON_COST_PRIEST;
	}

	public int getManaCost() {
		return MANA_COST_PRIEST;
	}
	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_PRIEST;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_PRIEST;
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
