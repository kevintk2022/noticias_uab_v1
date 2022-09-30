package com.sistema.uab.excepciones;


public class almacenExcepcion extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public almacenExcepcion(String mensaje) {
		super(mensaje);

	}

	public almacenExcepcion(String mensaje, Throwable excepcion) {
		super(mensaje, excepcion);

	}

}
