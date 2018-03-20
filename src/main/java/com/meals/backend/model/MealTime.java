package com.meals.backend.model;
// Generated Mar 20, 2018 2:44:46 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MealTime generated by hbm2java
 */
@Entity
@Table(name="MealTime"
//    ,schema="dbo"
//    ,catalog="nsrp"
)
public class MealTime  implements java.io.Serializable {


     private int mealTimeId;
     private String mealTimeName;
     private String description;

    public MealTime() {
    }

	
    public MealTime(int mealTimeId, String mealTimeName) {
        this.mealTimeId = mealTimeId;
        this.mealTimeName = mealTimeName;
    }
    public MealTime(int mealTimeId, String mealTimeName, String description) {
       this.mealTimeId = mealTimeId;
       this.mealTimeName = mealTimeName;
       this.description = description;
    }
   
     @Id 

    
    @Column(name="MealTimeId", unique=true, nullable=false)
    public int getMealTimeId() {
        return this.mealTimeId;
    }
    
    public void setMealTimeId(int mealTimeId) {
        this.mealTimeId = mealTimeId;
    }

    
    @Column(name="MealTimeName", nullable=false, length=50)
    public String getMealTimeName() {
        return this.mealTimeName;
    }
    
    public void setMealTimeName(String mealTimeName) {
        this.mealTimeName = mealTimeName;
    }

    
    @Column(name="Description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


