package com.rest.domain.device.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.rest.domain.device.control.NamespaceController;




@Entity
public class DeviceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private long serialNumber;
	private long serialPart;
	
	private int number;
	private Type type;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="deviceID")
	private List<CardEntity> cards;
	
	private String identifier;
	
	public DeviceEntity() {
		cards = new ArrayList<>();
	}


	public int getId() {
		return id;
	}

	public void setNumber(int number) {
		this.number = number;
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
	
	public String getIdentifier() {
		return identifier;
	}

	public int getNumber() {
		return number;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	
	/*
	 * 	Update ->  are equals -> identifier are same;
	 *  Update -> not equals ->
	 *  deviceDTO/naming 
	 *  
	 *  Intenceptor -> logowanie REQUEST'ow do pliku 
	 */
	
	private boolean isCorrectDevice(DeviceEntity deviceEntity) {
		if(deviceEntity.getIdentifier().equals(this.getIdentifier())){
			if(deviceEntity.getId() == this.getId() || deviceEntity.getId() == 0) {
				if(deviceEntity.getType().equals(this.getType()) || deviceEntity.getType() == null) {
					if(deviceEntity.getNumber() == this.getNumber() || deviceEntity.getNumber() == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void updateDevice(DeviceEntity deviceEntity) {
		if(isCorrectDevice(deviceEntity)){
			this.serialNumber = deviceEntity.getSerialNumber();
			this.serialPart = deviceEntity.getSerialPart();
		}
		updateCardsInside(deviceEntity.getCards(), cards);
	}
	
	private void updateCardsInside(List<CardEntity> cardsToCheck, List<CardEntity> actualCards) {
		List<CardEntity> cardsToAdd = new ArrayList<>();
		
		actualCards.retainAll(cardsToCheck);
		for(CardEntity actualCard : actualCards) {
			for (CardEntity updateCard : cardsToCheck) {
				if(actualCard.equals(updateCard) && validateCard(updateCard)) {
					
					actualCard.updateCard(updateCard);
					
				}else {
					if(!cardsToAdd.contains(updateCard) && validateCard(updateCard)) {
					
						cardsToAdd.add(updateCard);
						
					}
				}
			}
		}
		actualCards.addAll(cardsToAdd);
	}
	
	private boolean validateCard(CardEntity cardEntity) {
		String correctID = NamespaceController.makeIdentifier( cardEntity.getNumber(), this.getIdentifier()+"/"+cardEntity.getCardType());
		if(correctID.equals(cardEntity.getIdentifier())) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public void setType(String type) {
		this.type = Type.fromString(type);
	}
	
	public String getType() {
		return type.getDeviceType();
	}
	
	public void setCards(List<CardEntity> cards) {
		this.cards.clear();
		this.cards.addAll(cards);
		
	}
	
	public List<CardEntity> getCards() {
		return cards;
	}
	
	
	@Override
	public String toString() {
		return "[" + id + "] - "+ type.getDeviceType()+ " - " + identifier;
	}

}
