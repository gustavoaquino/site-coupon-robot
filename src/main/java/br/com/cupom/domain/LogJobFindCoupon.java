package br.com.cupom.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOG_JOB_FIND_COUPON")
public class LogJobFindCoupon {

    @Id
    @SequenceGenerator(name="LOG_JOB_FIND_COUPON_SEQUENCE", sequenceName="LOG_JOB_FIND_COUPON_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LOG_JOB_FIND_COUPON_SEQUENCE")
    @Column(name = "ID")
    private Long id;
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @Column(name = "MESSAGE")
    private String message;

    public LogJobFindCoupon(Date dateCreated, String message) {
        this.dateCreated = dateCreated;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
