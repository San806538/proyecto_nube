package com.fia.sem3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fia.sem3.Entity.Orden;
import com.fia.sem3.Entity.Usuario;
import com.fia.sem3.services.IOrdenService;
import com.fia.sem3.services.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrdenService ordenService;
	
	Orden orden=new Orden();
	
	
	@GetMapping("/listar")
	public String listar (Model model) {
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		return "usuario/listar";
	
	}
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	@PostMapping("/save")
	public String save(Usuario usuario, HttpSession session,Model model, RedirectAttributes flash) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		String mensajeFlash = "Registro Completado";
		usuario.setTipo("USER");
		usuarioService.save(usuario);
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/";
	}
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
		if(user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			if(user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
				
			}else {
				return "redirect:/";
			}
		}
		return "redirect:/";
	}
	
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		
		if (session.getAttribute("idusuario") != null) {
	        Optional<Usuario> usuarioOptional = usuarioService.findById((Integer) session.getAttribute("idusuario"));
	        if (usuarioOptional.isPresent()) {
	            Usuario usuario = usuarioOptional.get();
	            model.addAttribute("usuario", usuario);
	        }
	    }
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		
		List<Orden> ordenes= ordenService.findByUsuario(usuario);
		model.addAttribute("ordenes",ordenes);
		return "usuario/compras";
	}
	
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		
		Optional<Orden> pedido= ordenService.findById(id);
		model.addAttribute("detalles", pedido.get().getDetalle());
		
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return "usuario/detallecompra";
	}
	
	@GetMapping("/boleta/{id}")
	public String detalleBoleta (@PathVariable Integer id, HttpSession session, Model model) {
		
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		Usuario usuario= usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		model.addAttribute("usuario", usuario);
		
		List<Orden> ordenes =ordenService.findByUsuario(usuario);
		
		Orden orden=ordenService.findOne(id);
		model.addAttribute("ordenes", ordenes);
		
		model.addAttribute("orden", orden);
		Optional<Orden> pedido= ordenService.findById(id);
		model.addAttribute("orden", orden);
		model.addAttribute("detalles", pedido.get().getDetalle());
		
		return "usuario/boletaVenta";
	}
	
	
	
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}
}
