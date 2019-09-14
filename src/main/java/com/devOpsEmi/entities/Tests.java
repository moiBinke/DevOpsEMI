package com.devOpsEmi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="test")
public class Tests {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_test")
	private Long idTest;
	@ManyToOne
	private Programme programme;
	private String date;
	@ManyToOne
	private Utilisateur chargeTest;
	public Long getIdTest() {
		return idTest;
	}
	public void setIdTest(Long idTest) {
		this.idTest = idTest;
	}
	public Programme getProgramme() {
		return programme;
	}
	public void setProgramme(Programme programme) {
		this.programme = programme;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Utilisateur getChargeTest() {
		return chargeTest;
	}
	public void setChargeTest(Utilisateur chargeTest) {
		this.chargeTest = chargeTest;
	}
	public Tests(Programme programme, String date, Utilisateur chargeTest) {
		super();
		this.programme = programme;
		this.date = date;
		this.chargeTest = chargeTest;
	}
	public Tests() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
