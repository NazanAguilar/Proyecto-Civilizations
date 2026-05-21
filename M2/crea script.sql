DROP SCHEMA IF EXISTS civi_mnr;
CREATE SCHEMA civi_mnr COLLATE = utf8_general_ci;
USE civi_mnr;

/* *************************************************************** 
***************************CREATING TABLES************************
**************************************************************** */

-- Tabla principal
CREATE TABLE civilization_stats (
    civilization_id int NOT NULL,
    name VARCHAR(50),
    wood_amount int,
    iron_amount int,
    food_amount int,
    mana_amount int,
    magicTower_counter int,
    church_counter int,
    farm_counter int,
    smithy_counter int,
    carpentry_counter int,
    technology_defense_level int,
    technology_attack_level int,
    battles_counter int,
    CONSTRAINT pk_civilization_stats PRIMARY KEY (civilization_id)
);
-- Tabla de unidades de ataque
CREATE TABLE attack_units_stats (
    unit_id INT NOT NULL AUTO_INCREMENT,
    civilization_id INT NOT NULL,
    type ENUM ('Swordsman', 'Spearman', 'Crossbow', 'Cannon'),
    armor INT,
    base_damage INT,
    experience INT,
    sanctified BOOLEAN,
    CONSTRAINT pk_attack_units PRIMARY KEY (unit_id, civilization_id),
    CONSTRAINT fk_attack_civ FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);
-- Tabla de unidades defensivas
CREATE TABLE defense_units_stats (
    unit_id int NOT NULL AUTO_INCREMENT,
    civilization_id int NOT NULL,
    type enum("ArrowTower","Catapult","RocketLauncherTower"),
    armor int,
    base_damage int,
    experience int,
    sanctified boolean,
    CONSTRAINT pk_defense_units PRIMARY KEY (unit_id,civilization_id),
    CONSTRAINT fk_defense_civ FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);
-- Tabla de unidades especiales
CREATE TABLE special_units_stats (
    unit_id int NOT NULL AUTO_INCREMENT,
    civilization_id int NOT NULL,
    type enum("Magician","Priest"),
    armor int,
    base_damage int,
    experience int,
    CONSTRAINT pk_special_units PRIMARY KEY (unit_id, civilization_id),
    CONSTRAINT fk_special_civ FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id)
);
-- Tabla principal de batallas
CREATE TABLE battle_stats (
    civilization_id INT NOT NULL,
    id_battle INT NOT NULL,
    wood_acquired INT,
    iron_acquired INT,
    civ_enem int,
    PRIMARY KEY (civilization_id, id_battle),
    FOREIGN KEY (civilization_id) REFERENCES civilization_stats(civilization_id) ON DELETE CASCADE
);
-- Log de la batalla
CREATE TABLE battle_log (
    num_line INT NOT NULL AUTO_INCREMENT,
    civilization_id INT NOT NULL,
    id_battle INT NOT NULL,
    log_entry VARCHAR(200),
    civ_enem INT,
    PRIMARY KEY (num_line),
    FOREIGN KEY (civilization_id, id_battle) REFERENCES battle_stats(civilization_id, id_battle) ON DELETE CASCADE
);
-- Stats de ataque de la civilización
CREATE TABLE civilization_attack_stats (
    civilization_id INT NOT NULL,
    id_battle INT NOT NULL,
    type enum ("Swordsman", "Spearman","Crossbow","Cannon"),
    initial INT,
    drops INT,
    PRIMARY KEY (civilization_id, id_battle, type),
    FOREIGN KEY (civilization_id, id_battle) REFERENCES battle_stats(civilization_id, id_battle) ON DELETE CASCADE
);
-- Stats de defensa de la civilización
CREATE TABLE civilization_defense_stats (
    civilization_id INT NOT NULL,
    id_battle INT NOT NULL,
	type enum("ArrorTower","Catapult","RocketLauncherTower"),
    initial INT,
    drops INT,
    PRIMARY KEY (civilization_id, id_battle, type),
    FOREIGN KEY (civilization_id, id_battle) REFERENCES battle_stats(civilization_id, id_battle) ON DELETE CASCADE
);

-- Stats de unidades especiales de la civilización
CREATE TABLE civilization_special_stats (
    civilization_id INT NOT NULL,
    id_battle INT NOT NULL,
    type enum("Magician","Priest"),
    initial INT,
    drops INT,
    PRIMARY KEY (civilization_id, id_battle, type),
    FOREIGN KEY (civilization_id, id_battle) REFERENCES battle_stats(civilization_id, id_battle) ON DELETE CASCADE
);

commit;
