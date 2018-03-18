/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meals.backend.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "userrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userrole.findAll", query = "SELECT u FROM Userrole u"),
    @NamedQuery(name = "Userrole.findByUserRoleID", query = "SELECT u FROM Userrole u WHERE u.userRoleID = :userRoleID"),
    @NamedQuery(name = "Userrole.findByCode", query = "SELECT u FROM Userrole u WHERE u.code = :code"),
    @NamedQuery(name = "Userrole.findByName", query = "SELECT u FROM Userrole u WHERE u.name = :name"),
    @NamedQuery(name = "Userrole.findByCreated", query = "SELECT u FROM Userrole u WHERE u.created = :created"),
    @NamedQuery(name = "Userrole.findByLastUpdated", query = "SELECT u FROM Userrole u WHERE u.lastUpdated = :lastUpdated"),
    @NamedQuery(name = "Userrole.findByPublicAccess", query = "SELECT u FROM Userrole u WHERE u.publicAccess = :publicAccess"),
    @NamedQuery(name = "Userrole.findByUid", query = "SELECT u FROM Userrole u WHERE u.uid = :uid"),
    @NamedQuery(name = "Userrole.findByDisable", query = "SELECT u FROM Userrole u WHERE u.disable = :disable")})
public class Userrole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserRoleID")
    private Integer userRoleID;
    @Size(max = 255)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;
    @Column(name = "Created")
    @Temporal(TemporalType.DATE)
    private Date created;
    @Lob
    @Size(max = 65535)
    @Column(name = "Description")
    private String description;
    @Column(name = "LastUpdated")
    @Temporal(TemporalType.DATE)
    private Date lastUpdated;
    @Column(name = "PublicAccess")
    private Boolean publicAccess;
    @Size(max = 255)
    @Column(name = "UID")
    private String uid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Disable")
    private boolean disable;

    public Userrole() {
    }

    public Userrole(Integer userRoleID) {
        this.userRoleID = userRoleID;
    }

    public Userrole(Integer userRoleID, String name, boolean disable) {
        this.userRoleID = userRoleID;
        this.name = name;
        this.disable = disable;
    }

    public Integer getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(Integer userRoleID) {
        this.userRoleID = userRoleID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Boolean getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(Boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean getDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRoleID != null ? userRoleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userrole)) {
            return false;
        }
        Userrole other = (Userrole) object;
        if ((this.userRoleID == null && other.userRoleID != null) || (this.userRoleID != null && !this.userRoleID.equals(other.userRoleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.meals.backend.model.Userrole[ userRoleID=" + userRoleID + " ]";
    }
    
}
