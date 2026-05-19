package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;


public class UpdateBuildings {

	private static final String url = Variables.LOCAL_URL;
    private static final String usuario = Variables.LOCAL_USU;
    private static final String pass = Variables.LOCAL_PASS;


	public static void main(String[] args) {
		
	}
	
	public static void buyFarm() {
		
        try {
        	System.out.println("ACABAS DE COMPRAR UNA GRANJA");
            // Cargar Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado correctamente");

            // Crear conexión con BBDD
            Connection conn = DriverManager.getConnection(url, usuario, pass);
            System.out.println("Conexión creada correctamente");

            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?),"
            		+ "wood_amount = (wood_amount - ?),"
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_FARM);
			ps_update.setInt(2,Variables.WOOD_COST_FARM);
			ps_update.setInt(3,Variables.IRON_COST_FARM);
			ps_update.setInt(4,1);
			ps_update.setInt(5, Variables.FOOD_COST_FARM);
			ps_update.setInt(6,Variables.WOOD_COST_FARM);
			ps_update.setInt(7,Variables.IRON_COST_FARM);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Farm adquirido");
				
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

	public static void buyCarpentry() {
	
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?),"
            		+ "wood_amount = (wood_amount - ?),"
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_CARPENTRY);
			ps_update.setInt(2,Variables.WOOD_COST_CARPENTRY);
			ps_update.setInt(3,Variables.IRON_COST_CARPENTRY);
			ps_update.setInt(4,1);
			ps_update.setInt(5, Variables.FOOD_COST_CARPENTRY);
			ps_update.setInt(6,Variables.WOOD_COST_CARPENTRY);
			ps_update.setInt(7,Variables.IRON_COST_CARPENTRY);

			
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Carpentry adquirido");
				
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

	public static void buySmithy() {
		
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
	        String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?),"
            		+ "wood_amount = (wood_amount - ?),"
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_SMITHY);
			ps_update.setInt(2,Variables.WOOD_COST_SMITHY);
			ps_update.setInt(3,Variables.IRON_COST_SMITHY);
			ps_update.setInt(4,1);
			ps_update.setInt(5, Variables.FOOD_COST_SMITHY);
			ps_update.setInt(6,Variables.WOOD_COST_SMITHY);
			ps_update.setInt(7,Variables.IRON_COST_SMITHY);

			
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Smithy adquirido");
				
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
	
	public static void buyChurch() {
		
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
	        String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?),"
            		+ "wood_amount = (wood_amount - ?),"
            		+ "iron_amount = (iron_amount - ?),"
            		+ "mana_amount = (mana_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ? "
            		+ "and mana_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_CHURCH);
			ps_update.setInt(2,Variables.WOOD_COST_CHURCH);
			ps_update.setInt(3,Variables.IRON_COST_CHURCH);
			ps_update.setInt(4,Variables.MANA_COST_CHURCH);
			ps_update.setInt(5,1);
			ps_update.setInt(6, Variables.FOOD_COST_CHURCH);
			ps_update.setInt(7,Variables.WOOD_COST_CHURCH);
			ps_update.setInt(8,Variables.IRON_COST_CHURCH);
			ps_update.setInt(9,Variables.MANA_COST_CHURCH);

			
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Church adquirido");
				
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

	public static void buyMagicTower() {
		
	    try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL
	        String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?),"
            		+ "wood_amount = (wood_amount - ?),"
            		+ "iron_amount = (iron_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and wood_amount >= ? "
            		+ "and iron_amount >= ?";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_MAGICTOWER);
			ps_update.setInt(2,Variables.WOOD_COST_MAGICTOWER);
			ps_update.setInt(3,Variables.IRON_COST_MAGICTOWER);
			ps_update.setInt(4,1);
			ps_update.setInt(5, Variables.FOOD_COST_MAGICTOWER);
			ps_update.setInt(6,Variables.WOOD_COST_MAGICTOWER);
			ps_update.setInt(7,Variables.IRON_COST_MAGICTOWER);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo MagicTower adquirido");
				
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