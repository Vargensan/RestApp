package com.rest.domain.device.control;

import com.api.networkmanagement.CardDTO;
import com.rest.domain.device.entity.CardEntity;

public class CardMapper{

	public CardEntity mapToEntity(CardDTO cardDTO) {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setCardType(cardDTO.getCardType());
		cardEntity.setIdentifier(cardDTO.getIdentifier());
		cardEntity.setId(cardDTO.getId());
		return cardEntity;
	}

	
	public CardDTO mapToDTO(CardEntity cardEntity) {
		CardDTO cardDTO = new CardDTO();
		cardDTO.setCardType(cardEntity.getCardType());
		cardDTO.setId(cardEntity.getId());
		cardDTO.setIdentifier(cardEntity.getIdentifier());
		return cardDTO;
	}




	
}
