package com.wipro.coupon_service.exception;


public class CouponException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2537679600833460990L;
	
	public CouponException(String message)
	{
		super(message);
	}
@Override
public String toString()
{
	return "CouponID is not found";
}
}
