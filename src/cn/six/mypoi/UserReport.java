package cn.six.mypoi;

import java.io.Serializable;

/**
 * @author zml
 *
 *         2017年09月01日 15:39:35
 */
public class UserReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 年
	 */
	private Integer year;

	/**
	 * 月
	 */
	private Integer month;

	/**
	 * 新增买家
	 */
	private Integer addBuyers;

	/**
	 * 新增卖家
	 */
	private Integer addSeller;

	/**
	 * 新增用户
	 */
	private Integer newUser;

	/**
	 * 活跃买家
	 */
	private Integer activeBuyers;

	/**
	 * 活跃卖家
	 */
	private Integer activeSeller;

	/**
	 * 买家活跃率
	 */
	private String buyerActivityRate;

	/**
	 * 卖家活跃率
	 */
	private String sellerActivityRate;

	/**
	 * 成交买家
	 */
	private Integer dealBuyers;

	/**
	 * 成交卖家
	 */
	private Integer dealSeller;

	/**
	 * 累计买家
	 */
	private Integer accumulatedBuyers;

	/**
	 * 累计卖家
	 */
	private Integer accumulatedSeller;

	/**
	 * 总用户
	 */
	private Integer totalUser;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getAddBuyers() {
		return addBuyers;
	}

	public void setAddBuyers(Integer addBuyers) {
		this.addBuyers = addBuyers;
	}

	public Integer getAddSeller() {
		return addSeller;
	}

	public void setAddSeller(Integer addSeller) {
		this.addSeller = addSeller;
	}

	public Integer getNewUser() {
		return newUser;
	}

	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}

	public Integer getActiveBuyers() {
		return activeBuyers;
	}

	public void setActiveBuyers(Integer activeBuyers) {
		this.activeBuyers = activeBuyers;
	}

	public Integer getActiveSeller() {
		return activeSeller;
	}

	public void setActiveSeller(Integer activeSeller) {
		this.activeSeller = activeSeller;
	}

	public String getBuyerActivityRate() {
		return buyerActivityRate;
	}

	public void setBuyerActivityRate(String buyerActivityRate) {
		this.buyerActivityRate = buyerActivityRate;
	}

	public String getSellerActivityRate() {
		return sellerActivityRate;
	}

	public void setSellerActivityRate(String sellerActivityRate) {
		this.sellerActivityRate = sellerActivityRate;
	}

	public Integer getDealBuyers() {
		return dealBuyers;
	}

	public void setDealBuyers(Integer dealBuyers) {
		this.dealBuyers = dealBuyers;
	}

	public Integer getDealSeller() {
		return dealSeller;
	}

	public void setDealSeller(Integer dealSeller) {
		this.dealSeller = dealSeller;
	}

	public Integer getAccumulatedBuyers() {
		return accumulatedBuyers;
	}

	public void setAccumulatedBuyers(Integer accumulatedBuyers) {
		this.accumulatedBuyers = accumulatedBuyers;
	}

	public Integer getAccumulatedSeller() {
		return accumulatedSeller;
	}

	public void setAccumulatedSeller(Integer accumulatedSeller) {
		this.accumulatedSeller = accumulatedSeller;
	}

	public Integer getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}

	@Override
	public String toString() {
		return "UserReport [year=" + year + ", month=" + month + ", addBuyers=" + addBuyers + ", addSeller=" + addSeller
				+ ", newUser=" + newUser + ", activeBuyers=" + activeBuyers + ", activeSeller=" + activeSeller
				+ ", buyerActivityRate=" + buyerActivityRate + ", sellerActivityRate=" + sellerActivityRate
				+ ", dealBuyers=" + dealBuyers + ", dealSeller=" + dealSeller + ", accumulatedBuyers="
				+ accumulatedBuyers + ", accumulatedSeller=" + accumulatedSeller + ", totalUser=" + totalUser + "]";
	}

}
