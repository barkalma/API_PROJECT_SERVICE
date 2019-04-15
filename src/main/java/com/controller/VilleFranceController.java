package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import com.blo.VilleFranceBLO;
import com.dao.DAOFactory;
import com.dao.VilleFranceDAO;

import com.config.Application;

@RestController
public class VilleFranceController {

	/* Lien : localhost:8181/villeFrance OUI C'EST LE LIEN BIS */
	@RequestMapping(value = "/villeFrance", method = RequestMethod.GET)
	@ResponseBody

	public List<VilleFranceBLO> get(@RequestParam(required = false, value = "offset") String offset) {
		DAOFactory factory = new DAOFactory(Application.getString("url"), Application.getString("nomUtilisateur"),
				Application.getString("motDePasse"));
		VilleFranceDAO villeFranceDAO = new VilleFranceDAO(factory);
		List<VilleFranceBLO> villeFrance = new ArrayList<VilleFranceBLO>(50);
		villeFrance = villeFranceDAO.lister(Integer.parseInt(offset));
		System.out.println("Appel GET");
		return villeFrance;
	}
	
	@RequestMapping(value = "/villeFranceGet", method = RequestMethod.GET)
	@ResponseBody

	public List<VilleFranceBLO> getAll() {
		DAOFactory factory = new DAOFactory(Application.getString("url"), Application.getString("nomUtilisateur"),
				Application.getString("motDePasse"));
		VilleFranceDAO villeFranceDAO = new VilleFranceDAO(factory);
		List<VilleFranceBLO> villeFrance = new ArrayList<VilleFranceBLO>(50);
		villeFrance = villeFranceDAO.lister();
		System.out.println("Appel GET");
		return villeFrance;
	}

	@RequestMapping(value = "/villeFranceFind", method = RequestMethod.GET)
	@ResponseBody

	public List<VilleFranceBLO> getWhere(@RequestParam(required = true, value = "value") String codeCommuneInsee) {

		DAOFactory factory = new DAOFactory(Application.getString("url"), Application.getString("nomUtilisateur"),
				Application.getString("motDePasse"));
		VilleFranceDAO villeFranceDAO = new VilleFranceDAO(factory);
		VilleFranceBLO villeFranceFind = new VilleFranceBLO();
		villeFranceFind.setCodeCommuneInsee(codeCommuneInsee);
		
		List<VilleFranceBLO> villeFrance = villeFranceDAO.trouver(villeFranceFind);

		return villeFrance;
	}

	@RequestMapping(value = "/villeFranceDelete", method = RequestMethod.POST)
	@ResponseBody

	public String delete(@RequestParam(required = false, value = "value") String ville) {

		String deleteReturn;
		DAOFactory factory = new DAOFactory(Application.getString("url"), Application.getString("nomUtilisateur"),
				Application.getString("motDePasse"));
		VilleFranceDAO villeFranceDAO = new VilleFranceDAO(factory);
		VilleFranceBLO villeFranceFind = new VilleFranceBLO();
		villeFranceFind = this.ville(ville);
		villeFranceDAO.supprimer(villeFranceFind);
		System.out.println("Appel DELETE");
		deleteReturn = "Ville Supprimée de la base";

		return deleteReturn;
	}

	@RequestMapping(value = "/villeFrancePost", method = RequestMethod.POST)
	@ResponseBody

	public String post(@RequestBody String ville) {

		String postReturn;
		DAOFactory factory = new DAOFactory(Application.getString("url"), Application.getString("nomUtilisateur"),
				Application.getString("motDePasse"));
		VilleFranceDAO villeFranceDAO = new VilleFranceDAO(factory);
		villeFranceDAO.modifier(this.ville(ville));
		System.out.println("Appel POST");
		postReturn = "Ville Modifiée de la base";

		return postReturn;
	}

	@RequestMapping(value = "/villeFrancePut", method = RequestMethod.POST)
	@ResponseBody

	public String put(@RequestParam(required = true, value = "value") String ville) {

		String putReturn;
		DAOFactory factory = new DAOFactory(Application.getString("url"), Application.getString("nomUtilisateur"),
				Application.getString("motDePasse"));
		VilleFranceDAO villeFranceDAO = new VilleFranceDAO(factory);
		VilleFranceBLO villeFrance = this.ville(ville);
		villeFranceDAO.creer(villeFrance);
		System.out.println("Appel PUT");
		putReturn = "Ville Créée dans la base";

		return putReturn;
	}

	@RequestMapping(value = "/next")
	public String next(Map<String, Object> model) {
		model.put("message", "You are in new page !!");
		return "next";
	}

	@RequestMapping(value = "/")
	public String home(Map<String, Object> model) {
		model.put("message", "HowToDoInJava Reader !!");
		return "index";
	}

	protected VilleFranceBLO ville(String villeString) {

		VilleFranceBLO ville = new VilleFranceBLO();
		villeString.replace(" ", "");
		villeString.replace("[", "");
		villeString.replace("]", "");
		String[] villepart = villeString.split(",");
		String codeCommune = villepart[0].split("=")[1];
		String nomCommune = villepart[1].split("=")[1];
		String codePostal = villepart[2].split("=")[1];
		String libelle = villepart[3].split("=")[1];
		String ligne = "";
		if (villepart[4].split("=").length == 2) {
			ligne = villepart[4].split("=")[1];
		}
		String lattitude = villepart[5].split("=")[1];
		String longitude = villepart[6].split("=")[1];
		longitude = longitude.split("]")[0];
		ville.setCodeCommuneInsee(codeCommune);
		ville.setNomCommune(nomCommune);
		ville.setCodePostal(codePostal);
		ville.setLibelleAcheminement(libelle);
		ville.setLigne5(ligne);
		ville.setLattitude(lattitude);
		ville.setLongitude(longitude);
		return ville;
	}

}