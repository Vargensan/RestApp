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
		deviceEntity.setNumber(deviceDTO.getNumber());
		deviceEntity.setSerialNumber(deviceDTO.getSerialNumber());
		deviceEntity.setSerialPart(deviceDTO.getSerialPart());
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
		device.setNumber(deviceEntity.getNumber());
		device.setSerialNumber(deviceEntity.getSerialNumber());
		device.setSerialPart(deviceEntity.getSerialPart());
		device.setCards(deviceEntity.getCards()
				.stream()
				.map(cardMapper::mapToDTO)
				.collect(Collectors.toList()));
		
		
		return device;
	}
	
}
