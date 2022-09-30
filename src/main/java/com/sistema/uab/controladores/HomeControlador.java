package com.sistema.uab.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.uab.modelo.noticia;
import com.sistema.uab.repositorios.noticiaRepositorio;

@Controller
@RequestMapping("")
public class HomeControlador {
	@Autowired
	private noticiaRepositorio NoticiaRepositorio;
	
	@GetMapping("")
	public ModelAndView verPaginaDeInicio(){
		
		List<noticia> ultimasNoticias = NoticiaRepositorio.findAll(PageRequest.of(0, 4,Sort.by("fechaPublicacion").descending())).toList();
			return new ModelAndView("index")
					.addObject("ultimasNoticias",ultimasNoticias);
	}
	
	@GetMapping("/noticias")
	public ModelAndView listarNoticias(@PageableDefault(sort= "fechaPublicacion",direction=Sort.Direction.DESC)Pageable pageable) {
		Page<noticia> Noticias = NoticiaRepositorio.findAll(pageable);
		return new ModelAndView("Noticias")
				.addObject("Noticias",Noticias);
		
	}

	@GetMapping("noticias/{id}")
	public ModelAndView mostrarDetallesDeNoticia(@PathVariable Integer id) {
		noticia Noticia = NoticiaRepositorio.getOne(id);
		return new ModelAndView("noticia").addObject("Noticia" , Noticia);
	}
}























