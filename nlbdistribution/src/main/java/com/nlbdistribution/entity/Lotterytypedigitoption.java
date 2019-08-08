/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlbdistribution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "lotterytypedigitoption")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotterytypedigitoption.findAll", query = "SELECT l FROM Lotterytypedigitoption l")})
public class Lotterytypedigitoption implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lotterytypedigitoptionId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Winningnumber> winningnumberList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "lotterytype_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Lotterytype lotterytypeId;
    @JoinColumn(name = "digitoption_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Digitoption digitoptionId;

    public Lotterytypedigitoption() {
    }

    public Lotterytypedigitoption(Integer id) {
        this.id = id;
    }

    public Lotterytypedigitoption(Integer id,Digitoption digitoptionId) {
        this.id = id;
        this.digitoptionId = digitoptionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lotterytype getLotterytypeId() {
        return lotterytypeId;
    }

    public void setLotterytypeId(Lotterytype lotterytypeId) {
        this.lotterytypeId = lotterytypeId;
    }

    public Digitoption getDigitoptionId() {
        return digitoptionId;
    }

    public void setDigitoptionId(Digitoption digitoptionId) {
        this.digitoptionId = digitoptionId;
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
        if (!(object instanceof Lotterytypedigitoption)) {
            return false;
        }
        Lotterytypedigitoption other = (Lotterytypedigitoption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlbdistribution.entity.Lotterytypedigitoption[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Winningnumber> getWinningnumberList() {
        return winningnumberList;
    }

    public void setWinningnumberList(List<Winningnumber> winningnumberList) {
        this.winningnumberList = winningnumberList;
    }
    
}
