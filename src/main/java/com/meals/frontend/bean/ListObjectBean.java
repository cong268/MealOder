package com.meals.frontend.bean;

import java.util.List;

public class ListObjectBean {
	List<MealsOrderBean> lstCateringSave;
	List<MealsOrderBean> lstCateringReject;

	public List<MealsOrderBean> getLstCateringSave() {
		return lstCateringSave;
	}

	public void setLstCateringSave(List<MealsOrderBean> lstCateringSave) {
		this.lstCateringSave = lstCateringSave;
	}

	public List<MealsOrderBean> getLstCateringReject() {
		return lstCateringReject;
	}

	public void setLstCateringReject(List<MealsOrderBean> lstCateringReject) {
		this.lstCateringReject = lstCateringReject;
	}

}
