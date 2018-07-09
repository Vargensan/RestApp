package com.api.networkmanagement;


public class CardDTO {

	private int id;
	
	private String identifier;
	private String cardType;
	

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
	
	public int getId() {
		return id;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
}
