/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meals.backend.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CONG
 */
@Entity
@Table(name = "mealtime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mealtime.findAll", query = "SELECT m FROM Mealtime m"),
    @NamedQuery(name = "Mealtime.findByMealTimeId", query = "SELECT m FROM Mealtime m WHERE m.mealTimeId = :mealTimeId"),
    @NamedQuery(name = "Mealtime.findByDescription", query = "SELECT m FROM Mealtime m WHERE m.description = :description"),
    @NamedQuery(name = "Mealtime.findByMealTimeName", query = "SELECT m FROM Mealtime m WHERE m.mealTimeName = :mealTimeName")})
public class Mealtime implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "MealTimeId")
    private Integer mealTimeId;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MealTimeName")
    private String mealTimeName;

    public Mealtime() {
    }

    public Mealtime(Integer mealTimeId) {
        this.mealTimeId = mealTimeId;
    }

    public Mealtime(Integer mealTimeId, String mealTimeName) {
        this.mealTimeId = mealTimeId;
        this.mealTimeName = mealTimeName;
    }

    public Integer getMealTimeId() {
        return mealTimeId;
    }

    public void setMealTimeId(Integer mealTimeId) {
        this.mealTimeId = mealTimeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMealTimeName() {
        return mealTimeName;
    }

    public void setMealTimeName(String mealTimeName) {
        this.mealTimeName = mealTimeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mealTimeId != null ? mealTimeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mealtime)) {
            return false;
        }
        Mealtime other = (Mealtime) object;
        if ((this.mealTimeId == null && other.mealTimeId != null) || (this.mealTimeId != null && !this.mealTimeId.equals(other.mealTimeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.meals.backend.model.Mealtime[ mealTimeId=" + mealTimeId + " ]";
    }
    
}
