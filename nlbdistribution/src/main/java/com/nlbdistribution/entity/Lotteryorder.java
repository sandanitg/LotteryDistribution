/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlbdistribution.entity;

import com.nlbdistribution.util.RegexPattern;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
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
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "lotteryorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotteryorder.findAll", query = "SELECT l FROM Lotteryorder l")})
public class Lotteryorder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")

    private LocalDateTime date;
    @Column(name = "quantity")
    @RegexPattern(regexp = "^\\d{2,7}0$", message = "Invalid Printcount")
    private Integer quantity;
    @Lob
    @Column(name = "slip")
    private byte[] slip;
    @Column(name = "branchcode")
    @Pattern(regexp = "^\\d{3}$", message = "Invalid Branch Code")
    private String branchcode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "paidamount")
    @RegexPattern(regexp ="^([\\d]{3,10})(([.][\\d]{2})?)$", message = "Invalid Paid Amount")
    private BigDecimal paidamount;
    @Column(name = "winningpayment")
    @RegexPattern(regexp ="^([\\d]{3,10})(([.][\\d]{2})?)$", message = "Invalid Winning Amount")
    private BigDecimal winningpayment;
    @Column(name = "totalpayment")
    private BigDecimal totalpayment;
    @Column(name = "paiddate")
    @Temporal(TemporalType.DATE)
    private Date paiddate;
    @Lob
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "confirmationmsg")
    private String confirmationmsg;
    @Column(name = "confirmationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmationdate;
    @JoinColumn(name = "draw_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Draw drawId;
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Dealer dealerId;
    @JoinColumn(name = "lotteryorderstatus_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lotteryorderstatus lotteryorderstatusId;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employeeId;

    public Lotteryorder() {
    }

    public Lotteryorder(Integer id) {
        this.id = id;
    }

    public Lotteryorder(Draw drawId, Dealer dealerId) {

        this.drawId = drawId;
        this.dealerId = dealerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public byte[] getSlip() {
        return slip;
    }

    public void setSlip(byte[] slip) {
        this.slip = slip;
    }

    public String getBranchcode() {
        return branchcode;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public BigDecimal getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(BigDecimal paidamount) {
        this.paidamount = paidamount;
    }

    public BigDecimal getWinningpayment() {
        return winningpayment;
    }

    public void setWinningpayment(BigDecimal winningpayment) {
        this.winningpayment = winningpayment;
    }

    public BigDecimal getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(BigDecimal totalpayment) {
        this.totalpayment = totalpayment;
    }

    public Date getPaiddate() {
        return paiddate;
    }

    public void setPaiddate(Date paiddate) {
        this.paiddate = paiddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfirmationmsg() {
        return confirmationmsg;
    }

    public void setConfirmationmsg(String confirmationmsg) {
        this.confirmationmsg = confirmationmsg;
    }

    public Date getConfirmationdate() {
        return confirmationdate;
    }

    public void setConfirmationdate(Date confirmationdate) {
        this.confirmationdate = confirmationdate;
    }

    public Draw getDrawId() {
        return drawId;
    }

    public void setDrawId(Draw drawId) {
        this.drawId = drawId;
    }

    public Dealer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Dealer dealerId) {
        this.dealerId = dealerId;
    }

    public Lotteryorderstatus getLotteryorderstatusId() {
        return lotteryorderstatusId;
    }

    public void setLotteryorderstatusId(Lotteryorderstatus lotteryorderstatusId) {
        this.lotteryorderstatusId = lotteryorderstatusId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
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
        if (!(object instanceof Lotteryorder)) {
            return false;
        }
        Lotteryorder other = (Lotteryorder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nlbdistribution.entity.Lotteryorder[ id=" + id + " ]";
    }
    
}
