package com.devOpsEmi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devOpsEmi.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {
	@Query("select u from Utilisateur u where u.nom= :nom")
	Utilisateur chercherUtilisateur(@Param("nom")String nom);
	
	@Query("select u from Utilisateur u where u.role=:status")
	List <Utilisateur> chercherUtilisateurByStatus(@Param("status")String status);
}