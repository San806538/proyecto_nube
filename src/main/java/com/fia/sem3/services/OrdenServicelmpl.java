package com.fia.sem3.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fia.sem3.DAO.OrdenDao;
import com.fia.sem3.Entity.Orden;
import com.fia.sem3.Entity.Usuario;

@Service
public class OrdenServicelmpl implements IOrdenService {
	@Autowired
	private OrdenDao ordenDao;
	
	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return ordenDao.findAll();
	}

	@Override
	public Optional<Orden> findById(Integer id) {
		// TODO Auto-generated method stub
		return ordenDao.findById(id);
	}

	@Override
	public Orden save(Orden orden) {
		// TODO Auto-generated method stub
		return ordenDao.save(orden);
	}

	@Override
	public String generarNumeroOrden() {
		int numero =0;
		String numeroConcatenado="";
		
		List<Orden> ordenes=findAll();
		List<Integer>numeros= new ArrayList<Integer>();
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
		if(ordenes.isEmpty()){
			numero=1;
		}else {
			numero=numeros.stream().max(Integer::compare).get();
			numero++;
		}
		if(numero<10) {
			numeroConcatenado ="0000"+String.valueOf(numero);
		}else if(numero<100){
			numeroConcatenado ="0000"+ String.valueOf(numero);
		}else if(numero < 1000){
			numeroConcatenado = "0000"+ String.valueOf(numero);
		}
		
		return numeroConcatenado;
	}

	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return ordenDao.findByUsuario(usuario);
	}

	@Override
	public Orden findOne(Integer id) {
		// TODO Auto-generated method stub
		return ordenDao.findById(id).orElse(null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
