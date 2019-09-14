package com.devOpsEmi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devOpsEmi.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long>{
	@Query("select p from Produit p where p.nomProduit=:nomProduit")
	Produit chercherProduitByNom(@Param("nomProduit")String nomProduit);
}
