package com.api.networkmanagement;

import java.util.List;

public class DeviceDTO {
	
	private int id;
	
	private List<CardDTO> cards;
	private String type;
	private String identifier;

	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
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


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
