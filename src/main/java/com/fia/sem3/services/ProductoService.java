package com.fia.sem3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fia.sem3.DAO.ProductoDao;
import com.fia.sem3.Entity.Producto;
@Service
public class ProductoService implements IProductoService {
	
	@Autowired
	private ProductoDao productoDao;
	
	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return productoDao.findAll();
	}

	@Override
	public Producto save(Producto plato) {
		// TODO Auto-generated method stub
		return productoDao.save(plato);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id);
	}

	@Override
	public void update(Producto plato) {
		// TODO Auto-generated method stub
		productoDao.save(plato);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productoDao.deleteById(id);
	}

	@Override
	public Producto findOne(Integer id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}

}
