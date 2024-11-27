package com.fia.sem3.services;

import java.util.List;
import java.util.Optional;

import com.fia.sem3.Entity.MetodoEnvio;

public interface IMetodoEnvioService {
	
	public List<MetodoEnvio> findAll();
	
	public MetodoEnvio save(MetodoEnvio metodoEnvio);
	
	public Optional<MetodoEnvio> get(Integer id);
	
	public void update(MetodoEnvio metodoEnvio);
	
	public void delete(Integer id);
	
	
	
}
