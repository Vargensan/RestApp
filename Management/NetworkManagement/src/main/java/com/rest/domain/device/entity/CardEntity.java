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
	
	private CardType cardType;
	
	
	

	public void setCardType(String cardType) {
		this.cardType = CardType.fromString(cardType);
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}
	

	public String getCardType() {
		return cardType.getName();
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	
	public void updateCard(CardEntity cardEntity) {
		this.identifier = cardEntity.getIdentifier();
		this.cardType = CardType.fromString(cardEntity.getCardType());
	}

	@Override
	public boolean equals(Object obj) {
		CardEntity cardEntity = (CardEntity) obj;
		if(cardEntity.getId() != 0) {
			if(cardEntity.getId() == this.getId()) {
				return true;
			}else {
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	
}
