package com.example.CouponsExample.Exceptions.Customer;

public class CustomerDoesNotExist extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomerDoesNotExist() {
	}

	public CustomerDoesNotExist(String message) {
		super(message);
	}

	public CustomerDoesNotExist(Throwable cause) {
		super(cause);
	}

	public CustomerDoesNotExist(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerDoesNotExist(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}