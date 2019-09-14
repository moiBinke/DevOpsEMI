package com.devOpsEmi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Produit implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name="id_produit")
	private Long idProoduit;
	@Column(name="nom_produit",length=30)
	private String nomProduit;
	@OneToOne
	private Utilisateur chefDeProjet;
	@OneToMany(mappedBy="produit",cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	List<Configuration> configurations;
	public Long getIdProoduit() {
		return idProoduit;
	}
	public void setIdProoduit(Long idProoduit) {
		this.idProoduit = idProoduit;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public Utilisateur getChefDeProjet() {
		return chefDeProjet;
	}
	public void setChefDeProjet(Utilisateur chefDeProjet) {
		this.chefDeProjet = chefDeProjet;
	}
	public List<Configuration> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Produit(String nomProduit, Utilisateur chefDeProjet) {
		super();
		this.nomProduit = nomProduit;
		this.chefDeProjet = chefDeProjet;
	}
	
	
}
