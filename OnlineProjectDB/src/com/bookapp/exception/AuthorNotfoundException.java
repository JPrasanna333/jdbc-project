package com.bookapp.exception;

public class AuthorNotfoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AuthorNotfoundException() {
		super();
	}

	public AuthorNotfoundException(String message) {
		super(message);
	}

}
