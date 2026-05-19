package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;
import Proyecte_Civilizations.Variables.*;


public class UpdateDefense {


	private static final String url = Variables.LOCAL_URL;
    private static final String usuario = Variables.LOCAL_USU;
    private static final String pass = Variables.LOCAL_PASS;

    
	public static void main(String[] args) {
		
	}
	
	public static void buyArrowTower() {
		
        try {

            // Cargar Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");

            // Crear conexión con BBDD
            Connection conn = DriverManager.getConnection(url, usuario, pass);
            System.out.println("Conexión creada correctamente");

            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET wood_amount = (wood_amount - ?) where civilization_id = ?"
            		+ "and wood_amount >= ? ";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.WOOD_COST_ARROWTOWER);
			ps_update.setInt(2,1);
			ps_update.setInt(3, Variables.WOOD_COST_ARROWTOWER);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo ArrowTower adquirido");
				
			}
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update);
        } catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}

		
	}

	public static void buyCatapult() {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET wood_amount = (wood_amount - ?),"
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1,Variables.WOOD_COST_CATAPULT);
			ps_update.setInt(2,Variables.IRON_COST_CATAPULT);
			ps_update.setInt(3,1);
			ps_update.setInt(4,Variables.WOOD_COST_CATAPULT);
			ps_update.setInt(5,Variables.IRON_COST_CATAPULT);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Catapult adquirido");
			}

			System.out.println("Se ha realizado el update correctamente. \n"+ps_update);

	    } catch (ClassNotFoundException e) {
	
			e.printStackTrace();
			System.out.println("Error al cargar el driver: "+e);
		}
		catch (SQLException e) {
			System.out.println("Error al realizar la conexión");
			e.printStackTrace();
		}
	
		
	}

	public static void buyRocketLauncherTower() {
		
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET wood_amount = (wood_amount - ?),"
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1,Variables.WOOD_COST_ROCKETLAUNCHERTOWER);
			ps_update.setInt(2,Variables.IRON_COST_ROCKETLAUNCHERTOWER);
			ps_update.setInt(3,1);
			ps_update.setInt(4,Variables.WOOD_COST_ROCKETLAUNCHERTOWER);
			ps_update.setInt(5,Variables.IRON_COST_ROCKETLAUNCHERTOWER);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo RocketLauncherTower adquirido");
				
			}
			
			System.out.println("Se ha realizado el update correctamente. \n"+ps_update);
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