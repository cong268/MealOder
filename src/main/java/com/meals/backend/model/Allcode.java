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
@Table(name = "allcode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Allcode.findAll", query = "SELECT a FROM Allcode a"),
    @NamedQuery(name = "Allcode.findByIdx", query = "SELECT a FROM Allcode a WHERE a.idx = :idx"),
    @NamedQuery(name = "Allcode.findByCodeName", query = "SELECT a FROM Allcode a WHERE a.codeName = :codeName"),
    @NamedQuery(name = "Allcode.findByTypeName", query = "SELECT a FROM Allcode a WHERE a.typeName = :typeName"),
    @NamedQuery(name = "Allcode.findByCodeVal", query = "SELECT a FROM Allcode a WHERE a.codeVal = :codeVal"),
    @NamedQuery(name = "Allcode.findByCodeIDX", query = "SELECT a FROM Allcode a WHERE a.codeIDX = :codeIDX"),
    @NamedQuery(name = "Allcode.findByContents", query = "SELECT a FROM Allcode a WHERE a.contents = :contents"),
    @NamedQuery(name = "Allcode.findByResourceID", query = "SELECT a FROM Allcode a WHERE a.resourceID = :resourceID"),
    @NamedQuery(name = "Allcode.findByModify", query = "SELECT a FROM Allcode a WHERE a.modify = :modify")})
public class Allcode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDX")
    private Integer idx;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Code_Name")
    private String codeName;
    @Size(max = 60)
    @Column(name = "Type_Name")
    private String typeName;
    @Size(max = 2)
    @Column(name = "Code_Val")
    private String codeVal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Code_IDX")
    private int codeIDX;
    @Size(max = 60)
    @Column(name = "Contents")
    private String contents;
    @Column(name = "Resource_ID")
    private Integer resourceID;
    @Column(name = "Modify")
    private Boolean modify;

    public Allcode() {
    }

    public Allcode(Integer idx) {
        this.idx = idx;
    }

    public Allcode(Integer idx, String codeName, int codeIDX) {
        this.idx = idx;
        this.codeName = codeName;
        this.codeIDX = codeIDX;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCodeVal() {
        return codeVal;
    }

    public void setCodeVal(String codeVal) {
        this.codeVal = codeVal;
    }

    public int getCodeIDX() {
        return codeIDX;
    }

    public void setCodeIDX(int codeIDX) {
        this.codeIDX = codeIDX;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

    public Boolean getModify() {
        return modify;
    }

    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idx != null ? idx.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Allcode)) {
            return false;
        }
        Allcode other = (Allcode) object;
        if ((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.meals.backend.model.Allcode[ idx=" + idx + " ]";
    }
    
}
