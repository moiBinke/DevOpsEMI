package com.devOpsEmi.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devOpsEmi.dao.ProduitRepository;
import com.devOpsEmi.dao.ProgrammeRepository;
import com.devOpsEmi.dao.TestsRepository;
import com.devOpsEmi.dao.UtilisateurRepository;
import com.devOpsEmi.entities.Produit;
import com.devOpsEmi.entities.Programme;
import com.devOpsEmi.entities.Tests;
import com.devOpsEmi.entities.Utilisateur;

@Controller
public class DevOpsLabControl {
	@Autowired
	UtilisateurRepository gerantUtilisateur;
	@Autowired
	ProduitRepository gerantProduit;
	@Autowired
	ProgrammeRepository gerantProgramme;
	@Autowired
	TestsRepository gerantTests;
	
	
	@RequestMapping("/archives")
	public String archive() {
		return "archive";
	}
	@RequestMapping("gestionConfigurationIntegration")
	public String gestionConfigurationIntegration() {
		return "gestionConfigurationIntegration";
	}
	
	@RequestMapping("/gestionConfigurations")
	public String gestionConfigurations() {
		return "gestionConfigurations";
	}
	@RequestMapping("gestionConfigurationTestSystem")
	public String gestionConfigurationTestSystem() {
		return "gestionConfigurationTestSystem";
	}
	
	//GESTION DES PROGRAMMES
	@RequestMapping("/gestionProgramme")
	public String gestionProgramme(Model model) {
		List <Produit> produits=gerantProduit.findAll();
		model.addAttribute("produits", produits);
		List<Programme> programmes=gerantProgramme.chercherProgrammeNonPlannifie("plannifié");
		model.addAttribute("programmes", programmes);
		System.out.println("La taille est: "+programmes.size());
		return "gestionProgramme";
	}

	@RequestMapping("/ajouterUnProduit")
	public String ajouterUnProduit(Model model,String nomProduit, String nomChef) {
		Utilisateur utilisateur=null;
		try {
			 utilisateur=gerantUtilisateur.chercherUtilisateur(nomChef);
		}catch(Exception e) {
			return "gestionProgramme";
		}
		String erreurAjoutProgramme = null;
		if(utilisateur!=null) {
			if(utilisateur.getRole().compareTo("ChefProjet")==0) {
				Produit produit=new Produit(nomProduit,utilisateur);
				gerantProduit.save(produit);
			}
			else {
				erreurAjoutProgramme="Seul les chefs de projets peuvent ajouter un produit";
			}
		}
		else {
			erreurAjoutProgramme="Il faut demander une insceription chez l'administrateur";
		}
		model.addAttribute("erreurAjoutProgramme", erreurAjoutProgramme);
		
		List<Programme> programmes=gerantProgramme.chercherProgrammeNonPlannifie("plannifié");
		model.addAttribute("programmes", programmes);
		
		List <Produit> produits=gerantProduit.findAll();
		model.addAttribute("produits", produits);
		
		return "gestionProgramme";
	}
	
	@RequestMapping("/ajouterUnProgramme")
	public String ajouterUnProgramme(Model model, String nomProgramme, String descriptionProgramme,String produitConcerne) {
		
		Produit produitConcern=gerantProduit.chercherProduitByNom(produitConcerne);
		Programme prog=new Programme( nomProgramme,  produitConcern, "temporaire");
		gerantProgramme.save(prog);
		
		List<Programme> programmes=gerantProgramme.chercherProgrammeNonPlannifie("plannifié");
		model.addAttribute("programmes", programmes);
		
		List <Produit> produits=gerantProduit.findAll();
		model.addAttribute("produits", produits);
		return "gestionProgramme";
	}
	
