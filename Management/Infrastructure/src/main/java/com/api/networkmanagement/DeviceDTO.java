package com.api.networkmanagement;

import java.util.List;

public class DeviceDTO {
	
	private int id;
	
	private List<CardDTO> cards;
	private String type;
	private String identifier;
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
	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	

	public void setCards(List<CardDTO> cards) {
		this.cards = cards;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<CardDTO> getCards() {
		return cards;
	}
	
	
	public int getNumber() {
		return number;
	}


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
