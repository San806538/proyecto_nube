package com.fia.sem3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fia.sem3.DAO.UsuarioDao;
import com.fia.sem3.Entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}
	
	@Override
	public Optional<Usuario> findById(Integer id){
		return usuarioDao.findById(id);
	}
	
	@Override
	public Usuario save(Usuario usuario){
		return usuarioDao.save(usuario);
	}
	
	@Override
	public Optional<Usuario> findByEmail(String email){
		return usuarioDao.findByEmail(email);
	}
	
	@Override
	public Usuario findOne(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	
}
