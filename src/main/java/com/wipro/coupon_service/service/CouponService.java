package com.wipro.coupon_service.service;

import java.util.List;


import com.wipro.coupon_service.entity.Coupon;

public interface CouponService{
	Coupon saveCoupon(Coupon coupon);
	Coupon  findByCouponId(int couponId);
	List<Coupon > findAll();
	Coupon findByCouponCode(String couponCode);
	Coupon  updateCoupon (int couponId,  Coupon coupon);
	void deleteCoupon (int couponId);
}
