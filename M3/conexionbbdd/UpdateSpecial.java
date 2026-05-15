package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;
import Proyecte_Civilizations.Variables.*;



public class UpdateSpecial {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void buyMagician() {
		
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
			
			ps_update.setInt(1, Variables.FOOD_COST_MAGICIAN);
			ps_update.setInt(2,Variables.WOOD_COST_MAGICIAN);
			ps_update.setInt(3,Variables.IRON_COST_MAGICIAN);
			ps_update.setInt(4,Variables.MANA_COST_MAGICIAN);
			ps_update.setInt(5,1);
			ps_update.setInt(6, Variables.FOOD_COST_MAGICIAN);
			ps_update.setInt(7,Variables.WOOD_COST_MAGICIAN);
			ps_update.setInt(8,Variables.IRON_COST_MAGICIAN);
			ps_update.setInt(9,Variables.MANA_COST_MAGICIAN);
			
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Magician adquirido");
				
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

	public static void buyPriest() {
	
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
	
            // Update SQL
            String update = "UPDATE civilization_stats "
            		+ "SET food_amount = (food_amount - ?),"
            		+ "mana_amount = (mana_amount - ?) where civilization_id = ? "
            		+ "and food_amount >= ? "
            		+ "and mana_amount >= ? ";
            
			PreparedStatement ps_update = conn.prepareStatement(update,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update.setInt(1, Variables.FOOD_COST_PRIEST);
			ps_update.setInt(2,Variables.MANA_COST_PRIEST);
			ps_update.setInt(3,1);
			ps_update.setInt(4, Variables.FOOD_COST_PRIEST);
			ps_update.setInt(5,Variables.MANA_COST_PRIEST);
					
			int filasActualizadas = ps_update.executeUpdate();

			if (filasActualizadas == 0) {
			    System.out.println("No hay recursos suficientes para realizar la compra");
			}
			else {
				System.out.println(" Nuevo Priest adquirido");
				
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