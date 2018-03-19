/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meals.backend.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author CONG
 */
@Embeddable
public class CateringPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CateringDate")
    @Temporal(TemporalType.DATE)
    private Date cateringDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MealTimeId")
    private int mealTimeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "StaffId")
    private String staffId;

    public CateringPK() {
    }

    public CateringPK(Date cateringDate, int mealTimeId, String staffId) {
        this.cateringDate = cateringDate;
        this.mealTimeId = mealTimeId;
        this.staffId = staffId;
    }

    public Date getCateringDate() {
        return cateringDate;
    }

    public void setCateringDate(Date cateringDate) {
        this.cateringDate = cateringDate;
    }

    public int getMealTimeId() {
        return mealTimeId;
    }

    public void setMealTimeId(int mealTimeId) {
        this.mealTimeId = mealTimeId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cateringDate != null ? cateringDate.hashCode() : 0);
        hash += (int) mealTimeId;
        hash += (staffId != null ? staffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CateringPK)) {
            return false;
        }
        CateringPK other = (CateringPK) object;
        if ((this.cateringDate == null && other.cateringDate != null) || (this.cateringDate != null && !this.cateringDate.equals(other.cateringDate))) {
            return false;
        }
        if (this.mealTimeId != other.mealTimeId) {
            return false;
        }
        if ((this.staffId == null && other.staffId != null) || (this.staffId != null && !this.staffId.equals(other.staffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.meals.backend.model.CateringPK[ cateringDate=" + cateringDate + ", mealTimeId=" + mealTimeId + ", staffId=" + staffId + " ]";
    }
    
}
