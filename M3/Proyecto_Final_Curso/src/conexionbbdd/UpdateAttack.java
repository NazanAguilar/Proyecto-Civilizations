package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;
import Proyecte_Civilizations.Variables.*;


public class UpdateAttack {
	
	private static final String url = Variables.REMOT_URL;
    private static final String usuario = Variables.REMOT_USU;
    private static final String pass = Variables.REMOT_PASS;

	public static void main(String[] args) {
	}
	
	public static void buySwordsman() {
		
	   try {

            // Cargar Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");

            // Crear conexión con BBDD
            Connection conn = DriverManager.getConnection(url, usuario, pass);
            System.out.println("Conexión creada correctamente");

            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?), "
            		+ "wood_amount = (wood_amount - ?), "
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_SWORDSMAN);
			ps_update.setInt(2,Variables.WOOD_COST_SWORDSMAN);
			ps_update.setInt(3,Variables.IRON_COST_SWORDSMAN);
			ps_update.setInt(4,1);
			ps_update.setInt(5, Variables.FOOD_COST_SWORDSMAN);
			ps_update.setInt(6,Variables.WOOD_COST_SWORDSMAN);
			ps_update.setInt(7,Variables.IRON_COST_SWORDSMAN);

			
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Swordsman adquirido");
				
			}
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update);
			
            // Cerrar recursos
            conn.close();

        } catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}

		
	}

	public static void buySpearman() {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?), "
            		+ "wood_amount = (wood_amount - ?), "
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ? ";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_SPEARMAN);
			ps_update.setInt(2,Variables.WOOD_COST_SPEARMAN);
			ps_update.setInt(3,Variables.IRON_COST_SPEARMAN);
			ps_update.setInt(4,1);
			ps_update.setInt(5, Variables.FOOD_COST_SPEARMAN);
			ps_update.setInt(6,Variables.WOOD_COST_SPEARMAN);
			ps_update.setInt(7,Variables.IRON_COST_SPEARMAN);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Spearman adquirido");
				
			}
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update);

			// Cerrar recursos
            conn.close();

	    } catch (ClassNotFoundException e) {
	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	
		
	}

	public static void buyCrossbow() {
		
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET wood_amount = (wood_amount - ?), "
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ? ";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1,Variables.WOOD_COST_CROSSBOW);
			ps_update.setInt(2,Variables.IRON_COST_CROSSBOW);
			ps_update.setInt(3,1);
			ps_update.setInt(4,Variables.WOOD_COST_CROSSBOW);
			ps_update.setInt(5,Variables.IRON_COST_CROSSBOW);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Crossbow adquirido");
				
			}

			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update);
			
            // Cerrar recursos
            conn.close();

            
	    } catch (ClassNotFoundException e) {
	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	
		
	}
	
	public static void buyCannon() {
		
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
	        String update = "UPDATE civilization_stats "
            		+ "SET wood_amount = (wood_amount - ?), "
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ? ";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1,Variables.WOOD_COST_CANNON);
			ps_update.setInt(2,Variables.IRON_COST_CANNON);
			ps_update.setInt(3,1);
			ps_update.setInt(4,Variables.WOOD_COST_CANNON);
			ps_update.setInt(5,Variables.IRON_COST_CANNON);
			
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Cannon adquirido");
				
			}

			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update);
			
            // Cerrar recursos
            conn.close();

	    
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