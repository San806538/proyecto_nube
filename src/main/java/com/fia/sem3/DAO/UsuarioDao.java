package com.fia.sem3.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fia.sem3.Entity.Usuario;



public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

		Optional<Usuario> findByEmail(String email);
}
