/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlbdistribution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "winningnumber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Winningnumber.findAll", query = "SELECT w FROM Winningnumber w")})
public class Winningnumber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "val")
    private String val;
    @JoinColumn(name = "winning_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Winning winningId;
    @JoinColumn(name = "lotterytypedigitoption_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lotterytypedigitoption lotterytypedigitoptionId;

    public Winningnumber() {
    }

    public Winningnumber(Integer id) {
        this.id = id;
    }

    public Winningnumber(Integer id, String val) {

        this.id = id;
        this.val = val;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Winning getWinningId() {
        return winningId;
    }

    public void setWinningId(Winning winningId) {
        this.winningId = winningId;
    }

    public Lotterytypedigitoption getLotterytypedigitoptionId() {
        return lotterytypedigitoptionId;
    }

    public void setLotterytypedigitoptionId(Lotterytypedigitoption lotterytypedigitoptionId) {
        this.lotterytypedigitoptionId = lotterytypedigitoptionId;
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
        if (!(object instanceof Winningnumber)) {
            return false;
        }
        Winningnumber other = (Winningnumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlbdistribution.entity.Winningnumber[ id=" + id + " ]";
    }
    
}
