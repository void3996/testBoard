package com.ict.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsVO {
/*  
    gdsNum       number          not null,
    gdsName      varchar2(50)    not null,
    cateCode     varchar2(30)    not null,
    gdsPrice     number          not null,
    gdsStock     number          null,
    gdsDes       varchar(500)    null,
    gdsImg       varchar(200)    null,
    gdsDate      date            default sysdate,
*/
	private int gdsNum;
	private int gdsPrice;
	private int gdsStock;
	private String gdsName;
	private String cateCode;
	private String gdsDes;
	private Date gdsDate;
	private List<GoodsAttachVO> attachList;
	
	public GoodsVO() {
		this.attachList = new ArrayList<>();
	}


	public int getGdsStock() {
		return gdsStock;
	}



	public void setGdsStock(int gdsStock) {
		this.gdsStock = gdsStock;
	}


	public int getGdsNum() {
		return gdsNum;
	}



	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}



	public int getGdsPrice() {
		return gdsPrice;
	}



	public void setGdsPrice(int gdsPrice) {
		this.gdsPrice = gdsPrice;
	}



	public String getGdsName() {
		return gdsName;
	}



	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}



	public String getCateCode() {
		return cateCode;
	}



	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}



	public String getGdsDes() {
		return gdsDes;
	}



	public void setGdsDes(String gdsDes) {
		this.gdsDes = gdsDes;
	}



	public Date getGdsDate() {
		return gdsDate;
	}



	public void setGdsDate(Date gdsDate) {
		this.gdsDate = gdsDate;
	}



	public List<GoodsAttachVO> getAttachList() {
		return attachList;
	}



	public void setAttachList(List<GoodsAttachVO> attachList) {
		this.attachList = attachList;
	}


	@Override
	public String toString() {
		return "GoodsVO [gdsNum=" + gdsNum + ", gdsPrice=" + gdsPrice + ", gdsStock=" + gdsStock + ", gdsName="
				+ gdsName + ", cateCode=" + cateCode + ", gdsDes=" + gdsDes + ", gdsDate=" + gdsDate + ", attachList="
				+ attachList + "]";
	}



			
}