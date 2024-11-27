package com.fia.sem3.services;

import java.util.List;
import java.util.Optional;

import com.fia.sem3.Entity.Orden;
import com.fia.sem3.Entity.Usuario;

public interface IOrdenService {
	
	List<Orden> findAll();
	Optional<Orden> findById(Integer id);
	Orden save(Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario(Usuario usuario);
	public Orden findOne(Integer id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
