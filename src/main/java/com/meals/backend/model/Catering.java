/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meals.backend.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG
 */
@Entity
@Table(name = "catering")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catering.findAll", query = "SELECT c FROM Catering c"),
    @NamedQuery(name = "Catering.findByStaffId", query = "SELECT c FROM Catering c WHERE c.cateringPK.staffId = :staffId"),
    @NamedQuery(name = "Catering.findByMealTimeCode", query = "SELECT c FROM Catering c WHERE c.cateringPK.mealTimeCode = :mealTimeCode"),
    @NamedQuery(name = "Catering.findByCateringDate", query = "SELECT c FROM Catering c WHERE c.cateringPK.cateringDate = :cateringDate"),
    @NamedQuery(name = "Catering.findByCateringTime", query = "SELECT c FROM Catering c WHERE c.cateringTime = :cateringTime"),
    @NamedQuery(name = "Catering.findByDeptId", query = "SELECT c FROM Catering c WHERE c.deptId = :deptId"),
    @NamedQuery(name = "Catering.findByLocationId", query = "SELECT c FROM Catering c WHERE c.locationId = :locationId"),
    @NamedQuery(name = "Catering.findByMealTypeCode", query = "SELECT c FROM Catering c WHERE c.mealTypeCode = :mealTypeCode"),
    @NamedQuery(name = "Catering.findByOrdered", query = "SELECT c FROM Catering c WHERE c.ordered = :ordered"),
    @NamedQuery(name = "Catering.findByStatus", query = "SELECT c FROM Catering c WHERE c.status = :status"),
    @NamedQuery(name = "Catering.findByCatered", query = "SELECT c FROM Catering c WHERE c.catered = :catered")})
public class Catering implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CateringPK cateringPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CateringTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cateringTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DeptId")
    private int deptId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LocationId")
    private int locationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "MealTypeCode")
    private String mealTypeCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ordered")
    private boolean ordered;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Catered")
    private boolean catered;

    public Catering() {
    }

    public Catering(CateringPK cateringPK) {
        this.cateringPK = cateringPK;
    }

    public Catering(CateringPK cateringPK, Date cateringTime, int deptId, int locationId, String mealTypeCode, boolean ordered, boolean status, boolean catered) {
        this.cateringPK = cateringPK;
        this.cateringTime = cateringTime;
        this.deptId = deptId;
        this.locationId = locationId;
        this.mealTypeCode = mealTypeCode;
        this.ordered = ordered;
        this.status = status;
        this.catered = catered;
    }

    public Catering(String staffId, String mealTimeCode, Date cateringDate) {
        this.cateringPK = new CateringPK(staffId, mealTimeCode, cateringDate);
    }

    public CateringPK getCateringPK() {
        return cateringPK;
    }

    public void setCateringPK(CateringPK cateringPK) {
        this.cateringPK = cateringPK;
    }

    public Date getCateringTime() {
        return cateringTime;
    }

    public void setCateringTime(Date cateringTime) {
        this.cateringTime = cateringTime;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getMealTypeCode() {
        return mealTypeCode;
    }

    public void setMealTypeCode(String mealTypeCode) {
        this.mealTypeCode = mealTypeCode;
    }

    public boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getCatered() {
        return catered;
    }

    public void setCatered(boolean catered) {
        this.catered = catered;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cateringPK != null ? cateringPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catering)) {
            return false;
        }
        Catering other = (Catering) object;
        if ((this.cateringPK == null && other.cateringPK != null) || (this.cateringPK != null && !this.cateringPK.equals(other.cateringPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.meals.backend.model.Catering[ cateringPK=" + cateringPK + " ]";
    }
    
}
