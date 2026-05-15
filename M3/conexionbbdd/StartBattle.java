package conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StartBattle {

    private static final String url = "jdbc:mysql://localhost/civi_mnr?serverTimezone=UTC";
    private static final String usuario = "root";
    private static final String pass = "mysqlocal";

    public static void main(String[] args) {

        loadUnits("ataque", 1);
        loadUnits("defensa", 1);
        loadUnits("especial", 1);

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

        try {

            // Cargar driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear conexión
            Connection conn = DriverManager.getConnection(url, usuario, pass);

            // PreparedStatement
            PreparedStatement ps = conn.prepareStatement(consulta);

            ps.setInt(1, idcivi);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nTABLA: " + tabla);

            while (rs.next()) {
                System.out.println("idCivi - " + rs.getInt("civilization_id") + ", Tipo - " + rs.getString("type") + ", Exp - " + rs.getInt("experience"));
            }

            // Cerrar recursos
            rs.close();
            ps.close();
            conn.close();

        } catch (ClassNotFoundException e) {

            System.out.println("Error al cargar driver");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Error SQL");
            e.printStackTrace();
        }
    }
}