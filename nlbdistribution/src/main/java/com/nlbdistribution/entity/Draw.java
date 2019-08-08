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
import java.time.LocalTime;
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
@Table(name = "draw")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Draw.findAll", query = "SELECT d FROM Draw d")})
public class Draw implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drawId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Dordedraw> dordedrawList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drawId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Winning> winningList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drawId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lotteryorder> lotteryorderList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "no")
    @RegexPattern(regexp = "^\\d{2,4}$", message = "Invalid Draw Number")
    private Integer no;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "time")
    //@Temporal(TemporalType.TIME)
    private LocalTime time;
    @Column(name = "printcount")
    @RegexPattern(regexp = "^\\d{2,8}0$", message = "Invalid Printcount")
    private Integer printcount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "superprice")
    @RegexPattern(regexp ="^([\\d]{8,15})(([.][\\d]{2})?)$", message = "Invalid SuperPrice")
    private BigDecimal superprice;
    @Lob
    @Column(name = "location")
    @Pattern(regexp = "^([A-Z][a-z]+)$", message = "Invalid Location")
    private String location;
    @Column(name = "createddate")
    @Temporal(TemporalType.DATE)
    private Date createddate;
    @JoinColumn(name = "drawstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Drawstatus drawstatusId;
    @JoinColumn(name = "lottery_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lottery lotteryId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employeeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drawId", fetch = FetchType.LAZY)

    private List<Drawsupervisor> drawsupervisorList;

    public Draw() {
    }

    public Draw(Integer id) {
        this.id = id;
    }

    public Draw(Integer id, Integer no,Lottery lotteryid) {

        this.id = id;
        this.no = no;
        this.lotteryId = lotteryid;
    }

    public Draw(Integer id, Integer no) {

        this.id = id;
        this.no = no;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getPrintcount() {
        return printcount;
    }

    public void setPrintcount(Integer printcount) {
        this.printcount = printcount;
    }

    public BigDecimal getSuperprice() {
        return superprice;
    }

    public void setSuperprice(BigDecimal superprice) {
        this.superprice = superprice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Drawstatus getDrawstatusId() {
        return drawstatusId;
    }

    public void setDrawstatusId(Drawstatus drawstatusId) {
        this.drawstatusId = drawstatusId;
    }

    public Lottery getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Lottery lotteryId) {
        this.lotteryId = lotteryId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @XmlTransient
    public List<Drawsupervisor> getDrawsupervisorList() {
        return drawsupervisorList;
    }

    public void setDrawsupervisorList(List<Drawsupervisor> drawsupervisorList) {
        this.drawsupervisorList = drawsupervisorList;
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
        if (!(object instanceof Draw)) {
            return false;
        }
        Draw other = (Draw) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlbdistribution.entity.Draw[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Lotteryorder> getLotteryorderList() {
        return lotteryorderList;
    }

    public void setLotteryorderList(List<Lotteryorder> lotteryorderList) {
        this.lotteryorderList = lotteryorderList;
    }

    @XmlTransient
    public List<Winning> getWinningList() {
        return winningList;
    }

    public void setWinningList(List<Winning> winningList) {
        this.winningList = winningList;
    }

    @XmlTransient
    public List<Dordedraw> getDordedrawList() {
        return dordedrawList;
    }

    public void setDordedrawList(List<Dordedraw> dordedrawList) {
        this.dordedrawList = dordedrawList;
    }
    
}
