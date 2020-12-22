package com.example.CouponsExample.Exceptions.Company;

public class CompanyEmailDuplicate extends Exception {
	private static final long serialVersionUID = 1L;

	public CompanyEmailDuplicate() {
		super();
	}

	public CompanyEmailDuplicate(String message) {
		super(message);
	}

	public CompanyEmailDuplicate(Throwable cause) {
		super(cause);
	}

	public CompanyEmailDuplicate(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyEmailDuplicate(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
