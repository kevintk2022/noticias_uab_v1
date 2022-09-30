package com.sistema.uab.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.uab.modelo.genero;
import com.sistema.uab.modelo.noticia;
import com.sistema.uab.repositorios.GeneroRepositorio;
import com.sistema.uab.repositorios.noticiaRepositorio;
import com.sistema.uab.servicio.almacenServicioImpl;



@Controller
@RequestMapping("/admin")

public class AdminControlador {
	
	
	@Autowired
	private noticiaRepositorio NoticiaRepositorio;
	
	@Autowired
	private GeneroRepositorio generoRepositorio;
	
	@Autowired
	private almacenServicioImpl servicio;
	
	
	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo",size=5)Pageable pageable)  {
		
		Page<noticia> Noticias =NoticiaRepositorio.findAll(pageable);
		return new ModelAndView("admin/index").addObject("Noticias",Noticias);
	}
	@GetMapping("/Noticias/nuevo")
	public ModelAndView mostrarFormularioDeNuevaNoticia(){
		List<genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
		return new ModelAndView("admin/nueva-Noticia")
					.addObject("Noticia",new noticia())
					.addObject("generos",generos);
				
	}
	@PostMapping("/Noticias/nuevo")
	public ModelAndView registrarNoticia(@Validated noticia Noticia,BindingResult bindingResult) {
		if (bindingResult.hasErrors()|| Noticia.getPortada().isEmpty()) {
			if (Noticia.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada", "MultipartNotEmpty");
				
			}
			List<genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
			return new ModelAndView("admin/nueva-Noticia")
					.addObject("Noticia",Noticia)
					.addObject("generos",generos);			
			
		}

		
	String rutaPortada = servicio.almacenarArchivo(Noticia.getPortada());
	Noticia.setRutaPortada(rutaPortada);
	
	NoticiaRepositorio.save(Noticia);
	return new ModelAndView("redirect:/admin");
	
	}

@GetMapping("/Noticias/{id}/editar")
public ModelAndView editarNoticia(@PathVariable Integer id) {
	noticia Noticia= NoticiaRepositorio.getOne(id);
	List<genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
	return new ModelAndView("admin/editar-Noticia")
			.addObject("Noticia",Noticia)
			.addObject("generos",generos);
	
	
	
}
@PostMapping("/Noticias/{id}/editar")
public ModelAndView actualizarNoticia(@PathVariable Integer id,@Validated noticia Noticia,BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
		
		List<genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
		return new ModelAndView("admin/editar-Noticia")
				.addObject("Noticia",Noticia)
				.addObject("generos",generos);
	}
	noticia noticiaBD = NoticiaRepositorio.getOne(id);
	noticiaBD.setTitulo(Noticia.getTitulo());
	noticiaBD.setContexto(Noticia.getContexto());
	noticiaBD.setFechaPublicacion(Noticia.getFechaPublicacion());
	noticiaBD.setYoutubeVideoId(Noticia.getYoutubeVideoId());
	noticiaBD.setGeneros(Noticia.getGeneros());
		if (!Noticia.getPortada().isEmpty()) {
			servicio.eliminarArchivo(noticiaBD.getRutaPortada());
			String rutaPortada = servicio.almacenarArchivo(Noticia.getPortada());
			noticiaBD.setRutaPortada(rutaPortada);
			
			
		}
		NoticiaRepositorio.save(noticiaBD);
		return new ModelAndView("redirect:/admin");
	
	
}


@PostMapping("/Noticias/{id}/eliminar")
public String eliminarNoticia(@PathVariable Integer id) {
	noticia Noticia= NoticiaRepositorio.getOne(id);
	NoticiaRepositorio.delete(Noticia);
	servicio.eliminarArchivo(Noticia.getRutaPortada());
	return "redirect:/admin";
}
}







































