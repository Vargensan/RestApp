package com.rest.domain.device.control;

import java.util.stream.Collectors;

import com.api.networkmanagement.DeviceDTO;
import com.rest.domain.device.entity.DeviceEntity;

public class DeviceMapper{

	public DeviceEntity mapToEntity(DeviceDTO deviceDTO) {
		CardMapper cardMapper = new CardMapper();
		DeviceEntity deviceEntity = new DeviceEntity();
		
		deviceEntity.setId(deviceDTO.getId());
		deviceEntity.setIdentifier(deviceDTO.getIdentifier());
		deviceEntity.setType(deviceDTO.getType());
		deviceEntity.setCards(
				deviceDTO.getCards()
				.stream()
				.map(cardMapper::mapToEntity)
				.collect(Collectors.toList())
				);
		
		return deviceEntity;
	}
	
	
	public DeviceDTO mapToDTO(DeviceEntity deviceEntity) {
		DeviceDTO device = new DeviceDTO();
		CardMapper cardMapper = new CardMapper();
		
		device.setId(deviceEntity.getId());
		device.setIdentifier(deviceEntity.getIdentifier());
		device.setType(deviceEntity.getType());
		device.setCards(deviceEntity.getCards()
				.stream()
				.map(cardMapper::mapToDTO)
				.collect(Collectors.toList()));
		
		
		return device;
	}
	
}
