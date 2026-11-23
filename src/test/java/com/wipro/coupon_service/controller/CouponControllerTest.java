package com.wipro.coupon_service.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.coupon_service.entity.Coupon;
import com.wipro.coupon_service.service.CouponService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @MockitoBean
    private CouponService couponService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSaveCoupon() throws Exception {

        Coupon coupon = new Coupon();
        coupon.setCouponId(1);
        coupon.setCouponCode("NEW10");
        coupon.setDiscount(10.0);

        when(couponService.saveCoupon(any(Coupon.class)))
                .thenReturn(coupon);

        mockMvc.perform(post("/api/v1/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coupon)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.couponId").value(1))
                .andExpect(jsonPath("$.couponCode").value("NEW10"))
                .andExpect(jsonPath("$.discount").value(10.0));
    }

    @Test
    void testGetCouponById() throws Exception {

        Coupon coupon = new Coupon();
        coupon.setCouponId(1);
        coupon.setCouponCode("NEW10");
        coupon.setDiscount(10.0);

        when(couponService.findByCouponId(1))
                .thenReturn(coupon);

        mockMvc.perform(get("/api/v1/coupon/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.couponId").value(1))
                .andExpect(jsonPath("$.couponCode").value("NEW10"))
                .andExpect(jsonPath("$.discount").value(10.0));
    }

    @Test
    void testGetAllCoupons() throws Exception {

        Coupon c1 = new Coupon();
        c1.setCouponId(1);
        c1.setCouponCode("NEW10");
        c1.setDiscount(10.0);

        Coupon c2 = new Coupon();
        c2.setCouponId(2);
        c2.setCouponCode("SAVE20");
        c2.setDiscount(20.0);

        List<Coupon> coupons = Arrays.asList(c1, c2);

        when(couponService.findAll())
                .thenReturn(coupons);

        mockMvc.perform(get("/api/v1/coupon")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].couponId").value(1))
                .andExpect(jsonPath("$[0].couponCode").value("NEW10"))
                .andExpect(jsonPath("$[0].discount").value(10.0))

                .andExpect(jsonPath("$[1].couponId").value(2))
                .andExpect(jsonPath("$[1].couponCode").value("SAVE20"))
                .andExpect(jsonPath("$[1].discount").value(20.0));
    }
}