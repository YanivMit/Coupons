package com.example.CouponsExample.Exceptions.Coupon;


public class CouponTitleException extends CouponSystemException {
	private static final long serialVersionUID = 1L;

	public CouponTitleException() {
	}

	public CouponTitleException(String message) {
		super(message);
	}

	public CouponTitleException(Throwable cause) {
		super(cause);
	}

	public CouponTitleException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponTitleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
