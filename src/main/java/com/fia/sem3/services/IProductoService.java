package com.fia.sem3.services;

import java.util.List;
import java.util.Optional;

import com.fia.sem3.Entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	public Producto save(Producto plato);

	public Optional<Producto> get(Integer id);

	public void update(Producto plato);

	public void delete(Integer id);

	public Producto findOne(Integer id);
}
