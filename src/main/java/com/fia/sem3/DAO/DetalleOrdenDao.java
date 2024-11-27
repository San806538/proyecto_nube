package com.fia.sem3.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fia.sem3.Entity.DetalleOrden;
@Repository
public interface DetalleOrdenDao  extends JpaRepository<DetalleOrden, Integer>{

}
