/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlbdistribution.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Suranga
 */
@Entity
@Table(name = "sessionlog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sessionlog.findAll", query = "SELECT s FROM Sessionlog s")})
public class Sessionlog implements Serializable {

    @Column(name = "logintime")

    private LocalDate logintime;
    @Column(name = "logouttime")

    private LocalDate logouttime;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "token")
    private String token;
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;
    @JoinColumn(name = "sessionstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Sessionstatus sessionstatusId;

    public Sessionlog() {
    }

    public Sessionlog(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Sessionstatus getSessionstatusId() {
        return sessionstatusId;
    }

    public void setSessionstatusId(Sessionstatus sessionstatusId) {
        this.sessionstatusId = sessionstatusId;
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
        if (!(object instanceof Sessionlog)) {
            return false;
        }
        Sessionlog other = (Sessionlog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sessionlog[ id=" + id + " ]";
    }

    public LocalDate getLogintime() {
        return logintime;
    }

    public void setLogintime(LocalDate logintime) {
        this.logintime = logintime;
    }

    public LocalDate getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(LocalDate logouttime) {
        this.logouttime = logouttime;
    }
    
}
