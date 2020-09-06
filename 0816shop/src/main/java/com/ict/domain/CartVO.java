package com.ict.domain;

import java.util.Date;

public class CartVO {
/*  
    cartNum     number          not null,
    memId      varchar2(50)    not null,
    gdsNum      number          not null,
    cartStock   number          not null,
    addDate     date            default sysdate,
*/
	private int cartNum;
	private String memId;
	private int gdsNum;
	private int cartStock;
	private Date addDate;
	
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getGdsNum() {
		return gdsNum;
	}
	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}
	public int getCartStock() {
		return cartStock;
	}
	public void setCartStock(int cartStock) {
		this.cartStock = cartStock;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
			
}