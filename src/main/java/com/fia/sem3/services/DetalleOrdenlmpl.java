package com.fia.sem3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fia.sem3.DAO.DetalleOrdenDao;
import com.fia.sem3.Entity.DetalleOrden;
@Service
public class DetalleOrdenlmpl implements IDetalleService {
	
	@Autowired
	private DetalleOrdenDao detalleOrdenDao;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		// TODO Auto-generated method stub
		return detalleOrdenDao.save(detalleOrden);
	}
	
}
