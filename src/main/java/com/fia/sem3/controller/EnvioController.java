package com.fia.sem3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fia.sem3.Entity.MetodoEnvio;
import com.fia.sem3.Entity.Usuario;
import com.fia.sem3.services.IMetodoEnvioService;
import com.fia.sem3.services.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/envios")
public class EnvioController {
	
	
	
	@Autowired
	private IMetodoEnvioService envioService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@GetMapping("")
	public String show (Model model, HttpSession session) {
		
		model.addAttribute("sesion",session.getAttribute("idusuario"));
		
		model.addAttribute("envios", envioService.findAll());
		if (session.getAttribute("idusuario") != null) {
	        Optional<Usuario> usuarioOptional = usuarioService.findById((Integer) session.getAttribute("idusuario"));
	        if (usuarioOptional.isPresent()) {
	            Usuario usuario = usuarioOptional.get();
	            model.addAttribute("usuario", usuario);
	        }
	    }
		
		return "/envio/show";
	}
	
	@GetMapping("/form")
	public String create(Model model, HttpSession session) {
		
		MetodoEnvio metodoEnvio = new MetodoEnvio();
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		model.addAttribute("metodoEnvio", metodoEnvio);
		if (session.getAttribute("idusuario") != null) {
	        Optional<Usuario> usuarioOptional = usuarioService.findById((Integer) session.getAttribute("idusuario"));
	        if (usuarioOptional.isPresent()) {
	            Usuario usuario = usuarioOptional.get();
	            model.addAttribute("usuario", usuario);
	        }
	    }
		return "envio/form";
	}
	@PostMapping("/save")
	public String save(MetodoEnvio metodoEnvio, Model model, HttpSession session)throws Exception{
		
		model.addAttribute("sesion",session.getAttribute("idusuario"));
		
		envioService.save(metodoEnvio);
		
		return "redirect:/envios";
	}
	
}
