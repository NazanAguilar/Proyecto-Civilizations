package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;

public class EndBattle {

	public static void main(String[] args) {
		
		/*
		endBattle(5);
		
		insertAttack(1, "Swordsman", 5);
		insertAttack(1, "Crossbow", 5);
		insertSpecial(1, "Magician", 5);
		insertDefense(1, "Catapult", 5);
		insertSpecial(1, "Priest", 5);
		insertDefense(1, "ArrorTower", 5);
		 
		*/
		
	}
	public static void endBattle(int idCivi ) {
		String url = "jdbc:mysql://localhost/civi_mnr?serverTimezone=UTC";
	    String usuario = "root";
	    String pass = "mysqlocal";
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL, tras cada batalla sumamos 1 al contador de batallas
	        String update_counter = "UPDATE civilization_stats "
            		+ "SET battles_counter = (battles_counter + 1) where civilization_id = ?";
            
			PreparedStatement ps_update_count = conn.prepareStatement(update_counter,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update_count.setInt(1,idCivi);
					
			ps_update_count.executeUpdate();
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update_count);
			
	    
	    } catch (ClassNotFoundException e) {
	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
		
	}
	public static void insertAttack(int idCivi, String tipo, int exp) {
		String url = "jdbc:mysql://localhost/civi_mnr?serverTimezone=UTC";
	    String usuario = "root";
	    String pass = "mysqlocal";
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO attack_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) "
					+ " VALUES (?, ?, 0, 0, ?, false)";
			
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	   
			ps_insert.setInt(1, idCivi);
			ps_insert.setString(2,tipo);
			ps_insert.setInt(3,exp);
			
			ps_insert.executeUpdate();
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_insert);
			
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}
	public static void insertDefense(int idCivi, String tipo, int exp) {
		String url = "jdbc:mysql://localhost/civi_mnr?serverTimezone=UTC";
	    String usuario = "root";
	    String pass = "mysqlocal";
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO defense_units_stats (civilization_id, type, armor, base_damage, experience, sanctified) "
					+ "	VALUES (?, ?, 0, 0, ?, false);";
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			

			ps_insert.setInt(1, idCivi);
			ps_insert.setString(2,tipo);
			ps_insert.setInt(3,exp);
			
			ps_insert.executeUpdate();

			System.out.println("Se ha realizado el update correctamente. \n"+ps_insert);
			
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}

	public static void insertSpecial(int idCivi, String tipo, int exp) {
		String url = "jdbc:mysql://localhost/civi_mnr?serverTimezone=UTC";
	    String usuario = "root";
	    String pass = "mysqlocal";
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO special_units_stats (civilization_id, type, armor, base_damage, experience) "
					+ " VALUES (?, ?, 0, 0, ?)";
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	    

			ps_insert.setInt(1, idCivi);
			ps_insert.setString(2,tipo);
			ps_insert.setInt(3,exp);
			
			ps_insert.executeUpdate();
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_insert);
			
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}	
	}

	
	
	
/*	public static void battleStats(int idCivi, int numBat, int WoodAdq, int ironAdq) {
		String url = "jdbc:mysql://localhost/civi_mnr?serverTimezone=UTC";
	    String usuario = "root";
	    String pass = "mysqlocal";
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");

		// Insert SQL
			String insert = "INSERT INTO battle_stats (civilization_id, type, armor, base_damage, experience, sanctified) "
					+ "	VALUES (?, ?, 0, 0, ?, false);";
			PreparedStatement ps_insert = conn.prepareStatement(insert,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			

			ps_insert.setInt(1, idCivi);
			ps_insert.setString(2,tipo);
			ps_insert.setInt(3,exp);
			
			ps_insert.executeUpdate();
			
	    } catch (ClassNotFoundException e) {
	    	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	}

	*/
	
}
