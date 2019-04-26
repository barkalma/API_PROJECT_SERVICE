package com.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe abstraite DAO.
 *
 * <p>Cette classe définit toutes les méthodes CRUD utilisées par les DAO.</p>
 *
 * @param <T> le type paramétré permettant aux classes filles d'être modulables.
 * @author Thomas MENARD et Maxime LENORMAND
 */
public abstract class DAO<T> {

    private DAOFactory daoFactory;

    /**
     * Constructeur de DAO.
     *
     * @param daoFactory la Factory permettant la création d'une connexion à la BDD.
     */
    DAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public DAOFactory getDaoFactory() {
        return this.daoFactory;
    }

    /**
     * Insère un objet dans la BDD à partir des attributs spécifiés dans un bean objet.
     *
     * @param objet l'objet que l'on souhaite insérer dans la BDD à partir du bean objet.
     * @throws SQLException 
     */
    public abstract void creer(T objet) throws SQLException;

    /**
     * Liste tous les objets ayant pour attributs les mêmes que ceux
     * spécifiés dans un bean objet.
     *
     * @param objet l'objet que l'on souhaite trouver dans la BDD.
     * @return objets la liste des objets trouvés dans la BDD.
     * @throws SQLException 
     */
    public abstract List<T> trouver(T objet) throws SQLException;

    /**
     * Modifie UN objet ayant pour attributs les mêmes que ceux
     * spécifiés dans un bean objet et la même clé primaire.
     * Cette clé primaire ne peut être modifiée.
     *
     * @param objet l'objet que l'on souhaite modifier dans la BDD.
     * @throws SQLException 
     */
    public abstract void modifier(T objet) throws SQLException ;

    /**
     * Supprime tous les objets ayant pour attributs les mêmes que ceux
     * spécifiés dans un bean objet.
     *
     * @param objet l'objet que l'on souhaite supprimer dans la BDD.
     * @throws SQLException 
     */
    public abstract void supprimer(T objet) throws SQLException;

    /**
     * Liste tous les objets présents dans la BDD.
     *
     * @return objets la liste des objets présents dans la BDD.
     * @throws SQLException 
     */
    public abstract List<T> lister() throws SQLException;

}