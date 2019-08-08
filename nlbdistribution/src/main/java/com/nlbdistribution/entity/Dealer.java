/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlbdistribution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nlbdistribution.util.RegexPattern;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "dealer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dealer.findAll", query = "SELECT d FROM Dealer d")})
public class Dealer implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealerId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Dorder> dorderList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealerId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lotteryorder> lotteryorderList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fullname")
    @Pattern(regexp = "^([A-Z][a-z]*[.]?[\\s]?)*([A-Z][a-z]*)$", message = "Invalid Fullname")
    private String fullname;
    @Basic(optional = false)
    @Column(name = "nic")
    @RegexPattern(regexp = "^(([\\d]{9}[vVxX])|([\\d]{12}))$", message = "Invalid NIC")
    private String nic;
    @Lob
    @Column(name = "address")
    @RegexPattern(regexp = "^([\\w\\/\\-,\\s]{2,})$", message = "Invalid Address")
    private String address;
    @Column(name = "mobile")
    @RegexPattern(regexp = "^07\\d{8}$", message = "Invalid Mobilephone Number")
    private String mobile;
    @Column(name = "land")
    @RegexPattern (regexp = "^0\\d{9}$", message = "Invalid Landphone Number")
    private String land;
    @Column(name = "email")
    @Pattern (regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Invalid Email")
    private String email;
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private City cityId;
    @JoinColumn(name = "dealerstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dealerstatus dealerstatusId;
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Gender genderId;

    public Dealer() {
    }

    public Dealer(Integer id) {
        this.id = id;
    }

    public Dealer(Integer id, String fullname) {
        this.id = id;
        this.fullname = fullname;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public Dealerstatus getDealerstatusId() {
        return dealerstatusId;
    }

    public void setDealerstatusId(Dealerstatus dealerstatusId) {
        this.dealerstatusId = dealerstatusId;
    }

    public Gender getGenderId() {
        return genderId;
    }

    public void setGenderId(Gender genderId) {
        this.genderId = genderId;
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
        if (!(object instanceof Dealer)) {
            return false;
        }
        Dealer other = (Dealer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlbdistribution.entity.Dealer[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Lotteryorder> getLotteryorderList() {
        return lotteryorderList;
    }

    public void setLotteryorderList(List<Lotteryorder> lotteryorderList) {
        this.lotteryorderList = lotteryorderList;
    }

    @XmlTransient
    public List<Dorder> getDorderList() {
        return dorderList;
    }

    public void setDorderList(List<Dorder> dorderList) {
        this.dorderList = dorderList;
    }
    
}