	@RequestMapping("/soumettreUnProgramme")
	public String soumettreUnProgramme(Model model, String idProgrammeEnChaine) {
		Long idProgramme=(long) Integer.parseInt(idProgrammeEnChaine);
		Programme programme=gerantProgramme.getOne(idProgramme);
		programme.setEtatProgrammeDB("soumis");
		gerantProgramme.save(programme);
		List<Programme> programmesSoumis=gerantProgramme.chercherProgrammeParEtat("soumis");
		model.addAttribute("programmesSoumis", programmesSoumis);
		List<Programme> programmes=gerantProgramme.findAll();
		model.addAttribute("programmes", programmes);
		List<Utilisateur> utilisateurs=gerantUtilisateur.chercherUtilisateurByStatus("TesteurUnitaire");
		model.addAttribute("utilisateurs", utilisateurs);
		List <Produit> produits=gerantProduit.findAll();
		model.addAttribute("produits", produits);
		return "gestionTestPlanning";
	}
	@RequestMapping("/plannifierProgramme")
	public String plannifierProgramme(Model model, String idProduitEnChaine,String idProgrammeEnChaine,String personneCharge,String dateDeTest) {
		Long idProduit=(long)Integer.parseInt(idProduitEnChaine);	
		Long idProgramme=(long)Integer.parseInt(idProgrammeEnChaine);
		Utilisateur chargeTest=gerantUtilisateur.getOne(personneCharge);
		Programme programmeAPlannifier=gerantProgramme.getOne(idProgramme);
		Tests test=new Tests( programmeAPlannifier,  dateDeTest,  chargeTest);
		programmeAPlannifier.setEtatProgrammeDB("plannifié");
		gerantTests.save(test);
		
		List<Tests> tests=gerantTests.findAll();
		model.addAttribute("tests", tests);
		List<Programme> programmesSoumis=gerantProgramme.chercherProgrammeParEtat("soumis");
		model.addAttribute("programmesSoumis", programmesSoumis);
		List<Programme> programmes=gerantProgramme.findAll();
		model.addAttribute("programmes", programmes);
		List<Utilisateur> utilisateurs=gerantUtilisateur.chercherUtilisateurByStatus("TesteurUnitaire");
		model.addAttribute("utilisateurs", utilisateurs);
		List <Produit> produits=gerantProduit.findAll();
		model.addAttribute("produits", produits);
		return "gestionTestPlanning";
	}

	
	@RequestMapping("/supprimerProgramme")
	public String supprimerProgramme(Model model, String idProgrammeEnChaine) {
		Long idProgramme=(long) Integer.parseInt(idProgrammeEnChaine);
		gerantProgramme.deleteById(idProgramme);
		List<Programme> programmes=gerantProgramme.findAll();
		
		List <Produit> produits=gerantProduit.findAll();
		model.addAttribute("produits", produits);
		return "gestionProgramme";
	}
	
	
	
	@RequestMapping("/gestionTestExecutionTest")
	public String gestionTestExecution() {
		return "gestionTestExecutionTest";
	}
	
	@RequestMapping("/gestionTestPlanning")
	public String gestionTestPlanning(Model model) {
		List<Tests> tests=gerantTests.findAll();
		model.addAttribute("tests", tests);
		List<Programme> programmesSoumis=gerantProgramme.chercherProgrammeParEtat("soumis");
		model.addAttribute("programmesSoumis", programmesSoumis);
		List<Programme> programmes=gerantProgramme.findAll();
		model.addAttribute("programmes", programmes);
		List<Utilisateur> utilisateurs=gerantUtilisateur.chercherUtilisateurByStatus("TesteurUnitaire");
		model.addAttribute("utilisateurs", utilisateurs);
		List <Produit> produits=gerantProduit.findAll();
		model.addAttribute("produits", produits);
		return "gestionTestPlanning";
	}
	
	@RequestMapping("/gestionTests")
	public String gestionTest() {
		return "gestionTests";
	}
	
	//DroitD'auteur
	@RequestMapping("gestionDroitAuteur")
	public String gestionDroitAuteur(Model model) {
		List <Utilisateur> utilisateurs=gerantUtilisateur.findAll();
		model.addAttribute("utilisateurs", utilisateurs);
		return "gestionDroitAuteur";
	}
	
	@RequestMapping("ajouterUnutilisateur")
	public String ajouterUnutilisateur(Model model,String nomUtilisateur, String email,String motDePasse,String statut) {
		Utilisateur utilisateur=new Utilisateur( nomUtilisateur,  email,  motDePasse,  statut);		
		gerantUtilisateur.save(utilisateur);
		List <Utilisateur> utilisateurs=gerantUtilisateur.findAll();
		model.addAttribute("utilisateurs", utilisateurs);
		return"gestionDroitAuteur";
	}
	@RequestMapping("supprimerUtilisateur")
	public String supprimerUtilisateur(Model model,String nomUtilisateur) {
		gerantUtilisateur.deleteById(nomUtilisateur);
		List <Utilisateur> utilisateurs=gerantUtilisateur.findAll();
		model.addAttribute("utilisateurs", utilisateurs);
		return "gestionDroitAuteur";
	}
	
	@RequestMapping("afficherStatus")
	public String afficherStatus(Model model,String status) {
		List <Utilisateur> utilisateurs=null;
		if(status.compareTo("Tous les status")==0) {
			utilisateurs=gerantUtilisateur.findAll();
		}
		else {
			utilisateurs=gerantUtilisateur.chercherUtilisateurByStatus(status);
		}
		model.addAttribute("utilisateurs", utilisateurs);
		return "gestionDroitAuteur";
	}	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
}
