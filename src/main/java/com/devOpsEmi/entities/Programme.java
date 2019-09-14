package com.devOpsEmi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name="Programme")
public class Programme implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_programme")
	private Long idProgramme;
	@Column(name="nom_programme",length=30)
	String nomProgramme;
	@ManyToOne
	Produit produitConcerne;
	@Column(name="etat_programme")
	private String etatProgrammeDB;
	@ManyToOne
	Configuration configuration;
	public Long getIdProgramme() {
		return idProgramme;
	}
	public void setIdProgramme(Long idProgramme) {
		this.idProgramme = idProgramme;
	}
	public String getNomProgramme() {
		return nomProgramme;
	}
	public void setNomProgramme(String nomProgramme) {
		this.nomProgramme = nomProgramme;
	}
	public Produit getProduitConcerne() {
		return produitConcerne;
	}
	public void setProduitConcerne(Produit produitConcerne) {
		this.produitConcerne = produitConcerne;
	}
	public String getEtatProgrammeDB() {
		return etatProgrammeDB;
	}
	public void setEtatProgrammeDB(String etatProgrammeDB) {
		this.etatProgrammeDB = etatProgrammeDB;
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	public Programme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Programme(String nomProgramme, Produit produitConcerne, String etatProgrammeDB) {
		super();
		this.nomProgramme = nomProgramme;
		this.produitConcerne = produitConcerne;
		this.etatProgrammeDB = etatProgrammeDB;
	}
	
	
}
