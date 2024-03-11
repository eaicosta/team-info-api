package com.devdavicosta.teaminfoapi.services.exceptions;

public class SameIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SameIdException() {
		super("Cannot add the same ids.");
	}
}
