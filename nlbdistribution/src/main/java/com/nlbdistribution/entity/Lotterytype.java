/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlbdistribution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nlbdistribution.util.RegexPattern;

import java.io.Serializable;
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
@Table(name = "lotterytype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotterytype.findAll", query = "SELECT l FROM Lotterytype l")})
public class Lotterytype implements Serializable {

    @Column(name = "digitformat")
    @RegexPattern(regexp ="^[a-zA-Z0-9]{4,10}$", message = "Invalid Digit Format")
    private String digitformat;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lotterytypeId", fetch = FetchType.LAZY)
    private List<Lotterytypedigitoption> lotterytypedigitoptionList;
    @JoinColumn(name = "lotterytypestatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lotterytypestatus lotterytypestatusId;
    @JoinColumn(name = "digitsize_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Digitsize digitsizeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lotterytypeId", fetch = FetchType.LAZY)
    private List<Prize> prizeList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid Lottery Type")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lotterytypeId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lottery> lotteryList;

    public Lotterytype() {
    }

    public Lotterytype(Integer id) {
        this.id = id;
    }

    public Lotterytype(Integer id,String name) {
        this.id = id;
        this.name=name;
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

    @XmlTransient
    public List<Lottery> getLotteryList() {
        return lotteryList;
    }

    public void setLotteryList(List<Lottery> lotteryList) {
        this.lotteryList = lotteryList;
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
        if (!(object instanceof Lotterytype)) {
            return false;
        }
        Lotterytype other = (Lotterytype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlbdistribution.entity.Lotterytype[ id=" + id + " ]";
    }

    public String getDigitformat() {
        return digitformat;
    }

    public void setDigitformat(String digitformat) {
        this.digitformat = digitformat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public List<Lotterytypedigitoption> getLotterytypedigitoptionList() {
        return lotterytypedigitoptionList;
    }

    public void setLotterytypedigitoptionList(List<Lotterytypedigitoption> lotterytypedigitoptionList) {
        this.lotterytypedigitoptionList = lotterytypedigitoptionList;
    }

    public Lotterytypestatus getLotterytypestatusId() {
        return lotterytypestatusId;
    }

    public void setLotterytypestatusId(Lotterytypestatus lotterytypestatusId) {
        this.lotterytypestatusId = lotterytypestatusId;
    }

    public Digitsize getDigitsizeId() {
        return digitsizeId;
    }

    public void setDigitsizeId(Digitsize digitsizeId) {
        this.digitsizeId = digitsizeId;
    }

    @XmlTransient
    public List<Prize> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<Prize> prizeList) {
        this.prizeList = prizeList;
    }
    
}
