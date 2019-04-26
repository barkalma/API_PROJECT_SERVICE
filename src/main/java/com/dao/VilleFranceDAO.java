package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.blo.VilleFranceBLO;

public class VilleFranceDAO extends DAO<VilleFranceBLO> {

	private static final String ATTRIBUT_CODE_COMMUNE_INSEE = "Code_commune_INSEE";
	private static final String ATTRIBUT_NOM_COMMUNE = "Nom_commune";
	private static final String ATTRIBUT_CODE_POSTAL = "Code_postal";
	private static final String ATTRIBUT_LIBELLE_ACHEMINEMENT = "Libelle_acheminement";
	private static final String ATTRIBUT_LIGNE_5 = "Ligne_5";
	private static final String ATTRIBUT_LATITUDE = "Latitude";
	private static final String ATTRIBUT_LONGITUDE = "Longitude";

	/* Requetes SQL pour Utilisateur */
	private static final String SQL_INSERT = "INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`) VALUES  (?,?,?,?,?,?,?)";
	private static final String SQL_SELECT_VILLE_FRANCE = "SELECT Code_commune_INSEE, Nom_commune, Code_postal, "
			+ "Libelle_acheminement, " + "Ligne_5, Latitude, Longitude FROM ville_france ";

	private static final String SQL_SELECT_VILLE_FRANCE_PAR_50 = "SELECT Code_commune_INSEE, Nom_commune, Code_postal, "
			+ "Libelle_acheminement, "
			+ "Ligne_5, Latitude, Longitude FROM ville_france ORDER BY Code_commune_INSEE LIMIT 50 OFFSET ?";

	private static final String SQL_SELECT_WHERE = "SELECT Code_commune_INSEE, Nom_commune, Code_postal, "
			+ "Libelle_acheminement, "
			+ "Ligne_5, Latitude, Longitude FROM ville_france WHERE Code_commune_INSEE LIKE ?";
	private static final String SQL_UPDATE = "UPDATE `ville_france` SET `Nom_commune`=?,`Code_postal`=?,`Libelle_acheminement`=?,`Ligne_5`=?,`Latitude`=?,`Longitude`=? WHERE `Code_commune_INSEE`= ?";

	/* Constantes pour éviter la duplication de code */
	private static final String DELETE = "DELETE FROM `ville_france` WHERE `Code_commune_INSEE` LIKE ?";

	private List<VilleFranceBLO> villeFranceListe = new ArrayList<VilleFranceBLO>();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * Constructeur de DAO.
	 *
	 * @param daoFactory la Factory permettant la création d'une connexion à la BDD.
	 */
	public VilleFranceDAO(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public List<VilleFranceBLO> lister() throws SQLException {
		// TODO Auto-generated method stub
		connection = this.creerConnexion();
		preparedStatement = connection.prepareStatement(SQL_SELECT_VILLE_FRANCE);
		return this.executeQuery(preparedStatement);

	}

	public List<VilleFranceBLO> lister(int offset) throws SQLException {
		// TODO Auto-generated method stub

		connection = this.creerConnexion();
		preparedStatement = connection.prepareStatement(SQL_SELECT_VILLE_FRANCE_PAR_50);
		preparedStatement.setInt(1, offset);
		return this.executeQuery(preparedStatement);

	}

	// #################################################
	// # Méthodes privées #
	// #################################################

	/**
	 * Crée une connexion à la BDD.
	 *
	 * @return connection la connexion à la BDD.
	 * @throws SQLException
	 */
	protected Connection creerConnexion() throws SQLException {
		return this.getDaoFactory().getConnection();
	}

	@Override
	public void creer(VilleFranceBLO villeFranceBLO) throws SQLException {

		try {
			// création d'une connexion grâce à la DAOFactory placée en attribut de la
			// classe
			connection = this.creerConnexion();
			preparedStatement = connection.prepareStatement(SQL_INSERT);
			preparedStatement.setString(1, villeFranceBLO.getCodeCommuneInsee());
			preparedStatement.setString(2, villeFranceBLO.getNomCommune());
			preparedStatement.setString(3, villeFranceBLO.getCodePostal());
			preparedStatement.setString(4, villeFranceBLO.getLibelleAcheminement());
			preparedStatement.setString(5, villeFranceBLO.getLigne5());
			preparedStatement.setString(6, villeFranceBLO.getLattitude());
			preparedStatement.setString(7, villeFranceBLO.getLongitude());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public List<VilleFranceBLO> trouver(VilleFranceBLO villeFranceBLO) throws SQLException {

		connection = this.creerConnexion();
		preparedStatement = connection.prepareStatement(SQL_SELECT_WHERE);
		preparedStatement.setString(1, villeFranceBLO.getCodeCommuneInsee());
		return this.executeQuery(preparedStatement);
	}

	@Override
	public void modifier(VilleFranceBLO villeFranceBLO) throws SQLException {
		// TODO Auto-generated method stub

		try {
			// création d'une connexion grâce à la DAOFactory placée en attribut de la
			// classe
			connection = this.creerConnexion();

			/* Traite la mise à jour de la BDD */
			preparedStatement = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, villeFranceBLO.getNomCommune());
			preparedStatement.setString(2, villeFranceBLO.getCodePostal());
			preparedStatement.setString(3, villeFranceBLO.getLibelleAcheminement());
			preparedStatement.setString(4, villeFranceBLO.getLigne5());
			preparedStatement.setString(5, villeFranceBLO.getLattitude());
			preparedStatement.setString(6, villeFranceBLO.getLongitude());
			preparedStatement.setString(7, villeFranceBLO.getCodeCommuneInsee());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void supprimer(VilleFranceBLO villeFranceBLO) throws SQLException {
		// TODO Auto-generated method stub

		try {
			// création d'une connexion grâce à la DAOFactory placée en attribut de la
			// classe
			connection = this.creerConnexion();
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setString(1, villeFranceBLO.getCodeCommuneInsee());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {
			throw e;
		}
	}

	private VilleFranceBLO mapVille(ResultSet resultSet) throws SQLException {
		VilleFranceBLO villeFrance = new VilleFranceBLO();
		villeFrance.setCodeCommuneInsee(resultSet.getString(ATTRIBUT_CODE_COMMUNE_INSEE));
		villeFrance.setNomCommune(resultSet.getString(ATTRIBUT_NOM_COMMUNE));
		villeFrance.setCodePostal(resultSet.getString(ATTRIBUT_CODE_POSTAL));
		villeFrance.setLibelleAcheminement(resultSet.getString(ATTRIBUT_LIBELLE_ACHEMINEMENT));
		villeFrance.setLigne5(resultSet.getString(ATTRIBUT_LIGNE_5));
		villeFrance.setLattitude(resultSet.getString(ATTRIBUT_LATITUDE));
		villeFrance.setLongitude(resultSet.getString(ATTRIBUT_LONGITUDE));
		return villeFrance;
	}

	private List<VilleFranceBLO> executeQuery(PreparedStatement preparedStatement) throws SQLException {
		try {
			resultSet = preparedStatement.executeQuery();
			// récupération des valeurs des attributs de la BDD pour les mettre dans une
			// liste
			while (resultSet.next()) {
				VilleFranceBLO villeFrance = this.mapVille(resultSet);
				this.villeFranceListe.add(villeFrance);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();

		} catch (SQLException e) {
			throw e;
		}
		return villeFranceListe;
	}
}
