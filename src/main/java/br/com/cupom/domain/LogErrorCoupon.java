package br.com.cupom.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOG_ERROR_GENERATE_COUPON")
public class LogErrorCoupon {

    @Id
    @SequenceGenerator(name="LOG_ERROR_GENERATE_COUPON_SEQUENCE", sequenceName="LOG_ERROR_GENERATE_COUPON_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LOG_ERROR_GENERATE_COUPON_SEQUENCE")
    @Column(name = "ID")
    private Long id;
    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "DATE_CREATED")
    private Date dateCreated = new Date();
    @Column(name = "TYPE_ERROR")
    private String typeError;

    public LogErrorCoupon(Long companyId, Date dateCreated, String typeError) {
        this.companyId = companyId;
        this.dateCreated = dateCreated;
        this.typeError = typeError;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTypeError() {
        return typeError;
    }

    public void setTypeError(String typeError) {
        this.typeError = typeError;
    }
}
