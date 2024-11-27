package com.fia.sem3.Entity;

public class ShoeEntity {
	private int id;
    private String marca;
    private String modelo;
    private double precio;
	public ShoeEntity(int id, String marca, String modelo, double precio) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
    
}
