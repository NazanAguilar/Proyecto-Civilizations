package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;

public class PruebaRemoto {

	//remoto
    
    private static final String url = Variables.REMOT_URL;
    private static final String usuario = Variables.REMOT_USU;
    private static final String pass = Variables.REMOT_PASS;
    
    
    //local 
	/*
    private static final String url = Variables.LOCAL_URL;
    private static final String usuario = Variables.LOCAL_USU;
    private static final String pass = Variables.LOCAL_PASS;
	*/
    
    public static void main(String[] args) {

    	prueba();
    }

    public static void prueba() {


        try {
        	
        	// Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL, tras cada batalla sumamos 1 al contador de batallas
	        String update_counter = "UPDATE civilization_stats "
            		+ "SET name = ? where civilization_id = ?";
            
			PreparedStatement ps_update_count = conn.prepareStatement(update_counter,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update_count.setString(1,"Civi10");
			ps_update_count.setInt(2,10);
					
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
}