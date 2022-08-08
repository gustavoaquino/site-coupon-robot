package br.com.cupom.domain;


import br.com.cupom.crawler.to.CouponGenericTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "COUPON")
public class Coupon {

    @Id
    @SequenceGenerator(name="couponSequence", sequenceName="COUPON_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="couponSequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID")
    private UUID uuid = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false, referencedColumnName = "id")
    private Company company; //company

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LINK_COUPON")
    private String linkCoupon;

    @Column(name = "CODE_COUPON")
    private String codeCoupon;

    @Column(name = "DATE_EXPIRATION")
    private Date dateExpiration;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "DATE_EXCLUDED")
    private Date dateExcluded;

    public Coupon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateExcluded() {
        return dateExcluded;
    }

    public void setDateExcluded(Date dateExcluded) {
        this.dateExcluded = dateExcluded;
    }

    public static Coupon parseCouponGenericTO(CouponGenericTO couponGenericTO, Company company){
        final Coupon coupon = new Coupon();
        coupon.setCompany(company);
        coupon.setDescription(couponGenericTO.getDescription());
        coupon.setDateExpiration(couponGenericTO.getDateExpiration());
        coupon.setLinkCoupon(couponGenericTO.getLinkCoupon());
        coupon.setCodeCoupon(couponGenericTO.getCodeCoupon());
        coupon.setDateCreated(new Date());
        return coupon;
    }

    public static List<Coupon> parseCouponGenericTOList(List<CouponGenericTO> couponGenericTOList, Company company){

        final List<Coupon> couponList = new ArrayList<>(couponGenericTOList.size());

        for(CouponGenericTO couponGenericTO : couponGenericTOList){
            couponList.add(parseCouponGenericTO(couponGenericTO, company));
        }

        return couponList;
    }

}
