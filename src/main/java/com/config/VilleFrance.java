package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VilleFrance {
	
public static List<String> RecupererVille() {
		
		List<String> villes = new ArrayList<String>();
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			Connection connect = DriverManager
					.getConnection("jdbc:mysql://localhost/integrationcontinue?user=root&password=&serverTimezone=Europe/Paris");

			Statement stm = connect.createStatement();

			ResultSet rset = stm.executeQuery("SELECT * FROM ville_france WHERE Code_postal LIKE '01%' ORDER BY Nom_commune ASC");
			
			while (rset.next()) {
				villes.add(rset.getString("Nom_commune"));
			}
	
			// Fermeture de la connexion
			rset.close();
			stm.close();
			connect.close();
			
			return villes;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
