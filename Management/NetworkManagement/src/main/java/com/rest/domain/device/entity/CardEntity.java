package com.rest.domain.device.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.io.filefilter.FalseFileFilter;

@Entity
public class CardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String identifier;
	private int number;
	
	private CardType cardType;
	
	private long serialNumber;
	private long serialPart;
	

	public void setNumber(int number) {
		this.number = number;
	}
	public void setCardType(String cardType) {
		this.cardType = CardType.fromString(cardType);
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}
	
	public int getNumber() {
		return number;
	}

	public String getCardType() {
		return cardType.getName();
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	
	public void updateCard(CardEntity cardEntity) {
		this.serialNumber = cardEntity.getSerialNumber();
		this.serialPart = cardEntity.getSerialPart();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj == this) {
			return true;
		}
		CardEntity cardEntity = (CardEntity) obj;
		if(cardEntity.getIdentifier() == null || cardEntity.getIdentifier() == "") {
			return false;
		}
		if(cardEntity.getIdentifier().equals(this.getIdentifier())) {
			return true;
		}
		return false;
	}
	
	
	
}
