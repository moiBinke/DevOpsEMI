package com.devOpsEmi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devOpsEmi.entities.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration,Long> {

}
