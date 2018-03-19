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
@Table(name = "meal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meal.findAll", query = "SELECT m FROM Meal m"),
    @NamedQuery(name = "Meal.findByMealId", query = "SELECT m FROM Meal m WHERE m.mealId = :mealId"),
    @NamedQuery(name = "Meal.findByDescription", query = "SELECT m FROM Meal m WHERE m.description = :description"),
    @NamedQuery(name = "Meal.findByMealName", query = "SELECT m FROM Meal m WHERE m.mealName = :mealName")})
public class Meal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "MealId")
    private Integer mealId;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MealName")
    private String mealName;

    public Meal() {
    }

    public Meal(Integer mealId) {
        this.mealId = mealId;
    }

    public Meal(Integer mealId, String mealName) {
        this.mealId = mealId;
        this.mealName = mealName;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mealId != null ? mealId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meal)) {
            return false;
        }
        Meal other = (Meal) object;
        if ((this.mealId == null && other.mealId != null) || (this.mealId != null && !this.mealId.equals(other.mealId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.meals.backend.model.Meal[ mealId=" + mealId + " ]";
    }
    
}
