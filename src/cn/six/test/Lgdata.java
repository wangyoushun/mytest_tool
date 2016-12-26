package cn.six.test;

import java.util.Date;

public class Lgdata {

	private String id;
	private String arriveCode;
	private String passengerName;
	private String passengerRecord;
	private String passengerTicketnum;
	private String passengerSeattype;
	private String credentialsType;
	private String cardNum;
	private Date aircraftDate;
	private String departureCode;
	private String aircraftNum;
	private String ticketType;
	private String coupon;
	private String seat;;
	private String ssr;
	private String passengerName2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArriveCode() {
		return arriveCode;
	}

	public void setArriveCode(String arriveCode) {
		this.arriveCode = arriveCode;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerRecord() {
		return passengerRecord;
	}

	public void setPassengerRecord(String passengerRecord) {
		this.passengerRecord = passengerRecord;
	}

	public String getPassengerTicketnum() {
		return passengerTicketnum;
	}

	public void setPassengerTicketnum(String passengerTicketnum) {
		this.passengerTicketnum = passengerTicketnum;
	}

	public String getPassengerSeattype() {
		return passengerSeattype;
	}

	public void setPassengerSeattype(String passengerSeattype) {
		this.passengerSeattype = passengerSeattype;
	}

	public String getCredentialsType() {
		return credentialsType;
	}

	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Date getAircraftDate() {
		return aircraftDate;
	}

	public void setAircraftDate(Date aircraftDate) {
		this.aircraftDate = aircraftDate;
	}

	public String getDepartureCode() {
		return departureCode;
	}

	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}

	public String getAircraftNum() {
		return aircraftNum;
	}

	public void setAircraftNum(String aircraftNum) {
		this.aircraftNum = aircraftNum;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getSsr() {
		return ssr;
	}

	public void setSsr(String ssr) {
		this.ssr = ssr;
	}

	public String getPassengerName2() {
		return passengerName2;
	}

	public void setPassengerName2(String passengerName2) {
		this.passengerName2 = passengerName2;
	}

	@Override
	public String toString() {
		return "Lgdata [id=" + id + ", arriveCode=" + arriveCode
				+ ", passengerName=" + passengerName + ", passengerRecord="
				+ passengerRecord + ", passengerTicketnum="
				+ passengerTicketnum + ", passengerSeattype="
				+ passengerSeattype + ", credentialsType=" + credentialsType
				+ ", cardNum=" + cardNum + ", aircraftDate=" + aircraftDate
				+ ", departureCode=" + departureCode + ", aircraftNum="
				+ aircraftNum + ", ticketType=" + ticketType + ", coupon="
				+ coupon + ", seat=" + seat + ", ssr=" + ssr
				+ ", passengerName2=" + passengerName2 + "]";
	}

}
