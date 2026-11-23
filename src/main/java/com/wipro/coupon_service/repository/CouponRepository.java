package com.wipro.coupon_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.coupon_service.entity.Coupon;


public interface CouponRepository extends JpaRepository<Coupon, Integer> 
{
Coupon findByCouponCode(String couponCode);
}
