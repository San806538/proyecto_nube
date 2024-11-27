package com.fia.sem3.controller;
import com.fia.sem3.DAO.ShoeDAO;
import com.fia.sem3.Entity.DetalleOrden;
import com.fia.sem3.Entity.Orden;
import com.fia.sem3.Entity.Producto;
import com.fia.sem3.Entity.ShoeEntity;
import com.fia.sem3.Entity.Usuario;
import com.fia.sem3.services.IDetalleService;
import com.fia.sem3.services.IOrdenService;
import com.fia.sem3.services.IProductoService;
import com.fia.sem3.services.IUsuarioService;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/")
public class ShoeController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IOrdenService ordenService;
	
	@Autowired
	private IDetalleService detalleOrdenService;
	
	List<DetalleOrden> detalles=new ArrayList<DetalleOrden>();
	
	Orden orden =new Orden();
	
	
	
	
	
	private final ShoeDAO shoeDAO = new ShoeDAO();
    
    
    @GetMapping("")
	private String home(Model model, HttpSession session) {
    	
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		if (session.getAttribute("idusuario") != null) {
	        Optional<Usuario> usuarioOptional = usuarioService.findById((Integer) session.getAttribute("idusuario"));
	        if (usuarioOptional.isPresent()) {
	            Usuario usuario = usuarioOptional.get();
	            model.addAttribute("usuario", usuario);
	        }
	    }
		return "usuario/home";
	}
    
    @RequestMapping("/contactos")
    public String contactame(Model model) {
        return "contactos";
    }
    @RequestMapping("/contactosS")
    public String contactameS(Model model) {
        return "contactosS";
    }

    @GetMapping("/shoes")
    public String listShoes(Model model) {
        List<ShoeEntity> shoes = shoeDAO.getAllShoes();
        model.addAttribute("shoes", shoes);
        return "usuario/home";
    }

    @GetMapping("/shoes/search")
    public String searchShoesByBrand(Model model, @RequestParam("brand") String brand) {
        List<ShoeEntity> shoes = shoeDAO.getShoesByBrand(brand);
        model.addAttribute("shoes", shoes);
        return "shoeList";
    }
    @GetMapping("/adidas")
    public String adidas(Model model) {
    	List<ShoeEntity>adidas = shoeDAO.getShoesByBrand("adidas");
    	model.addAttribute("shoes",adidas);
    	return "adidas";
    }
    @GetMapping("/nikel")
    public String nikel(Model model) {
    	List<ShoeEntity>nikel = shoeDAO.getShoesByBrand("nikel");
    	model.addAttribute("shoes",nikel);
    	return "nikel";
    }
    @GetMapping("/Nort")
    public String nort(Model model) {
    	List<ShoeEntity>nort = shoeDAO.getShoesByBrand("Nort");
    	model.addAttribute("shoes",nort);
    	return "Nort";
    }

    @GetMapping("/robin")
    public String robin(Model model) {
    	List<ShoeEntity>robin = shoeDAO.getShoesByBrand("robin");
    	model.addAttribute("shoes",robin);
    	return "robin";
    }
    @GetMapping("/AIR")
    public String air(Model model) {
    	List<ShoeEntity>air = shoeDAO.getShoesByBrand("air");
    	model.addAttribute("shoes",air);
    	return "air";
    }
    @GetMapping("/puma")
    public String puma(Model model) {
    	List<ShoeEntity>puma = shoeDAO.getShoesByBrand("puma");
    	model.addAttribute("shoes",puma);
    	return "puma";
    }
    @GetMapping("/bask")
    public String bask(Model model) {
    	List<ShoeEntity>bask = shoeDAO.getShoesByBrand("bask");
    	model.addAttribute("shoes",bask);
    	return "bask";
    }
     
    @RequestMapping(value = "/checkout", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkout(String paymentMethod, Model model) {
        if ("BCP".equals(paymentMethod)) {
            // Lógica para procesar el pago con BCP
            model.addAttribute("message", "Pago exitoso con BCP");
        } else if ("BBVA".equals(paymentMethod)) {
            // Lógica para procesar el pago con BBVA
            model.addAttribute("message", "Pago exitoso con BBVA");
        } else if ("YAPE".equals(paymentMethod)) {
            // Lógica para procesar el pago con Yape
            model.addAttribute("message", "Pago exitoso con Yape");
        } else {
            // Método de pago no reconocido
            model.addAttribute("message", "Método de pago no reconocido");
        }
        
        return "checkoutConfirmation";
    }
    @GetMapping("/buscarMarca")
    public String zapatosPorMarca(@RequestParam("marca") String marca, Model model) {
        List<ShoeEntity> shoes = shoeDAO.getShoesByBrand(marca);
        model.addAttribute("marca", marca);
        model.addAttribute("shoes", shoes);
        return "zapatosPorMarca";
    }
    
    /* ========================================================== */
    @GetMapping("/producto")
	private String producto(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		model.addAttribute("productos", productoService.findAll());
		if (session.getAttribute("idusuario") != null) {
	        Optional<Usuario> usuarioOptional = usuarioService.findById((Integer) session.getAttribute("idusuario"));
	        if (usuarioOptional.isPresent()) {
	            Usuario usuario = usuarioOptional.get();
	            model.addAttribute("usuario", usuario);
	        }
	    }	
		return "usuario/producto";
		}
    
    @GetMapping("/productoHome/{id}")
	public String productoHome(@PathVariable Integer id, Model model, HttpSession session) {

		model.addAttribute("sesion", session.getAttribute("idusuario"));
		Producto producto=new Producto();
		Optional<Producto> produtoOptional = productoService.get(id);
		producto = produtoOptional.get();
		if (session.getAttribute("idusuario") != null) {
	        Optional<Usuario> usuarioOptional = usuarioService.findById((Integer) session.getAttribute("idusuario"));
	        if (usuarioOptional.isPresent()) {
	            Usuario usuario = usuarioOptional.get();
	            model.addAttribute("usuario", usuario);
	        }
	    }
		model.addAttribute("producto", producto);
		
		return "usuario/productoHome";
	}
    /* ========================================================== */
    @PostMapping("/carrito")
    public String addCarrito(@RequestParam Integer id, @RequestParam Integer cantidad,@RequestParam String medida, Model model,
    		HttpSession session) {
    	//name usuario
    	if (session.getAttribute("idusuario") != null) {
	        Optional<Usuario> usuarioOptional = usuarioService.findById((Integer) session.getAttribute("idusuario"));
	        if (usuarioOptional.isPresent()) {
	            Usuario usuario = usuarioOptional.get();
	            model.addAttribute("usuario", usuario);
	        }
	    }
    	
    	model.addAttribute("session", session.getAttribute("idusuario"));
    	DetalleOrden detalleOrden = new DetalleOrden();
    	Producto producto=new Producto();
    	double sumaTotal=0;
    	
    	// Obtener el producto
    	
    	Optional<Producto> productoOptional = productoService.get(id);
    	producto=productoOptional.get();
    	
    	detalleOrden.setCantidad(cantidad);// Seteamos la cantidad que enviamos
    	detalleOrden.setMedida(medida);
    	detalleOrden.setPrecio(producto.getPrecio());// Obtenemos el precio del producto
    	detalleOrden.setNombre(producto.getNombre());// Obtenemos el nombre
    	detalleOrden.setTalla(producto.getTalla());// Obtenemos el nombre
    	detalleOrden.setColor(producto.getColor());// Obtenemos el nombre
    	detalleOrden.setTotal(producto.getPrecio()*cantidad);// Obtenemos el nuevo precio
    	detalleOrden.setProducto(producto);// El producto en su foranea
    	
    	// Validacion de producto existente
    	
    	Integer idProducto= producto.getId();
    	boolean ingresado= detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
    	
    	if(!ingresado){
    		//Añadimos los detalles a la lista
    		detalles.add(detalleOrden);
    	}
    	//Metodo para calcular el total
    	sumaTotal= detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
    	
    	orden.setTotal(sumaTotal);// Seteamos el total en el campo total de orden
    	model.addAttribute("car", detalles);
    	model.addAttribute("orden", orden);
  
    	return "usuario/carrito";
    }
    	// Quitar producto del carrito
    	@GetMapping("/delete/carrito/{id}")
    	public String deleteProductoCarrito(@PathVariable Integer id, Model model, HttpSession session) {
    		
    		
    		model.addAttribute("sesion", session.getAttribute("idusuario"));
    		List<DetalleOrden> ordenesNueva=new ArrayList<DetalleOrden>();    		
    	// Si el id es diferente lo agrega
    		for(DetalleOrden detalleOrden: detalles) {
    			if(detalleOrden.getProducto().getId() != id) {
    				ordenesNueva.add(detalleOrden);
    			}
    		}
    		// Nueva lista
    		detalles = ordenesNueva;
    		
    		double sumaTotal=0;
    		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
    		
    		orden.setTotal(sumaTotal);
    		model.addAttribute("car", detalles);
    		model.addAttribute("orden", orden);
    		
    		
    		return "redirect:/getCarrito";
    	}
    	
    	// Metodo para ir a carrito
    	@GetMapping("/getCarrito")
    	public String getCarrito(Model model, HttpSession session) {
    		
    		model.addAttribute("car", detalles);
    		model.addAttribute("orden", orden);
    		
    		// Sesion
    		
    		model.addAttribute("sesion", session.getAttribute("idusuario"));
    		
    		
    		return "usuario/carrito";
    	}
    
    
    	
    	@GetMapping("/orden")
    	public String Orden(Model model, HttpSession session) {
    		
    		model.addAttribute("sesion", session.getAttribute("idusuario"));
    		Usuario usuario= usuarioService.findById
    				(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
    		
    		model.addAttribute("car",detalles);
    		model.addAttribute("orden",orden);
    		model.addAttribute("usuario",usuario);
    		
    		return "usuario/resumenorden";
    	}
    	
    	@GetMapping("/saveOrden")
    	public String saveOrden(HttpSession session){
    		Date fechaCreacion = new Date();
    		orden.setFecha(fechaCreacion);
    		orden.setNumero(ordenService.generarNumeroOrden());
    		//usuario
    		
    		Usuario usuario= usuarioService.findById
    				(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
    		
    		orden.setUsuario(usuario);
    		ordenService.save(orden);
    		
    		for(DetalleOrden dt: detalles) {
    			dt.setOrden(orden);
				detalleOrdenService.save(dt);
    		}
    		
    		// Limpiar la lista y la orden
    		
    		orden = new Orden();
    		detalles.clear();
    		return "redirect:/";
    	}
    
  
    
}
