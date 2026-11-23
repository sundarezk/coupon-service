package com.wipro.coupon_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.coupon_service.entity.Coupon;
import com.wipro.coupon_service.exception.CouponException;
import com.wipro.coupon_service.repository.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService {

    private CouponRepository couponRepository;

    public CouponServiceImpl(@Autowired CouponRepository couponRepository) {
        super();
        this.couponRepository = couponRepository;
    }

    @Override
    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon findByCouponId(int couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() ->
                        new CouponException("Coupon Not Found"));
    }

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon findByCouponCode(String couponCode) {
        return couponRepository.findByCouponCode(couponCode);
    }

    // Update Coupon
    @Override
    public Coupon updateCoupon(int couponId, Coupon coupon) {

        Coupon existingCoupon = couponRepository.findById(couponId)
                .orElseThrow(() ->
                        new CouponException("Coupon Not Found with Id: " + couponId));

        existingCoupon.setCouponCode(coupon.getCouponCode());
        existingCoupon.setDiscount(coupon.getDiscount());

        return couponRepository.save(existingCoupon);
    }

    // Delete Coupon
    @Override
    public void deleteCoupon(int couponId) {

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() ->
                        new CouponException("Coupon Not Found with Id: " + couponId));

        couponRepository.delete(coupon);
    }
}