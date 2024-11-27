package com.fia.sem3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fia.sem3.Entity.Producto;
import com.fia.sem3.services.IProductoService;
import com.fia.sem3.services.UploadFileService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private IProductoService productoService;
	@Autowired
	private UploadFileService upload;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "/producto/show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Producto producto =new Producto();
		model.addAttribute("producto", producto);
		return "/producto/create";
	}
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("img") MultipartFile file, Model model)
			throws Exception {
		
		if(producto.getId()==null) {
			String nombreImagen =upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		productoService.save(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Producto producto =new Producto();
		Optional<Producto>optionalProducto = productoService.get(id);
		producto = optionalProducto.get();
		model.addAttribute("producto",producto);
		
		return "producto/edit";
		
	}
	
	@PostMapping("/update")
	public String update(Producto producto,@RequestParam("img") MultipartFile file) throws Exception{
	
		Producto p=new Producto();
		p = productoService.get(producto.getId()).get();
		if(file.isEmpty()){
		producto.setImagen(p.getImagen());
		}else {
			if(!p.getImagen().equals("default.jpg")) {
				upload.deleteImagen(p.getImagen());
			}
			String nombreImgen = upload.saveImage(file);
			producto.setImagen(nombreImgen);
		}
		productoService.update(producto);
		return "redirect:/productos";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model) {
		
		try {
			Producto p=new Producto();
			p=productoService.get(id).get();
			if(!p.getImagen().equals("default.jpg")) {
				upload.deleteImagen(p.getImagen());
			}
			productoService.delete(id);
			return "redirect:/productos";
		
		}catch(Exception e) {
			
			return "redirect:/productos";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
