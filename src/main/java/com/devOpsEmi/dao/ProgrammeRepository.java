package com.devOpsEmi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devOpsEmi.entities.Programme;


public interface ProgrammeRepository extends JpaRepository<Programme,Long> {
	@Query("select prog from Programme prog where prog.etatProgrammeDB=:etat")
	List <Programme> chercherProgrammeParEtat(@Param("etat")String etat);
	
	@Query("select prog from Programme prog where prog.etatProgrammeDB !=:etat ")
	List <Programme> chercherProgrammeNonPlannifie(@Param("etat")String etat);

}
