package com.fia.sem3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.sem3.DAO.EnvioDao;
import com.fia.sem3.Entity.MetodoEnvio;
@Service
public class MetodoEnviolmpl implements IMetodoEnvioService {
	@Autowired
	private EnvioDao envioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<MetodoEnvio> findAll() {
		// TODO Auto-generated method stub
		return envioDao.findAll();
	}

	@Override
	@Transactional
	public MetodoEnvio save(MetodoEnvio metodoEnvio) {
		// TODO Auto-generated method stub
		return envioDao.save(metodoEnvio);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<MetodoEnvio> get(Integer id) {
		// TODO Auto-generated method stub
		return envioDao.findById(id);
	}

	@Override
	@Transactional
	public void update(MetodoEnvio metodoEnvio) {
		// TODO Auto-generated method stub
		envioDao.save(metodoEnvio);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		envioDao.deleteById(id);
	}

}
