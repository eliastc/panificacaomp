package com.mpsuporteti.mpvendas.services.exceptions;

public class ObjectNaoEncontradoException extends RuntimeException {	
	private static final long serialVersionUID = 1L;
	
	public ObjectNaoEncontradoException(String msg) {
		super(msg);
	}

	public ObjectNaoEncontradoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
