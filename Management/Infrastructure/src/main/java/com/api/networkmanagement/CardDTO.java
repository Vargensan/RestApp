package com.api.networkmanagement;


public class CardDTO {

	private int id;
	
	private String identifier;
	private String cardType;
	private int number;
	
	private long serialNumber;
	private long serialPart;
	
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public void setSerialPart(long serialPart) {
		this.serialPart = serialPart;
	}
	
	public long getSerialNumber() {
		return serialNumber;
	}
	
	public long getSerialPart() {
		return serialPart;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
	public String getCardType() {
		return cardType;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getId() {
		return id;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
}
