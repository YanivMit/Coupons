package com.example.CouponsExample.Exceptions.Company;

public class CompanyException extends Exception {

	private static final long serialVersionUID = 1812100737891484201L;

	public CompanyException() {
	}

	public CompanyException(String message) {
		super(message);
	}

	public CompanyException(Throwable cause) {
		super(cause);
	}

	public CompanyException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
