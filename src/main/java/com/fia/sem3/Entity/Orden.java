package com.fia.sem3.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenes")
public class Orden implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String numero;
	private Date fecha;
	private Double total;

	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "orden", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<DetalleOrden> detalle;

	public Orden() {
		
	}

	public Orden(Integer id, String numero, Date fecha, Double total) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.total = total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleOrden> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleOrden> detalle) {
		this.detalle = detalle;
	}

}
