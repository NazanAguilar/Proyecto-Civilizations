package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;

public class EndBattle {
	
	private static final String url = Variables.REMOT_URL;
    private static final String usuario = Variables.REMOT_USU;
    private static final String pass = Variables.REMOT_PASS;

    
	public static void main(String[] args) {
		

	}
	public static void finishBattle(int idCivi, int mTowerCounter, int churchCounter, int farmCounter, int smithyCounter, int carpentryCounter, int ataqueNivel, int defensaNivel  ) {
			
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL, tras cada batalla sumamos 1 al contador de batallas
	        String update_counter = "UPDATE civilization_stats "
            		+ "SET magicTower_counter = ?, church_counter = ?, farm_counter = ?, smithy_counter = ?, carpentry_counter = ?, "
            		+ " technology_attack_level = ?,  technology_defense_level = ?, "
            		+ " battles_counter = (battles_counter + 1) where civilization_id = ?";
            
			PreparedStatement ps_update_count = conn.prepareStatement(update_counter,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update_count.setInt(1,mTowerCounter);
			ps_update_count.setInt(2,churchCounter);
			ps_update_count.setInt(3,farmCounter);
			ps_update_count.setInt(4,smithyCounter);
			ps_update_count.setInt(5,carpentryCounter);
			ps_update_count.setInt(6,ataqueNivel);
			ps_update_count.setInt(7,defensaNivel);
			ps_update_count.setInt(8,idCivi);
					
			ps_update_count.executeUpdate();
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update_count);
			
            // Cerrar recursos
            conn.close();
            ps_update_count.close();
            
	    
	    } catch (ClassNotFoundException e) {
	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
		
	}
	public static void insertAttack(int idCivi, String tipo, int armor, int damage, int exp) {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO attack_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) "
					+ " VALUES (?, ?, ?, ?, ?, false)";
			
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	   
			ps_insert.setInt(1, idCivi);
			ps_insert.setString(2,tipo);
			ps_insert.setInt(3,armor);
			ps_insert.setInt(4,damage);
			ps_insert.setInt(5,exp);

			ps_insert.executeUpdate();
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_insert);
		
            // Cerrar recursos
            conn.close();
            ps_insert.close();

	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}
	public static void insertDefense(int idCivi, String tipo, int armor, int damage, int exp) {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO defense_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) "
					+ "	VALUES (?, ?, ?, ?, ?, false);";
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			

			ps_insert.setInt(1, idCivi);
			ps_insert.setString(2,tipo);
			ps_insert.setInt(3,armor);
			ps_insert.setInt(4,damage);
			ps_insert.setInt(5,exp);

			ps_insert.executeUpdate();

			System.out.println("Se ha realizado el update correctamente. \n"+ps_insert);

            // Cerrar recursos
            conn.close();
            ps_insert.close();
            
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}

	public static void insertSpecial(int idCivi, String tipo, int damage, int exp) {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO special_units_stats (civilization_id, type, armor, base_damage, experience) "
					+ " VALUES (?, ?, 0, ?, ?)";
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	    

			ps_insert.setInt(1, idCivi);
			ps_insert.setString(2,tipo);
			ps_insert.setInt(3,damage);
			ps_insert.setInt(4,exp);
			
			ps_insert.executeUpdate();
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_insert);
			
            // Cerrar recursos
            conn.close();
            ps_insert.close();
            
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}	
	}

	
	public static void newBattle(int idCivi, int numBat, int woodAdq, int ironAdq, int idCiviEne) {
		
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO battle_stats (civilization_id, id_battle,wood_acquired,iron_acquired,civ_enem) "
					+ "	VALUES (?, ?, ?, ?, ?);";
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			

			ps_insert.setInt(1, idCivi);
			ps_insert.setInt(2, numBat);
			ps_insert.setInt(3, woodAdq);
			ps_insert.setInt(4, ironAdq);
			ps_insert.setInt(5, idCiviEne);

			
			ps_insert.executeUpdate();
			
            // Cerrar recursos
            conn.close();
            ps_insert.close();

            
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}

	
	public static void updateBattleStats(int idCivi, int numBat, int woodAdq, int ironAdq) {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String update = "UPDATE battle_stats SET wood_acquired = ? , iron_acquired = ? WHERE civilization_id = ? AND id_battle = ?";
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, woodAdq);
			ps_update.setInt(2, ironAdq);
			ps_update.setInt(3, idCivi);
			ps_update.setInt(4, numBat);

			
			ps_update.executeUpdate();
			
            // Cerrar recursos
            conn.close();
            ps_update.close();

            
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}

	public static void insertBattleLogs(int idCivi, int numBat, String log, int idCiviEne) {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO battle_log (civilization_id, id_battle, log_entry, civ_enem) "
					+ "	VALUES (?, ?, ?, ?);";
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			

			ps_insert.setInt(1, idCivi);
			ps_insert.setInt(2, numBat);
			ps_insert.setString(3, log);
			ps_insert.setInt(4, idCiviEne);

			
			ps_insert.executeUpdate();
			
            // Cerrar recursos
            conn.close();
            ps_insert.close();
            
            
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}


}
