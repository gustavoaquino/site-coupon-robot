package br.com.cupom.service;


import br.com.cupom.domain.Coupon;
import br.com.cupom.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public int findQtdCoupon(){
        return couponRepository.getCountCoupon();
    }
    public List<Coupon> findAllCouponByCompanyIdAndDateExpiredMoreThanNow(Long companyId){
        return this.couponRepository.findAllByCompanyIdWhereDateExpiredIsMoreThanNow(companyId);
    }

    public void saveCouponList(List<Coupon> couponList){
        this.couponRepository.saveAll(couponList);
    }

    public List<Coupon> findAllCouponPrincipalPage(){
        return this.couponRepository.findAllCouponPrincipalPage();
    }

}
