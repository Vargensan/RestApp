package com.rest.domain.device.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;




@Entity
public class DeviceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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

	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdentifier() {
		return identifier;
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
	public void updateDevice(DeviceEntity deviceEntity) {
		updateCardsInside(deviceEntity.getCards(), cards);
		this.identifier = deviceEntity.getIdentifier();
	}
	
	private void updateCardsInside(List<CardEntity> cardsToCheck, List<CardEntity> actualCards) {
		List<CardEntity> cardsToAdd = new ArrayList<>();
		
		actualCards.retainAll(cardsToCheck);
		for(CardEntity actualCard : actualCards) {
			for (CardEntity updateCard : cardsToCheck) {
				if(actualCard.equals(updateCard)) {
					actualCard.updateCard(updateCard);
				}else {
					if(cardsToAdd.contains(updateCard)) {
						cardsToAdd.add(updateCard);
					}
				}
			}
		}
		actualCards.addAll(cardsToAdd);
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
