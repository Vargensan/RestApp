package com.rest.domain.device.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.transaction.Transactional;

import org.hibernate.jpa.criteria.predicate.IsEmptyPredicate;

import com.rest.domain.device.control.CardMapper;
import com.rest.domain.device.control.DeviceMapper;
import com.rest.domain.device.control.NamespaceController;
import com.sun.xml.bind.v2.runtime.Name;




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

	
	private boolean isCorrectDevice(DeviceEntity deviceEntity) {
		return isSameIdentifierOrNotSet(deviceEntity) && 
				isSameIDOrNotSet(deviceEntity) &&
				isSameTypeOrNotSet(deviceEntity) &&
				isSameNumberOrNotSet(deviceEntity);
	}
	
	@Transactional
	public void updateDevice(DeviceEntity deviceEntity) {
		if(isCorrectDevice(deviceEntity)){
			serialNumber = deviceEntity.getSerialNumber();
			serialPart = deviceEntity.getSerialPart();
			updateCardsInsideBasedOn(deviceEntity);
		}
	}
	
	
	@Transactional
	private void updateCardsInsideBasedOn(DeviceEntity deviceEntity) {
		cards.retainAll(deviceEntity.getCards());
		deviceEntity.getCards().forEach(card -> {	
			if(cards.contains(card)) {
				CardEntity cardToUpdate = getEqualCard(cards, card);
				cardToUpdate.updateCard(card);
			}else {
				if(validateCardIdentifier(card)) {
					cards.add(card);
				}else {
					if(isCardAbleToResolveDependancies(card)) {
						resolveCardDependancies(card);
						cards.add(card);
					}
				}
			}
		});
	}
	
	private boolean isCardAbleToResolveDependancies(CardEntity cardEntity) {
		return cardEntity.isCardTypeSet() && 
				!cardEntity.isNumberSet() && 
				!cardEntity.isIdentifierSet();
	}
	
	private CardEntity getEqualCard(List<CardEntity> cards, CardEntity card) {
		for(CardEntity cardEntity : cards) {
			if(cardEntity.equals(card)) {
				return cardEntity;
			}
		}
		return null;
	}
	
	private boolean validateCardIdentifier(CardEntity cardEntity) {
		String correctID = NamespaceController.makeIdentifier( cardEntity.getNumber(), this.getIdentifier(),cardEntity.getCardType());
		if(correctID.equals(cardEntity.getIdentifier())) {
			return true;
		}else {
			return false;
		}
	}
	
	private void resolveCardDependancies(CardEntity cardInfo) {
		cardInfo.setNumber(
				NamespaceController.minimalPositiveNumberNotInCollection(
						cards.stream()
						.map(card -> card)
						.filter(predicate -> predicate.getCardType().equals(cardInfo.getCardType()))
						.collect(Collectors.toSet())
						.stream()
						.map(card -> card.getNumber())
						.collect(Collectors.toSet())
						)
				);
		
		cardInfo.setIdentifier(NamespaceController.makeIdentifier(cardInfo.getNumber(), this.getIdentifier(),cardInfo.getCardType()));
	}
	
	
	private boolean isSameIdentifierOrNotSet(DeviceEntity deviceEntity) {
		return deviceEntity.getIdentifier().equals(this.getIdentifier());
	}
	
	private boolean isSameIDOrNotSet(DeviceEntity deviceEntity) {
		return deviceEntity.getId() == this.getId() || deviceEntity.getId() == 0;
	}
	
	private boolean isSameTypeOrNotSet(DeviceEntity deviceEntity) {
		return deviceEntity.getType().equals(this.getType()) || deviceEntity.getType() == null || deviceEntity.getType() == "";
	}
	
	private boolean isSameNumberOrNotSet(DeviceEntity deviceEntity) {
		return deviceEntity.getNumber() == this.getNumber() || deviceEntity.getNumber() == 0;
	}
	
	
	@Override
	public String toString() {
		return "[" + id + "] - "+ type.getDeviceType()+ " - " + identifier;
	}

}
