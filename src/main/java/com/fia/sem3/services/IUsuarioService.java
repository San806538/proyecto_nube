package com.fia.sem3.services;

import java.util.List;
import java.util.Optional;

import com.fia.sem3.Entity.Usuario;

public interface IUsuarioService {

	List<Usuario> findAll();

	Optional<Usuario> findById(Integer id);

	Usuario save(Usuario usuario);

	Optional<Usuario> findByEmail(String email);

	public Usuario findOne(Integer id);

}
