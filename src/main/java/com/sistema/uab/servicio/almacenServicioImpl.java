package com.sistema.uab.servicio;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.sistema.uab.excepciones.FileNotFoundException;
import com.sistema.uab.excepciones.almacenExcepcion;

@Service
public class almacenServicioImpl implements almacenServicio {
	@Value("${storage.location}")
	private String storageLocation;

	@PostConstruct
	@Override
	public void iniciarAlmacenDeArchivos() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(Paths.get(storageLocation));
		} catch (IOException excepcion) {
			// TODO: handle exception
			throw new almacenExcepcion("Error al inicializar la ubicacion en el almacen de archivos");
		}
	}

	@Override
	public String almacenarArchivo(MultipartFile archivo) {
		// TODO Auto-generated method stub
		String nombreArchivo = archivo.getOriginalFilename();
		if (archivo.isEmpty()) {
			throw new almacenExcepcion("no se puede almacenar un archivo vacio");

		}
		try {
			InputStream inputStream = archivo.getInputStream();
			Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo),
					StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException excepcion) {
			// TODO: handle exception
			throw new almacenExcepcion("error al almacenar el archivo" + nombreArchivo, excepcion);
		}
		return nombreArchivo;

	}

	@Override
	public Path cargarArchivo(String nombreArchivo) {
		// TODO Auto-generated method stub
		
		return Paths.get(storageLocation).resolve(nombreArchivo);
	}

	@Override
	public Resource cargarComoRecurso(String nombreArchivo) {
		// TODO Auto-generated method stub
		try {
			Path archivo = cargarArchivo(nombreArchivo);
			Resource recurso= new UrlResource(archivo.toUri());
			if (recurso.exists() || recurso.isReadable()) {
				return recurso;
				
			}else {
				throw new FileNotFoundException("No se pudo encontrar el archivo"+ nombreArchivo);

			}
		} catch (MalformedURLException excepcion) {
			// TODO: handle exception
			throw new FileNotFoundException("no se pudo encontrar el archivo",excepcion);
		}
		
	}

	@Override
	public void eliminarArchivo(String nombreArchivo) {
		// TODO Auto-generated method stub
		Path archivo = cargarArchivo(nombreArchivo);
		try {
			FileSystemUtils.deleteRecursively(archivo);
			
		} catch (Exception excepcion) {
			// TODO: handle exception
			System.out.println(excepcion);
			
		}

	}

}
