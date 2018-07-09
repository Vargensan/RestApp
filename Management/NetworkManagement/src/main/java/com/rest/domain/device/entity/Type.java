package com.rest.domain.device.entity;


public enum Type {

	CARD("CARD"),
	CONTROLLER("CONTROLLER"),
	PIN("PIN");
	
	private String deviceType;
	
	
	Type(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getDeviceType() {
		return deviceType;
	};
	
	public static Type fromString(String str) {
		str = str.toUpperCase();
		switch (str) {
			case "CARD":
				return Type.CARD;
			case "CONTROLLER":
				return Type.CONTROLLER;
			case "PIN":
				return Type.PIN;
			default:
				return null;
		}
		
	}
	

	
	
}
