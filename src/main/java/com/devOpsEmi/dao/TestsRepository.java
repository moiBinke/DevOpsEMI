package com.devOpsEmi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devOpsEmi.entities.Tests;

public interface TestsRepository extends JpaRepository<Tests,Long> {

}
