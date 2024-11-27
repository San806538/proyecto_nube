package com.fia.sem3.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fia.sem3.Entity.MetodoEnvio;

public interface EnvioDao extends JpaRepository<MetodoEnvio, Integer>{

}
