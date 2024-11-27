package com.fia.sem3.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fia.sem3.Entity.Orden;
import com.fia.sem3.Entity.Producto;
import com.fia.sem3.Entity.Usuario;
import com.fia.sem3.services.IMetodoEnvioService;
import com.fia.sem3.services.IOrdenService;
import com.fia.sem3.services.IProductoService;
import com.fia.sem3.services.IUsuarioService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IOrdenService ordenService;
	
	@Autowired
	private IMetodoEnvioService envioService;
	
	@GetMapping("")
	public String home(Model model) {
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		
		return "administrador/home";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	// Obtener los pedidos
	
	@GetMapping("/pedidos/{id}")
	
	public String pedidos(@PathVariable Integer id, Model model) {
		
		Usuario usuario= usuarioService.findOne(id);
		List<Orden>pedidos= usuario.getOrdenes();
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("ordenes", ordenService.findAll());	
		
		return "/administrador/pedidos";
	}
	
	@GetMapping("/detalle/{id}")
	
	public String detalle(Model model,@PathVariable Integer id) {
		// Obtener la el pedido x id
		Orden orden=ordenService.findById(id).get();
		// Enviar los detalles a la vista
		model.addAttribute("detalles", orden.getDetalle());
		
		return "administrador/detalleorden";
	}
	
	@GetMapping("/envios")
	public String envios(Model model, HttpSession session) {
	
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		model.addAttribute("envios", envioService.findAll());
		
		return "/administrador/fenvios";
	}
	
	
	
	
	
	
	
	
	
}
