package br.com.cupom.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @SequenceGenerator(name="companySequence", sequenceName="COMPANY_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="companySequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "URI_SOCIAL_SOUL")
    private String uriSocialSoul;

    @Column(name = "URL_IMAGE_ICON")
    private String urlImageIcon;

    @Column(name = "NAME_COMPANY")
    private String nameCompany;

    @Column(name = "DATE_CREATED")
    private final Date dateCreated = new Date();

    @Column(name = "DATE_EXCLUDED")
    private Date dateExcluded;

    public Company() {
    }

    public Company(Long id) {
        this.id = id;
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

    public String getUriSocialSoul() {
        return uriSocialSoul;
    }

    public void setUriSocialSoul(String uriSocialSoul) {
        this.uriSocialSoul = uriSocialSoul;
    }

    public String getUrlImageIcon() {
        return urlImageIcon;
    }

    public void setUrlImageIcon(String urlImageIcon) {
        this.urlImageIcon = urlImageIcon;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateExcluded() {
        return dateExcluded;
    }

    public void setDateExcluded(Date dateExcluded) {
        this.dateExcluded = dateExcluded;
    }
}
