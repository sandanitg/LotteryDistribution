/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlbdistribution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nlbdistribution.util.RegexPattern;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "lottery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lottery.findAll", query = "SELECT l FROM Lottery l")})
public class Lottery implements Serializable {

    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "dointroduced")
    @Temporal(TemporalType.DATE)
    private Date dointroduced;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lotteryId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Draw> drawList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pricesale")
    @RegexPattern(regexp ="^([\\d]{2,5})(([.][\\d]{2})?)$", message = "Invalid PriceSale")
    private BigDecimal pricesale;
    @Column(name = "pricedistribution")
    @RegexPattern(regexp = "^([\\d]{2,5})(([.][\\d]{2})?)$", message = "Invalid PriceDistribution")
    private BigDecimal pricedistribution;
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "lotterytype_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lotterytype lotterytypeId;
    @JoinColumn(name = "digittype_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Digittype digittypeId;
    @JoinColumn(name = "lotterystatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lotterystatus lotterystatusId;
    @JoinColumn(name = "lotterycolor_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lotterycolor lotterycolorId;
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Day dayId;

    public Lottery() {
    }

    public Lottery(Integer id) {
        this.id = id;
    }

    public Lottery(Integer id,String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getPricesale() {
        return pricesale;
    }

    public void setPricesale(BigDecimal pricesale) {
        this.pricesale = pricesale;
    }

    public BigDecimal getPricedistribution() {
        return pricedistribution;
    }

    public void setPricedistribution(BigDecimal pricedistribution) {
        this.pricedistribution = pricedistribution;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lotterytype getLotterytypeId() {
        return lotterytypeId;
    }

    public void setLotterytypeId(Lotterytype lotterytypeId) {
        this.lotterytypeId = lotterytypeId;
    }

    public Digittype getDigittypeId() {
        return digittypeId;
    }

    public void setDigittypeId(Digittype digittypeId) {
        this.digittypeId = digittypeId;
    }

    public Lotterystatus getLotterystatusId() {
        return lotterystatusId;
    }

    public void setLotterystatusId(Lotterystatus lotterystatusId) {
        this.lotterystatusId = lotterystatusId;
    }

    public Lotterycolor getLotterycolorId() {
        return lotterycolorId;
    }

    public void setLotterycolorId(Lotterycolor lotterycolorId) {
        this.lotterycolorId = lotterycolorId;
    }

    public Day getDayId() {
        return dayId;
    }

    public void setDayId(Day dayId) {
        this.dayId = dayId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lottery)) {
            return false;
        }
        Lottery other = (Lottery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlbdistribution.entity.Lottery[ id=" + id + " ]";
    }


    public Date getDointroduced() {
        return dointroduced;
    }

    public void setDointroduced(Date dointroduced) {
        this.dointroduced = dointroduced;
    }

    @XmlTransient
    public List<Draw> getDrawList() {
        return drawList;
    }

    public void setDrawList(List<Draw> drawList) {
        this.drawList = drawList;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
}
