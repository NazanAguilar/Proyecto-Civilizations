package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Proyecte_Civilizations.Variables;
import Proyecte_Civilizations.Civilization;

public class StartBattle {

	private static final String url = Variables.REMOT_URL;
    private static final String usuario = Variables.REMOT_USU;
    private static final String pass = Variables.REMOT_PASS;



    public static void main(String[] args) {

    }


    public static Civilization loadCivi(int idcivi) {

        String consulta = "SELECT wood_amount, iron_amount, food_amount, mana_amount, "
                + "magicTower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, "
                + "technology_defense_level, technology_attack_level, battles_counter "
                + "FROM civilization_stats WHERE civilization_id = ?";

        Civilization civi = null;

        try {

            // Cargar driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear conexión
            Connection conn = DriverManager.getConnection(url, usuario, pass);

            // Consulta
            PreparedStatement psCons = conn.prepareStatement(consulta);

            psCons.setInt(1, idcivi);

            ResultSet rs = psCons.executeQuery();

            if (rs.next()) {

                civi = new Civilization(

                    rs.getInt("technology_defense_level"),
                    rs.getInt("technology_attack_level"),
                    rs.getInt("wood_amount"),
                    rs.getInt("iron_amount"),
                    rs.getInt("food_amount"),
                    rs.getInt("mana_amount"),
                    rs.getInt("magicTower_counter"),
                    rs.getInt("church_counter"),
                    rs.getInt("farm_counter"),
                    rs.getInt("smithy_counter"),
                    rs.getInt("carpentry_counter"),
                    rs.getInt("battles_counter")
                );

            }

            // Cerrar recursos
            rs.close();
            psCons.close();
            conn.close();

        } catch (ClassNotFoundException e) {

            System.out.println("Error al cargar driver");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Error SQL");
            e.printStackTrace();
        }

        return civi;
    }
    
    public static void loadUnits(String tabla, int idcivi) {

    	String from = "";
    	
    	if (tabla == "ataque") {
    		from = "attack_units_stats";
    		
    	} else if (tabla == "defensa") {
    		from = "defense_units_stats";

    		
    	} else if (tabla == "especial") {
    		from = "special_units_stats";
    		
    		
    	}
    	
        String consulta = "SELECT civilization_id, type, experience FROM " + from + " WHERE civilization_id = ?";
        String delete = "DELETE FROM " + from + " WHERE civilization_id = ?";

        try {

            // Cargar driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear conexión
            Connection conn = DriverManager.getConnection(url, usuario, pass);

            // Consulta
            PreparedStatement psCons = conn.prepareStatement(consulta);

            psCons.setInt(1, idcivi);

            ResultSet rs = psCons.executeQuery();

            System.out.println("\nTABLA: " + tabla);

            while (rs.next()) {
                System.out.println("idCivi - " + rs.getInt("civilization_id") + ", Tipo - " + rs.getString("type") + ", Exp - " + rs.getInt("experience"));
            }

            //Delete 
            PreparedStatement psDel = conn.prepareStatement(delete);

            psDel.setInt(1, idcivi);

            int filasBorradas = psDel.executeUpdate();

            System.out.println("Filas eliminadas: " + filasBorradas);

            
            // Cerrar recursos
            rs.close();
            psCons.close();
            psDel.close();
            conn.close();

        } catch (ClassNotFoundException e) {

            System.out.println("Error al cargar driver");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Error SQL");
            e.printStackTrace();
        }
    }
    
    public static void modifyLevel(int idCivi, String tipo ) {

    	
		try {
	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexi贸n con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexi贸n creada correctamente");
	
            // Update SQL
	        String update_level = "";
	    	if (tipo == "ataque") {
	    		update_level = "UPDATE civilization_stats "
	            		+ "SET technology_attack_level = (technology_attack_level + 1) where civilization_id = ?";

	    		
	    	} else if (tipo == "defensa") {
	    		update_level = "UPDATE civilization_stats "
	            		+ "SET technology_defense_level = (technology_defense_level + 1) where civilization_id = ?";

	    		
	    	}
            
			PreparedStatement ps_update_count = conn.prepareStatement(update_level,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			

			ps_update_count.setInt(1,idCivi);
					
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
			System.out.println("Error al realizar la conexi贸n");
			e.printStackTrace();
		}
		
	}
    public static void updateIniResources(int idCivi, int wood, int iron, int food, int mana) {
        try {
        	
	        // Cargar Driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Driver cargado correctamente");
	
	        // Crear conexión con BBDD
	        Connection conn = DriverManager.getConnection(url, usuario, pass);
	        System.out.println("Conexión creada correctamente");
	
            // Update SQL, tras cada batalla sumamos 1 al contador de batallas
	        String update_counter = "UPDATE civilization_stats "
            		+ "SET wood_amount = ?, iron_amount = ?, food_amount = ?, mana_amount = ?  where civilization_id = ?";
            
			PreparedStatement ps_update_count = conn.prepareStatement(update_counter,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			
			ps_update_count.setInt(1,wood);
			ps_update_count.setInt(2,iron);
			ps_update_count.setInt(3,food);
			ps_update_count.setInt(4,mana);
			ps_update_count.setInt(5,idCivi);
					
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
    
    public static int loadNumBattle() {
    	
    	int numBat = 0 ;

        String consulta = "SELECT id_battle FROM battle_log order by id_battle desc limit 1;";
        
        try {

            // Cargar driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear conexión
            Connection conn = DriverManager.getConnection(url, usuario, pass);

            // Consulta
            PreparedStatement psCons = conn.prepareStatement(consulta);

            ResultSet rs = psCons.executeQuery();

            while (rs.next()) {
                numBat = rs.getInt("id_battle");
            }
            // Cerrar recursos
            rs.close();
            psCons.close();
            conn.close();

        } catch (ClassNotFoundException e) {

            System.out.println("Error al cargar driver");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Error SQL");
            e.printStackTrace();
        }
            
    	return numBat;
    	
	}
}
