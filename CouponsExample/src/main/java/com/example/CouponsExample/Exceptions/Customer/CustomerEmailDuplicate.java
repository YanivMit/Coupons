package com.example.CouponsExample.Exceptions.Customer;

public class CustomerEmailDuplicate extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomerEmailDuplicate() {
	}

	public CustomerEmailDuplicate(String message) {
		super(message);
	}

	public CustomerEmailDuplicate(Throwable cause) {
		super(cause);
	}

	public CustomerEmailDuplicate(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerEmailDuplicate(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}