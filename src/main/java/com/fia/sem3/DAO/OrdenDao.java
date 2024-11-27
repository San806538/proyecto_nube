package com.fia.sem3.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fia.sem3.Entity.Orden;
import com.fia.sem3.Entity.Usuario;

@Repository
public interface OrdenDao extends JpaRepository<Orden, Integer>{
	
	List<Orden> findByUsuario(Usuario usuario);
}
