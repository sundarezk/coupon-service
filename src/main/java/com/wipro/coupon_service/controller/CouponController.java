package com.wipro.coupon_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.coupon_service.entity.Coupon;
import com.wipro.coupon_service.service.CouponService;

@RestController
@RequestMapping("api/v1/coupon")
public class CouponController {

    private CouponService couponService;

    public CouponController(@Autowired CouponService couponService) {
        this.couponService = couponService;
    }

    // Insert Coupon
    @PostMapping
    public ResponseEntity<Coupon> saveCoupon(@RequestBody Coupon coupon) {

        return new ResponseEntity<Coupon>(
                couponService.saveCoupon(coupon),
                HttpStatus.CREATED);
    }

    // Display Coupon Details By Id
    @GetMapping("/{couponId}")
    public ResponseEntity<Coupon> getCouponById(
            @PathVariable int couponId) {

        return new ResponseEntity<Coupon>(
                couponService.findByCouponId(couponId),
                HttpStatus.OK);
    }

    // Display All Coupons
    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupon() {

        return new ResponseEntity<List<Coupon>>(
                couponService.findAll(),
                HttpStatus.OK);
    }

    // Display Coupon By Code
    @GetMapping("/code/{couponCode}")
    public ResponseEntity<Coupon> getCouponByCode(
            @PathVariable String couponCode) {
   	 System.out.println("Response from 8092");
        return new ResponseEntity<Coupon>(
                couponService.findByCouponCode(couponCode),
                HttpStatus.OK);
    }

    // Update Coupon
    @PutMapping("/{couponId}")
    public ResponseEntity<Coupon> updateCoupon(
            @PathVariable int couponId,
            @RequestBody Coupon coupon) {

        return new ResponseEntity<Coupon>(
                couponService.updateCoupon(couponId, coupon),
                HttpStatus.OK);
    }

    // Delete Coupon
    @DeleteMapping("/{couponId}")
    public ResponseEntity<String> deleteCoupon(
            @PathVariable int couponId) {

        couponService.deleteCoupon(couponId);

        return new ResponseEntity<>(
                "Coupon Deleted Successfully",
                HttpStatus.OK);
    }
}