package com.devOpsEmi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="configuration")
public class Configuration implements Serializable{
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_configuration")
	private Long idConfiguration;
	@Column(name="nom_configuration",length=30)
	String nomConfiguration;
	@OneToMany(mappedBy="configuration",cascade={CascadeType.PERSIST, CascadeType.REMOVE},  orphanRemoval=true)
	List<Programme> programmes;
	@ManyToOne
	Produit produit;
	public Long getIdConfiguration() {
		return idConfiguration;
	}
	public void setIdConfiguration(Long idConfiguration) {
		this.idConfiguration = idConfiguration;
	}
	public String getNomConfiguration() {
		return nomConfiguration;
	}
	public void setNomConfiguration(String nomConfiguration) {
		this.nomConfiguration = nomConfiguration;
	}
	public List<Programme> getProgrammes() {
		return programmes;
	}
	public void setProgrammes(List<Programme> programmes) {
		this.programmes = programmes;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Configuration(String nomConfiguration, Produit produit) {
		super();
		this.nomConfiguration = nomConfiguration;
		this.produit = produit;
	}
	
}
