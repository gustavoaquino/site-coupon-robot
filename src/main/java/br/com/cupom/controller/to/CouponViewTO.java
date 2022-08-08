package br.com.cupom.controller.to;


import br.com.cupom.domain.Company;
import br.com.cupom.domain.Coupon;
import br.com.cupom.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class CouponViewTO {

    private Long id;
    private String description;
    private String linkCoupon;
    private String codeCoupon;
    private String dateExpiration;
    private Company company;

    public CouponViewTO() {
    }

    public CouponViewTO(String description, String linkCoupon, String codeCoupon, String dateExpiration) {
        this.description = description;
        this.linkCoupon = linkCoupon;
        this.codeCoupon = codeCoupon;
        this.dateExpiration = dateExpiration;
    }

    private static CouponViewTO parseCoupon(Coupon coupon){

        final CouponViewTO couponViewTO = new CouponViewTO();

        couponViewTO.setId(coupon.getId());
        couponViewTO.setCodeCoupon(coupon.getCodeCoupon());
        couponViewTO.setLinkCoupon(coupon.getLinkCoupon());
        couponViewTO.setDescription(coupon.getDescription() + "...");
        couponViewTO.setDateExpiration(DateUtils.parseDateExpiration(coupon.getDateExpiration()));
        couponViewTO.setCompany(coupon.getCompany());

        return couponViewTO;
    }

    public static List<CouponViewTO> parseCouponList(List<Coupon> couponList){
        final List<CouponViewTO> couponViewTOList = new ArrayList<>(couponList.size());

        for(Coupon coupon : couponList){
            couponViewTOList.add(parseCoupon(coupon));
        }

        return couponViewTOList;
    }

    public static void setCompany(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkCoupon() {
        return linkCoupon;
    }

    public void setLinkCoupon(String linkCoupon) {
        this.linkCoupon = linkCoupon;
    }

    public String getCodeCoupon() {
        return codeCoupon;
    }

    public void setCodeCoupon(String codeCoupon) {
        this.codeCoupon = codeCoupon;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
