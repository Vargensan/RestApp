package com.rest.domain.device.entity;


public enum CardType {
	WEQ("WEQ"),
	DEQ("DEQ"),
	SEQ("SEQ");
	
	private String deviceType;
	
	
	CardType (String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getName() {
		return deviceType;
	};
	
	public static CardType fromString(String str) {
		str = str.toUpperCase();
		switch (str) {
			case "WEQ":
				return CardType.WEQ;
			case "DEQ":
				return CardType.DEQ;
			case "SEQ":
				return CardType.SEQ;
			default:
				return null;
		}
		
	}
}
