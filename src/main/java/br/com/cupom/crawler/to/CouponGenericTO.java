package br.com.cupom.crawler.to;


import lombok.Data;

import java.util.Date;

@Data
public class CouponGenericTO {

    private String description;
    private Date dateExpiration;
    private String positionXpathLink;
    private String linkCoupon;
    private String codeCoupon;
    private Long companyId;
    public CouponGenericTO(String description, Date dateExpiration, String linkCoupon, String codeCoupon, Long companyId) {
        this.description = description;
        this.dateExpiration = dateExpiration;
        this.linkCoupon = linkCoupon;
        this.codeCoupon = codeCoupon;
        this.companyId = companyId;
    }
}
